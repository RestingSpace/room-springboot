package com.example.restingspace.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1910400635577541899L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long rid;
    private boolean isValid;
    private String location;
    private int price;
    private long size;
    private String roomImageURL;

    public long getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getRoomImage() {
        return roomImageURL;
    }

    public void setRoomImage(String roomImageURL) {
        this.roomImageURL = roomImageURL;
    }
}
