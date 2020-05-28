package com.example.restingspace.controller;
import com.example.restingspace.model.Reservation;
import com.example.restingspace.model.Room;
import com.example.restingspace.model.User;
import com.example.restingspace.service.ReservationService;
import com.example.restingspace.service.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@RestController
public class ReserveController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping(path="/reserve")
    public @ResponseBody Reservation reserveRoom(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }
/*
    @PostMapping(path="/reserve")
    public @ResponseBody Reservation reserveRoom(@RequestBody ReservationRequestBody reservationbody) {
        Reservation reservation = new Reservation();
        reservation.setDate(reservationbody.getDate());
        reservation.setStart_time(reservationbody.getStart_time());
        reservation.setEnd_time(reservationbody.getEnd_time());
        String username = reservationbody.getUsername();
        long rid = reservationbody.getRid();
        User user = userService.getUserByUsername(username);
        Room room =roomService.getRoomById(rid);
        reservation.setRoom(room);
        reservation.setUser(user);
        return reservationService.addReservation(reservation);
    }
*/
    @DeleteMapping(path="/cancelReserve/{reservationId}")
    public void cancelReservedRoom(@PathVariable(value="reservationId") long reservationId) {
        reservationService.deleteReservation(reservationId);
    }

    @GetMapping(path="/reservations/{username}")
    public @ResponseBody List<Reservation> getReservationsbyU (@PathVariable(value="username") String username) {
        return reservationService.getAllReservations(username);
    }

    @GetMapping(path="/reservationsbyRoom/{room_id}")
    public @ResponseBody List<Reservation> getReservationsbyR(@PathVariable(value="room_id") long room_id) {
        return reservationService.getAllReservations(room_id);
    }
}

/*
class ReservationRequestBody{
    private Date date;

    private Date start_time;

    private Date end_time;

    public Date getDate() {
        return date;
    }

    public Date getStart_time() {
        return start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public long getRid() {
        return rid;
    }

    public String getUsername() {
        return username;
    }

    private String username;

    private long rid;


}
*/
