package com.example.restingspace.controller;
import com.example.restingspace.model.Reservation;
import com.example.restingspace.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReserveController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping(path="/reserve")
    public void reserveRoom(@RequestBody Reservation reservation) {
        reservationService.addReservation(reservation);
    }
    @DeleteMapping(path="/cancelReserve/{reservationId}")
    public void cancelReservedRoom(@PathVariable(value="reservationId") int reservationId) {
        reservationService.deleteReservation(reservationId);
    }
}
