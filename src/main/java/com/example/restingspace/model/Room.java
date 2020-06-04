package com.example.restingspace.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="rooms")
public class Room implements Serializable {

    private static final long serialVersionUID = 1910400635577541899L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rid;
    private boolean isValid;
    private String location;
    private int price;
    private long size;
    @Transient
    private MultipartFile roomImage;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
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

    public MultipartFile getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(MultipartFile roomImage) {
        this.roomImage = roomImage;
    }
}
