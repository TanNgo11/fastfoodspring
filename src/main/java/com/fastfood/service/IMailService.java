package com.fastfood.service;

import javax.mail.MessagingException;

import com.fastfood.dto.OrderDTO;
import com.fastfood.entity.AccountEntity;

public interface IMailService {
	
	String sendReSetPasswordMail(AccountEntity account);
	String sendReSetPasswordTemplateMail(AccountEntity account) throws MessagingException;
	String sendInvoiceTemplateMail(AccountEntity account,OrderDTO order) throws MessagingException;

}
