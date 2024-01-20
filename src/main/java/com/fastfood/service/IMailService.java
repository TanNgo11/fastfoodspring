package com.fastfood.service;

import javax.mail.MessagingException;

public interface IMailService {
	
	String sendSimpleMail();
	String sendTemplateMail() throws MessagingException;

}
