package com.example.restingspace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.restingspace.model.Order;
import com.example.restingspace.model.Reservation;
import com.example.restingspace.model.User;
import com.example.restingspace.service.OrderService;
import com.example.restingspace.service.ReservationService;

@Controller
public class OrderController {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/reserve/{reservationId}", method = RequestMethod.GET)
	public String createOrder(@PathVariable("reservationId") int reservationId) {

		Order order = new Order();
		Reservation reservation = reservationService.getReservationById(reservationId);
		order.setReservation(reservation);

		User user = reservation.getUser();
		order.setUser(user);
		order.setBillingAddress(user.getBillingAddress());
		orderService.addOrder(order);
		return "redirect:/checkout?cartId=" + reservationId;
	}
}
