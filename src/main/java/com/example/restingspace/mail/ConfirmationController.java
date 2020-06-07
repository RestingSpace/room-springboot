package com.example.restingspace.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restingspace.model.User;
import com.example.restingspace.service.ConfirmationService;
import com.example.restingspace.service.ReservationService;

@RestController
public class ConfirmationController {
	@Autowired
	private ConfirmationService confirmationService;
	@Autowired
	private ReservationService reservationService;
	/*	//formal version without QR
	 	@RequestMapping(value = "/send-email/{reservationId}")
	public String sendConfirmation(@PathVariable(value="reservationId") int reservationId) {
		
		Reservation reservation = reservationService.getReservationById(reservationId);
		
		try {
			confirmationService.sendConfirmation(reservation);
		} catch (MailException e) {
			
		}
		
		return "confirmation email sent";
	}*/
	/*//test version without QR
	 	@RequestMapping(value = "/send-email")
	public String sendConfirmation() {
		
		
		User user = new User();
		user.setFirstName("Dorothy");
		user.setLastName("Lovegood");
		//user.setEmail("linaw@usc.edu");
		user.setEmail("mochou113@163.com");
		//user.setEmail("mochou113@gmail.com");
		
		try {
			confirmationService.sendConfirmation(user);
		} catch (MailException e) {
			
		}
		
		return "confirmation email sent";
	}*/
	
	//test version with QR
	@RequestMapping(value = "/send-email")
	public String sendConfirmationWithAttachment() {
		
		
		User user = new User();
		user.setFirstName("Dorothy");
		user.setLastName("Lovegood");
		user.setEmail("mochou113@163.com");
		
		
		try {
			confirmationService.sendConfirmationWithAttachment(user, "Images/Sakura.jpg");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "confirmation email sent";
	}
}
