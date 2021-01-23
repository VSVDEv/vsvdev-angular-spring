package com.vsvdev.booking.vsvdevangularspring.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reserv")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDate checkin;

    @NotNull
    private LocalDate checkout;

    @ManyToOne
    private Room roomEntity;

    public Reservation() {
    }

    public Reservation(LocalDate checkin, LocalDate checkout, Room roomEntity) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.roomEntity = roomEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public Room getRoomEntity() {
        return roomEntity;
    }

    public void setRoomEntity(Room roomEntity) {
        this.roomEntity = roomEntity;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", checkin=" + checkin +
                ", checkout=" + checkout +
                ", roomEntity=" + roomEntity +
                '}';
    }
}
