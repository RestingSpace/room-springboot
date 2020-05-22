package com.example.restingspace.model;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name ="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;
    private int start_time;
    private int end_time;
    //status: 1-> Not expired; 2-> Expired
    private int status;
    private Timestamp bookTime;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Reservation(){
        this.date = getDate();
        this.start_time = 6;
        this.end_time = 6;
        this.bookTime = new Timestamp(Calendar.getInstance().getTime().getTime());
        status = 1;
    }

    public Reservation(Date date, int start_time, int end_time, Room room, User user){
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.room = room;
        this.user = user;
        this.bookTime = new Timestamp(Calendar.getInstance().getTime().getTime());
        status = 1;
    }

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


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public long getTotalPrice() {
        return getRoom().getPrice()* (end_time - start_time);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
