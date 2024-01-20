package com.fastfood.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.fastfood.service.IMailService;

@Service
public class MailService implements IMailService {

	@Autowired
	private JavaMailSender emailSender;
	
	

	@Override
	public String sendSimpleMail() {
		for (int i = 0; i < 10; i++) {

			SimpleMailMessage message = new SimpleMailMessage();

			message.setTo("tan.ngo.cit20@eiu.edu.vn");
			message.setSubject("Test Simple Email");
			message.setText("Hello, Im testing Simple Email");

			System.out.println(i);

			this.emailSender.send(message);
		}

		return "Email Sent!";
	}

	@Override
	public String sendTemplateMail() throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();

		boolean multipart = true;

		MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

		String htmlMsg = "<h3>Im testing send a HTML email</h3>"
				+ "<img src='http://www.apache.org/images/asf_logo_wide.gif'>";

		message.setContent(htmlMsg, "text/html");

		helper.setTo("tan.ngo.cit20@eiu.edu.vn");

		helper.setSubject("Test send HTML email");

		this.emailSender.send(message);

		return "Email Sent!";
	}

}
