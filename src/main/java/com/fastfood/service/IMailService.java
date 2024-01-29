package com.fastfood.service;

import javax.mail.MessagingException;

import com.fastfood.entity.AccountEntity;

public interface IMailService {
	
	String sendReSetPasswordMail(AccountEntity account);
	String sendReSetPasswordTemplateMail(AccountEntity account) throws MessagingException;

}
