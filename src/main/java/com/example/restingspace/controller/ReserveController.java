package com.example.restingspace.controller;
import com.example.restingspace.model.Reservation;
import com.example.restingspace.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReserveController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping(path="/reserve")
    public @ResponseBody Reservation reserveRoom(@RequestBody Reservation reservation) {
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
    public @ResponseBody List<Reservation> getReservationsbyR(@PathVariable(value="room_id") long room_id) {
        return reservationService.getAllReservations(room_id);
    }
}
