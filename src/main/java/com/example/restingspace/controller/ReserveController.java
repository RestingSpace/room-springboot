package com.example.restingspace.controller;
import com.example.restingspace.model.Reservation;
import com.example.restingspace.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReserveController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping(consumes = "application/json", produces = "application/json",path="/reserve")
    public void reserveRoom(@RequestBody Reservation reservation) {
        reservationService.addReservation(reservation);
    }
    @DeleteMapping(consumes = "application/json", produces = "application/json", path="/cancelReserve/{reservationId}")
    public void cancelReservedRoom(@PathVariable(value="reservationId") int reservationId) {
        reservationService.deleteReservation(reservationId);
    }
}
