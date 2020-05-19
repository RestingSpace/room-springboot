package com.example.restingspace.Dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {
    @Autowired
    private SessionFactory sessionFactory;

}
