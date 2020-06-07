package com.example.restingspace.mail;

import javax.validation.ValidationException;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.mail.SimpleMailMessage;

@RestController
public class FeedbackController {
	
	private EmailCfg emailCfg;
	
	public FeedbackController(EmailCfg emailCfg) {
		this.emailCfg = emailCfg;
	}
	@RequestMapping(value = "/support", method = RequestMethod.POST)
	public void sendFeedback(@RequestBody FeedbackForm feedback, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new ValidationException("feedback is not valid");
		}
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(this.emailCfg.getHost());
		mailSender.setPort(this.emailCfg.getPort());
		mailSender.setUsername(this.emailCfg.getUsername());
		mailSender.setPassword(this.emailCfg.getPassword());
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(feedback.getEmail());
		mailMessage.setTo("support@resting-room.com");
		mailMessage.setSubject("New feedback from " + feedback.getName());
		mailMessage.setText(feedback.getFeedback());
		
		mailSender.send(mailMessage);
	}

}
