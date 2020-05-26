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

    public List<Reservation> getAllReservations(){
        return reservationDao.getAllReservations();
    }
   /* public void addReservation(Reservation reservation){
        reservationDao.addReservation(reservation);
    }*/
    public Reservation getReservationById(int reservationId){
        return reservationDao.getReservationById(reservationId);
    }

    public void deleteReservation(int reservationId){
        reservationDao.cancelReservation(reservationId);
    }

}
