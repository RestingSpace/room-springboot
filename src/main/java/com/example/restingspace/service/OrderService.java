package com.example.restingspace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restingspace.Dao.OrderDao;
import com.example.restingspace.model.Order;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;
    
    public void addOrder(Order order) {
   	 orderDao.addOrder(order);
    }
}
