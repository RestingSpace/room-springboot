package com.example.restingspace.controller;
import com.example.restingspace.model.Reservation;
import com.example.restingspace.model.User;
import com.example.restingspace.service.ReservationService;
import com.example.restingspace.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReserveController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/myReservation/getReservationById", method = RequestMethod.GET)
	public ModelAndView getReservationId(){
		ModelAndView modelAndView = new ModelAndView("reservation");
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		User user = userService.getUserByUsername(username);
		modelAndView.addObject("reservationId", user.getReservation().getId());
		return modelAndView;
	}

    @DeleteMapping(path="/cancelReserve/{reservationId}")
    public void cancelReservedRoom(@PathVariable(value="reservationId") int reservationId) {
        reservationService.deleteReservation(reservationId);
    }
}
