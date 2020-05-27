package com.example.restingspace.service;

import com.example.restingspace.Dao.UserDao;
import com.example.restingspace.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}