package com.vsvdev.booking.vsvdevangularspring.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Integer roomNumber;
    @NotNull
    private String price;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Reservation> reservationEntityList;

    public Room() {
        super();
    }

    public Room(Integer roomNumber, String price) {
        super();
        this.roomNumber = roomNumber;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Reservation> getReservationEntityList() {
        return reservationEntityList;
    }

    public void setReservationEntityList(List<Reservation> reservationEntityList) {
        this.reservationEntityList = reservationEntityList;
    }

    public void addReservation(Reservation reservationEntity) {
        if (null == reservationEntityList)
            reservationEntityList = new ArrayList<>();

        reservationEntityList.add( reservationEntity );
    }

}
