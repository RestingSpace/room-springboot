package com.example.restingspace.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.restingspace.model.Reservation;
import com.example.restingspace.model.User;
import com.example.restingspace.service.ConfirmationService;
import com.example.restingspace.service.ReservationService;

@EnableWebMvc
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
	
	//test version with temp QR ok
	/*@RequestMapping(value = "/send-email")
	public String sendConfirmationWithAttachment() {
		
		
		User user = new User();
		user.setFirstName("Dorothy");
		user.setLastName("Lovegood");
		user.setEmail("mochou113@gmail.com");
		
		
		try {
			//confirmationService.sendConfirmationWithAttachment(user, "src/main/resources/Sakura.jpg");
			//confirmationService.sendConfirmationWithAttachment(user, "QRcode.png");
			confirmationService.sendConfirmationWithAttachment(user, "QRcode.png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "confirmation email sent";
	}*/
	
	@RequestMapping(value = "/send-email/{reservationId}", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] sendConfirmationWithAttachment(@PathVariable(value="reservationId") int reservationId) {
		
		Reservation reservation = reservationService.getReservationById(reservationId);
		
		try {
			//confirmationService.sendConfirmationWithAttachment(reservation, "QRcode.png");
			 byte[] qrCode = confirmationService.sendConfirmationWithBufferedQR(reservation);
			System.out.println("confirmation email sent");
			return qrCode;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		//return "confirmation email sent";
	}
}
