package com.example.restingspace.Dao;

import com.example.restingspace.model.Reservation;
import com.example.restingspace.model.Room;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationDao {
    @Autowired
    private SessionFactory sessionFactory;

  //LW: our logic equivalent to combining cartDao and salesOrderDao, so here we have only ReservationDao to include all the method.
  //LW: A little bit messy here but functional...
    /* public void addReservation(Reservation reservation){
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
     }*/
     public Reservation getReservationById(int reservationId) {
     	Reservation reservation = null;
 		try (Session session = sessionFactory.openSession()) {
 			session.beginTransaction();
 			reservation = (Reservation) session.get(Reservation.class, reservationId);
 			session.getTransaction().commit();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return reservation;
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
     //LW: this is for admin use? 
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
     //reserve time can't be less than 5min and other rule, tbd
     public Reservation validate(int reservationId) throws IOException {
     	Reservation reservation = getReservationById(reservationId);
 		if (reservation == null || (reservation.getEnd_time() - reservation.getStart_time() < 5)) {
 			throw new IOException(reservationId + "");
 		}
 		update(reservation);
 		return reservation;
 	}

 	private void update(Reservation reservation) {
 		double total = getReservationTotal(reservation);
 		reservation.setTotalPrice(total);

 		try (Session session = sessionFactory.openSession()) {
 			session.beginTransaction();
 			session.saveOrUpdate(reservation);
 			session.getTransaction().commit();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 	}

 	private double getReservationTotal(Reservation reservation) {
 		double total = 0;
 		Room room = reservation.getRoom();
 		
 		total = room.getPrice() *(reservation.getEnd_time() - reservation.getStart_time());
 	
 		return total;
 	}

}
