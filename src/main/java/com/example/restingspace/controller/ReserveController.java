package com.example.restingspace.controller;

import com.example.restingspace.model.Reservation;
import com.example.restingspace.model.Room;
import com.example.restingspace.model.User;
import com.example.restingspace.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

@RestController
public class ReserveController {
    @Autowired
    private ReservationService reservationService;

    @RequestMapping(path="/reserve", method = RequestMethod.POST)
    public void reserveRoom(@RequestBody ReserveRequestBody reserveRequestBody,
                            Writer writer) throws IOException {
        Date date = reserveRequestBody.getDate();
        int start_time = reserveRequestBody.getStart_time();
        int end_time = reserveRequestBody.getEnd_time();
        Room room = reserveRequestBody.getRoom();
        User user = reserveRequestBody.getUser();
        reservationService.addReservation(new Reservation(date,start_time,end_time,room, user));
    }
    @RequestMapping(path="/cancelreserve/{reservationId}", method = RequestMethod.POST)
    public void cancelReservedRoom(@PathVariable(value="reservationId") int reservationId) {
        reservationService.deleteReservation(reservationId);
    }
}

class ReserveRequestBody{
    private Date date;
    private int start_time;
    private int end_time;
    private Room room;
    private User user;

    public Date getDate() {
        return date;
    }

    public int getStart_time() {
        return start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public Room getRoom() {
        return room;
    }

    public User getUser() {
        return user;
    }
}