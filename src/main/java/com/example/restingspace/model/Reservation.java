package com.example.restingspace.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name ="reservations")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 6336888006050033955L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id")
    private int id;

    @Column(name="time_reserved_start")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @NotNull
    private Timestamp start_time;

    @Column(name="time_reserved_end")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @NotNull
    private Timestamp end_time;

    //status: 1-> Not expired; 2-> Expired
    //private int status;
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;


    @ManyToOne
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

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public double getTotalPrice() { return totalPrice; }

    public void setTotalPrice(double price) { this.totalPrice = price; }
/*
    public int getStatus() {
        Timestamp cur_time = new Timestamp(System.currentTimeMillis());
        if(cur_time.after(end_time)){
            status= 2;
        }else{
            status = 1;
        }
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
*/
}
