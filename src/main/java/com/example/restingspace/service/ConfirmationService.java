package com.example.restingspace.service;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.restingspace.model.Reservation;
import com.example.restingspace.model.User;

@Service
public class ConfirmationService {
	private JavaMailSender javaMailSender;

	@Autowired
	public ConfirmationService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/*public void sendConfirmation(Reservation reservation) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("no-reply@resting-room.com");
		mail.setTo(reservation.getUser().getEmail());
		mail.setSubject("Your reservation is received!");
		mail.setText(reservation.getUser().getFirstName() 
				+ "\n Thank you for your reservation on Resting-room. "
				+ "Your reservation from " + reservation.getStart_time() 
				+ " to " + reservation.getEnd_time() + " has been confirmed.");

		javaMailSender.send(mail);
	}*/
	public void sendConfirmation(User user) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("no-reply@resting-room.com");
		mail.setTo(user.getEmail());
		mail.setSubject("Your reservation is received!");
		mail.setText(user.getFirstName() 
				+ "\n Thank you for your reservation on Resting-room. "
				+ "Your reservation has been confirmed.");

		javaMailSender.send(mail);
	}
	/*public void sendConfirmationWithAttachment(Reservation reservation, String filePath) throws Exception {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setSubject("Your reservation is received!");
		mimeMessageHelper.setFrom("no-reply@resting-room.com");
		mimeMessageHelper.setTo(reservation.getUser().getEmail());
		mimeMessageHelper.setText(reservation.getUser().getFirstName() 
				+ "\n Thank you for your reservation on Resting-room. "
				+ "Your reservation from " + reservation.getStart_time() 
				+ " to " + reservation.getEnd_time() + " has been confirmed."
				+ "Attached please find the QR code to enter your room");
		FileSystemResource file = new FileSystemResource(new File(filePath));
		mimeMessageHelper.addAttachment("Room QR code", file);
		javaMailSender.send(mimeMessage);
	}*/
	public void sendConfirmationWithAttachment(User user, String filePath) throws Exception {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setSubject("Your reservation is received!");
		mimeMessageHelper.setFrom("no-reply@resting-room.com");
		mimeMessageHelper.setTo(user.getEmail());
		mimeMessageHelper.setText(user.getFirstName() 
				+ "\n Thank you for your reservation on Resting-room. "
				+ "Your reservation has been confirmed."
				+ "Attached please find the QR code to enter your room");
		//FileSystemResource file = new FileSystemResource(new File(this.getClass().getClassLoader().getResource(filePath).toURI()));
		//FileSystemResource file = new FileSystemResource(new File(filePath));
		//mimeMessageHelper.addAttachment(file.getFilename(), file);
		ClassPathResource classPathResource = new ClassPathResource(filePath);
		mimeMessageHelper.addAttachment(classPathResource.getFilename(), classPathResource);
		javaMailSender.send(mimeMessage);
	}
}
