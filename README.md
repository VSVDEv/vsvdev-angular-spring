# vsvdev-angular-spring
Simple rest service to booking room in hotel
rest(Spring boot), Front Angular, H2, Rest-assured(Test)
Require(java 11, node js, angular)
download -> run npm install, -> run test(Spring) -> run spring project -> folder postman collection for test rest, and for runner -> run angular(ng serve)->
go to http://localhost:4200 and check booking rooms

#########
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<header id="intro" style="display: block;">
  <article class="fullheight" style="height: 568px;">
    <div class="hgroup">
      <h1>London Hotel</h1>
      <h2>West London</h2>
      <p><a href="/#welcome"><img src="/assets/images/arrow.png" alt="down arrow"></a></p>
    </div>
  </article>

  <div class="scrollmagic-pin-spacer"
       style="display: block; top: auto; left: auto; bottom: auto; right: auto; position: relative; margin: 0 auto auto; box-sizing: content-box; width: 100%; min-height: 44px; height: auto; padding-top: 0; padding-bottom: 0;">
    <nav id="nav" style="position: relative; top: 0; left: 0; bottom: auto; right: auto; width: 100%;">
      <div class="navbar">
        <div class="brand"><a href="/#welcome">London <span>Hotel</span></a></div>
      </div><!-- navbar -->
    </nav>
  </div>
</header>
<main id="wrapper">
  <div class="scene" id="welcome">
    <article class="content">
     

     <div class="row">
     <div class="col-12">
      <div class="gallery">
        <img src="/assets/images/intro_room.jpg" alt="Intro Gallery Room Sample Pictures">
        <img src="/assets/images/intro_pool.jpg" alt="Intro Gallery Pool Sample Pictures">
        <img src="/assets/images/intro_dining.jpg" alt="Intro Gallery Dining Sample Pictures">
        <img src="/assets/images/intro_attractions.jpg" alt="Intro Gallery Attractions Sample Pictures">
        <img class="hidesm" src="/assets/images/intro_wedding.jpg" alt="Intro Gallery Dining Sample Pictures">
      </div>
      <h1>Book a Room</h1>
      <form class="form-horizontal" [formGroup]="roomsearch"
      (ngSubmit)="onSubmit(roomsearch)">
      <div class="form-group">
      <label for="checkin" class="col-4">Check-In:</label>
      <div class="col-8">
      <input id="checkin" type="date" class="form-control" 
      formControlName="checkin">
      </div>
      </div>
       <div class="form-group">
      <label for="checkout" class="col-4">Check-Out:</label>
      <div class="col-8">
      <input id="checkout" type="date" class="form-control" 
      formControlName="checkout">
      </div>
      </div>
       <div class="form-group">
       <div class="col-offset-2 col-10">
       <button type="submit" class="btn btn-primary btn-lg">Submit</button>
       </div>
       </div>
      </form>
     </div>
     </div>
      <h1>Available Rooms</h1>
<div class="row" *ngIf="rooms">
<div class="col-12">
<div class="table-responsive">
<table class="table">
<tbody>
<tr *ngFor="let room of rooms">
<td>
<img src="/assets/images/intro_room.jpg" alt="Intro Gallery Room Sample Pictures">
</td>
<td>
<strong>Room #:{{room.roomNumber}}</strong><br/>
<strong>Price: ${{room.price}}</strong>
</td>
<td>
<button type="submit" class="btn btn-primary" (click)="reserveRoom(room.id)">Reserve</button>
</td>
</tr>
</tbody>
</table>
</div>
</div>
</div>
    </article>
