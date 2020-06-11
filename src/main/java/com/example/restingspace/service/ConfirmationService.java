package com.example.restingspace.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Timestamp;
//import java.time.format.DateTimeFormatter;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.restingspace.model.Reservation;
import com.example.restingspace.model.User;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

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
	public void sendConfirmationWithAttachment(Reservation reservation, String filePath) throws Exception {
		//String startTime = timestampToString(reservation.getStart_time());
		//String endTime = timestampToString(reservation.getEnd_time());
		
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
		ClassPathResource classPathResource = new ClassPathResource(filePath);
		mimeMessageHelper.addAttachment(classPathResource.getFilename(), classPathResource);
		javaMailSender.send(mimeMessage);
	}
	/*public void sendConfirmationWithAttachment(User user, String filePath) throws Exception {
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
	}*/
	/*private String timestampToString(Timestamp input) {
		//Timestamp timestamp = Timestamp.valueOf(input);
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		  
		//String timestampAsString = formatter.format(timestamp.toLocalDateTime());
		String timestampAsString = formatter.format(input.toLocalDateTime());
		return timestampAsString;
		//assertEquals("2020-06-10T07:00:00.0", timestampAsString);
	}*/
	public byte[] sendConfirmationWithBufferedQR(Reservation reservation) throws Exception {
		//String startTime = timestampToString(reservation.getStart_time());
		//String endTime = timestampToString(reservation.getEnd_time());
		String barcodeText = Long.toString(reservation.getId());
		byte[] qrCode = generateQRCodeImage(barcodeText);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setSubject("Your reservation is received!");
		mimeMessageHelper.setFrom("no-reply@resting-room.com");
		mimeMessageHelper.setTo(reservation.getUser().getEmail());
		//MimeMultipart multipart = new MimeMultipart("related");
		MimeMultipart multipart = new MimeMultipart();
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(reservation.getUser().getFirstName() 
				+ "\n Thank you for your reservation on Resting-room. "
				+ "Your reservation from " + reservation.getStart_time() 
				+ " to " + reservation.getEnd_time() + " has been confirmed."
				+ "Attached please find the QR code to enter your room");
		multipart.addBodyPart(messageBodyPart);
		MimeBodyPart img = new MimeBodyPart(); 
		ByteArrayDataSource byteArray = new ByteArrayDataSource(qrCode, "image/png"); 
		img.setDataHandler(new DataHandler(byteArray)); 
		img.setFileName("ReservationQR"); 
		multipart.addBodyPart(img);
		mimeMessage.setContent(multipart);
		javaMailSender.send(mimeMessage);
		return qrCode;
	}
	public static byte[] generateQRCodeImage(String barcodeText) {
	    try {
		QRCodeWriter barcodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = 
	      barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
	    return byteArrayOutputStream.toByteArray();
	} catch(Exception e) {
		System.err.println(e.getMessage());
		return null;
		}
	}
}
