package com.example.restingspace.service;

import com.example.restingspace.Dao.UserDao;
import com.example.restingspace.model.Reservation;
import com.example.restingspace.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void addUser(User user){
        userDao.addUser(user);
    }

    public User getUserByUsername(String username){
        return userDao.getUserByUsername(username);
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public List<Reservation> getReservationsByUsername(String username){
        return userDao.getReservationsByUsername(username);
    }

}
