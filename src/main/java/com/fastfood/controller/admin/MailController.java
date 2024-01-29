package com.fastfood.controller.admin;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fastfood.service.IMailService;

@Controller
public class MailController {

	@Autowired
	private IMailService mailService;

//	@ResponseBody
//	@RequestMapping("/sendSimpleEmail")
//	public String sendSimpleEmail() {
//		return mailService.sendReSetPasswordMail(account)();
//
//	}

//	@ResponseBody
//	@RequestMapping("/sendHtmlEmail")
//	public String sendHtmlEmail() throws MessagingException {
//
//		return mailService.s();
//	}

}
