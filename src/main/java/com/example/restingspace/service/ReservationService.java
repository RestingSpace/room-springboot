package com.example.restingspace.service;

import com.example.restingspace.Dao.ReservationDao;
import com.example.restingspace.model.Reservation;
import com.example.restingspace.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationDao reservationDao;

    public List<Reservation> getAllReservations(String username) {
        return reservationDao.getAllReservations(username);
    }

    public List<Reservation> getAllReservations(int roomid) {
        return reservationDao.getAllReservations(roomid);
    }

    public Reservation addReservation(Reservation reservation) {
        Timestamp start_time = reservation.getStart_time();
        Timestamp end_time = reservation.getEnd_time();
        Date starttime = new Date(start_time.getTime());
        Date endtime = new Date(end_time.getTime());
        Room room = reservation.getRoom();
        long diff = endtime.getTime() - starttime.getTime();
        int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
        int diffhours = (int) (diff / (60 * 60 * 1000));
        int diffmin = (int) (diff / (60 * 1000));
        double total_time= (double)diffDays*24+(double)diffhours+(double)diffmin/60;
        double total_price = room.getPrice()* total_time;
        reservation.setTotalPrice(total_price);
        reservationDao.addReservation(reservation);
        return reservation;
    }

    public Reservation getReservationById(int reservationId) {
        return reservationDao.getReservationById(reservationId);
    }

    public void deleteReservation(int reservationId) {
        reservationDao.cancelReservation(reservationId);
    }
}
