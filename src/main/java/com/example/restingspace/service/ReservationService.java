package com.example.restingspace.service;

import com.example.restingspace.Dao.ReservationDao;
import com.example.restingspace.model.Reservation;
import com.example.restingspace.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationDao reservationDao;

    public List<Reservation> getAllReservations(String username){
        return reservationDao.getAllReservations(username);
    }
    public List<Reservation> getAllReservations(long roomid){
        return reservationDao.getAllReservations(roomid);
    }

   /* public void addReservation(Reservation reservation){
        reservationDao.addReservation(reservation);
    }*/
    public Reservation getReservationById(int reservationId){
        return reservationDao.getReservationById(reservationId);


    public Reservation addReservation(Reservation reservation){
        Room room = reservation.getRoom();
        long roomid = room.getRid();
        List<Reservation> reserves = getAllReservations(roomid);
        Date start_time = reservation.getStart_time();
        Date end_time = reservation.getEnd_time();
        Date date = reservation.getDate();
        if(end_time.before(start_time)){
            return null;
        }
        for(Reservation prev:reserves){
            if(prev.getDate().compareTo(date)==0){
                if(prev.getStart_time().before(start_time) && start_time.before(prev.getEnd_time())
                        ||end_time.before(prev.getEnd_time()) && end_time.after(prev.getStart_time())
                        ||prev.getStart_time()==start_time || prev.getEnd_time()==end_time
                ){
                    return null;
                }
            }
        }
        reservationDao.addReservation(reservation);
        return reservation;
    }

    public void deleteReservation(long reservationId){
        reservationDao.cancelReservation(reservationId);
    }

}
