package com.example.restingspace.model;


import javax.persistence.*;

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
