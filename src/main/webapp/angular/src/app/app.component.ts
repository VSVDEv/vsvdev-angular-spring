import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
// import { HttpClient } from '@angular/common/http';
// import { Observable } from 'rxjs';
// import "rxjs/add/operator/catch";
// import "rxjs/add/operator/map";
import { Observable, throwError } from "rxjs";
import {
	HttpClient,
	HttpHeaders,
	HttpErrorResponse
} from "@angular/common/http";
import { catchError, map } from "rxjs/operators";
@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
	title = 'angular';
	roomsearch: FormGroup;
	public submitted: boolean;
	rooms: Room[];
	private baseUrl: string = 'http://localhost:8080';
	private getUrl: string = this.baseUrl + '/room/reservation/v1/';
	private postUrl: string = this.baseUrl + '/room/reservation/v1';
	currentCheckInVal: string;
	currentCheckOutVal: string;
	request: ReserveRoomRequest;
	constructor(private http: HttpClient) { }
	ngOnInit() {
		this.roomsearch = new FormGroup({
			checkin: new FormControl(''),
			checkout: new FormControl('')
		});
		const roomsearchValueChanges$ = this.roomsearch.valueChanges;
		roomsearchValueChanges$.subscribe(valChange => {
			this.currentCheckInVal = valChange.checkin;
			this.currentCheckOutVal = valChange.checkout;
		})
	}

	reserveRoom(value: string) {
		this.request = new ReserveRoomRequest(value, this.currentCheckInVal, this.currentCheckOutVal);
		this.createReservation(this.request);
	}
	createReservation(body: ReserveRoomRequest) {
		let bodyString = JSON.stringify(body);
		const option = {
			headers: new HttpHeaders({
				'Content-Type': 'application/json'
			})
		};
		this.http.post(this.postUrl, body, option)
			.subscribe(res => console.log(res));
	}

	onSubmit({ value, valid }: { value: Roomsearch, valid: boolean }) {
		this.http.get(this.getUrl +
			'?checkin=' + this.currentCheckInVal + '&checkout=' + this.currentCheckOutVal).subscribe(
				data => {
					this.rooms = data["content"] as Room[];	 // FILL THE ARRAY WITH DATA.

				},
				(err: HttpErrorResponse) => {
					console.log(err.message);
				}
			);

	}

	getAll(): Observable<Room[]> {
		return this.http.get<Room[]>(this.baseUrl +
			'/room/reservation/v1?checkin=2021-03-18&checkout=2021-03-19');
		// .pipe(map(this.mapRoom));
	}

}

export interface Roomsearch {
	checkin: string;
	checkout: string;
}

export interface Room {
	id: string;
	roomNumber: string;
	price: string;
	links: string;
}

export class ReserveRoomRequest {
	roomId: string;
	checkin: string;
	checkout: string;

	constructor(roomId: string,
		checkin: string,
		checkout: string) {
		this.roomId = roomId;
		this.checkin = checkin;
		this.checkout = checkout;
	}
}
