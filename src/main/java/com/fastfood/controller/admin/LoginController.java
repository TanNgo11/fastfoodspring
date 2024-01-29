package com.fastfood.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.oauth2.RestFB;
import com.fastfood.oauth2.RestGoogle;
import com.fastfood.repository.ResetPasswordTokenRepository;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.IMailService;
import com.fastfood.service.impl.CustomUserDetailsService;

@Controller(value = "loginControllerOfAdmin")
public class LoginController {

	@Autowired
	private RestFB restFB;

	@Autowired
	private RestGoogle googleUtils;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IMailService mailService;

	@Autowired
	private ResetPasswordTokenRepository resetPasswordTokenRepository;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@RequestMapping(value = { "/admin/login" }, method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("admin/login");
		return mav;
	}

	@RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/home");
	}

	

}
