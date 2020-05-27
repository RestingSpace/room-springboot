package com.example.restingspace.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id")
    private long id;

    @Column(name="date_reserved")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "UTC")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date date;

    @Column(name="time_reserved_start")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    @Temporal(TemporalType.TIME)
    @NotNull
    private Date start_time;

    @Column(name="time_reserved_end")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    @Temporal(TemporalType.TIME)
    @NotNull
    private Date end_time;

    //status: 1-> Not expired; 2-> Expired
    private int status;
    private int totalPrice;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Room getRoom(){
        return room;
    }

    public void setRoom(Room room){
        this.room = room;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int price) { this.totalPrice = price; }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
