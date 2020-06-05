package com.example.restingspace.controller;
import com.example.restingspace.service.RoomService;
import com.example.restingspace.model.Reservation;

import com.example.restingspace.model.Room;
import com.example.restingspace.model.User;
import com.example.restingspace.service.ReservationService;
import com.example.restingspace.service.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class ReserveController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/reserve")
    public @ResponseBody Reservation reserveRoom(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/reserve2")
    public @ResponseBody Reservation reserveRoom(@RequestBody ReservationRequestBody reservationbody) {
        Reservation reservation = new Reservation();
        reservation.setStart_time(reservationbody.getStart_time());
        reservation.setEnd_time(reservationbody.getEnd_time());
        String username = reservationbody.getUsername();
        int rid = reservationbody.getRid();
        User user = userService.getUserByUsername(username);
        Room room = roomService.getRoom(rid);
        reservation.setRoom(room);
        reservation.setUser(user);
        return reservationService.addReservation(reservation);
    }

    @DeleteMapping(path="/cancelReserve/{reservationId}")
    public void cancelReservedRoom(@PathVariable(value="reservationId") long reservationId) {
        reservationService.deleteReservation(reservationId);
    }

    @GetMapping(path="/reservations/{username}")
    public @ResponseBody List<Reservation> getReservationsbyU (@PathVariable(value="username") String username) {
        return reservationService.getAllReservations(username);
    }

    @GetMapping(path="/reservationsbyRoom/{room_id}")
    public @ResponseBody
    List<Reservation> getReservationsbyR(@PathVariable(value="room_id") int room_id) {
        return reservationService.getAllReservations(room_id);
    }
}


class ReservationRequestBody{
    private String username;
    private int rid;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp start_time;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp end_time;

    public Timestamp getStart_time() {
        return start_time;
    }
    public Timestamp getEnd_time() {
        return end_time;
    }
    public int getRid() {
        return rid;
    }
    public String getUsername() {
        return username;
    }

}
