package com.example.restingspace.Dao;

import com.example.restingspace.model.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void addReservation(Reservation reservation){
        Session session =null;

        try{
           session = sessionFactory.openSession();
           session.beginTransaction();
           session.save(reservation);
           session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    public void cancelReservation(int reservationId){
        Session session =null;

        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            Reservation reservation = (Reservation) session.get(Reservation.class, reservationId);
            session.delete(reservation);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }
    public List<Reservation> getAllReservations(){
        List<Reservation> reservations = new ArrayList<Reservation>();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Reservation> criteriaQuery = criteriaBuilder.createQuery(Reservation.class);
            Root<Reservation> root = criteriaQuery.from(Reservation.class);
            criteriaQuery.select(root);
            reservations = session.createQuery(criteriaQuery).getResultList();
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return reservations;
    }

}
