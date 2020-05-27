package com.example.restingspace.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long rid;

    private boolean isValid;
    private String location;
    private int price;
    private long size;

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    private Collection<Reservation> reservation;

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public String getLocation() {
        return location;
    }

    public int getPrice() {
        return price;
    }

    public long getSize() {
        return size;
    }


    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

}
