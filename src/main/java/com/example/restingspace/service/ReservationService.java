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

    public List<Reservation> getAllReservations(int uid){
        return reservationDao.getAllReservations(uid);
    }

    public void addReservation(Reservation reservation){


        reservationDao.addReservation(reservation);
    }

    public void deleteReservation(long reservationId){
        reservationDao.cancelReservation(reservationId);
    }

}
