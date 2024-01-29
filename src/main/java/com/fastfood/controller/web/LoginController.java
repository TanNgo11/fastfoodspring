package com.fastfood.controller.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.dto.AccountDTO;
import com.fastfood.dto.MyUser;
import com.fastfood.entity.AccountEntity;
import com.fastfood.entity.PasswordResetToken;
import com.fastfood.oauth2.RestFB;
import com.fastfood.oauth2.RestGoogle;
import com.fastfood.repository.ResetPasswordTokenRepository;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.IMailService;
import com.fastfood.service.impl.CustomUserDetailsService;
import com.fastfood.utils.GooglePojo;
import com.fastfood.utils.SecurityUtils;

@Controller(value = "loginControllerOfWeb")
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

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("/web/login");
		return mav;
	}

	@RequestMapping(value = { "/resetPassword" }, method = RequestMethod.GET)
	public ModelAndView resetPasswordPage() {
		ModelAndView mav = new ModelAndView("resetPassword");
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public @ResponseBody Map<String, String> accessDenied() {
		Map<String, String> response = new HashMap<>();
		response.put("error", "Access Denied");
		response.put("message", "You do not have permission to access this resource");
		return response;
	}

	@RequestMapping("/AccessFacebook/login-facebook")
	public String loginFacebook(HttpServletRequest request) {
		String code = request.getParameter("code");
		String accessToken = "";
		try {
			accessToken = restFB.getToken(code);
		} catch (IOException e) {

			return "login?facebook=error";
		}

		com.restfb.types.User user = restFB.getUserInfo(accessToken);

		MyUser userDetail = (MyUser) restFB.buildUser(user);

		SecurityUtils.setPrincipal(userDetail, request);

		return "redirect:/user/" + userDetail.getId();
	}

	@RequestMapping("/login-google")
	public String loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException {
		String code = request.getParameter("code");

		if (code == null || code.isEmpty()) {
			return "redirect:/login?message=google_error";
		}
		String accessToken = googleUtils.getToken(code);

		GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);

		UserDetails userDetail = googleUtils.buildUser(googlePojo);

		SecurityUtils.setPrincipal(userDetail, request);
		return "redirect:/home";
	}

	@PostMapping("/forgot-password")
	public String forgotPasswordProcess(@ModelAttribute AccountDTO accountDTO) throws MessagingException {
		String output = "";
		AccountEntity account = userRepository.findOneByUserNameAndEmail(accountDTO.getUsername(),
				accountDTO.getEmail());

		if (account != null) {
			output = mailService.sendReSetPasswordTemplateMail(account);
		}

		if (output.equals("success")) {
			return "redirect:/login?msg=success_reset";
		}

		return "redirect:/login?msg=error_reset";
	}

	@GetMapping("/resetPassword/{token}")
	public String resetPasswordForm(@PathVariable String token, Model model) {
		PasswordResetToken reset = resetPasswordTokenRepository.findByToken(token);
		if (reset != null && customUserDetailsService.hasExipred(reset.getExpiryDateTime())) {
			model.addAttribute("email", reset.getAccountEntity().getEmail());
			model.addAttribute("username", reset.getAccountEntity().getUserName());
			return "resetPassword";
		}
		return "redirect:/forgotPassword?msg=error_reset";
	}

	@PostMapping("/resetPassword")
	public String passwordResetProcess(@ModelAttribute AccountDTO accountDTO) {
		AccountEntity account = userRepository.findOneByUserName(accountDTO.getUsername());

		if (account != null && account.getEmail().equals(accountDTO.getEmail())) {
			account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
			userRepository.save(account);

		}
		return "redirect:/login?msg=success_resetpassword";
	}

}
