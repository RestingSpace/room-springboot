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
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void addRoom(Room room){
        Session session =null;

        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(room);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    public void deleteRoom(int roomId){
        Session session =null;

        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            Room room = (Room) session.get(Room.class, roomId);
            session.delete(room);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    public void updateRoom(Room room){
        Session session =null;

        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(room);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }
    public Room getRoom(int roomId){
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Room room = (Room) session.get(Room.class, roomId);
            session.getTransaction().commit();
            return room;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Room> getAllRoom(){
        List<Room> rooms = new ArrayList<Room>();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
            Root<Room> root = criteriaQuery.from(Room.class);
            criteriaQuery.select(root);
            rooms = session.createQuery(criteriaQuery).getResultList();
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rooms;
    }
}
