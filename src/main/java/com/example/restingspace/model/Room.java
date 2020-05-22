package com.example.restingspace.model;


import javax.persistence.*;

@Entity
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long rid;

    private final String DEFAULT_CITY = "LOS ANGELES";
    private final int DEFAULT_PRICE = 100;
    private final int DEFAULT_SIZE = 100;
    private boolean isValid;
    private String location;
    private int price;
    private long size;

    public Room(){
        location = DEFAULT_CITY;
        price = DEFAULT_PRICE;
        size = DEFAULT_SIZE;
    }

    public Room(int price, int size, String location){
        this.location = location;
        this.price = price;
        this.size = size;
    }
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
