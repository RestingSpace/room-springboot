package com.example.restingspace.service;

import com.example.restingspace.Dao.ReservationDao;
import com.example.restingspace.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationDao reservationDao;

    public List<Reservation> getAllReservations(String username) {
        return reservationDao.getAllReservations(username);
    }

    public List<Reservation> getAllReservations(long roomid) {
        return reservationDao.getAllReservations(roomid);
    }

    public Reservation addReservation(Reservation reservation) {
        reservationDao.addReservation(reservation);
        return reservation;
    }

    public Reservation getReservationById(int reservationId) {
        return reservationDao.getReservationById(reservationId);
    }

    public void deleteReservation(long reservationId) {
        reservationDao.cancelReservation(reservationId);
    }
}
