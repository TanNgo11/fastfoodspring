package com.fastfood.controller.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fastfood.constant.SystemConstant;
import com.fastfood.converter.AccountConverter;
import com.fastfood.dto.AccountDTO;
import com.fastfood.dto.OrderDTO;
import com.fastfood.dto.ProductDTO;
import com.fastfood.entity.AccountEntity;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.IAccountService;
import com.fastfood.service.IOrderService;
import com.fastfood.service.IProductService;
import com.fastfood.utils.MessageUtil;
import com.fastfood.utils.RestFB;
import com.fastfood.utils.SecurityUtils;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@Autowired
	private IProductService productService;

	@Autowired
	private IOrderService orderService;

	@Autowired
	IAccountService accountService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	AccountConverter accountConverter;

	@Autowired
	private RestFB restFB;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage(@ModelAttribute("foodModel") ProductDTO foodModel,
			@ModelAttribute("drinkModel") ProductDTO drinkModel) {
		Pageable pageable = new PageRequest(0, 4);
		ModelAndView mav = new ModelAndView("web/home");
		foodModel.setListResult(productService.findByCategory_idAndStatus(1, pageable, SystemConstant.ACTIVE_STATUS));
		foodModel.splitImg();
		drinkModel.setListResult(productService.findByCategory_idAndStatus(2, pageable, SystemConstant.ACTIVE_STATUS));
		drinkModel.splitImg();
		mav.addObject("foodModel", foodModel);
		mav.addObject("drinkModel", drinkModel);
		return mav;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detailPage(@RequestParam(name = "pid") long pid,
			@ModelAttribute("productDetail") ProductDTO productDetail, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("web/detail");
		productDetail = productService.findById(pid);
		mav.addObject("productDetail", productDetail);

		return mav;
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView cartPage() {
		ModelAndView mav = new ModelAndView("web/cart");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		AccountDTO account = null;
		if (authentication instanceof AnonymousAuthenticationToken) {
			account = new AccountDTO();
		} else {
			AccountEntity userEntity = userRepository.findOneByUserNameAndStatus(
					SecurityUtils.getPrincipal().getUsername(), SystemConstant.ACTIVE_STATUS);
			account = accountConverter.toDTO(userEntity);
		}

		mav.addObject("account", account);
		return mav;
	}

	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public ModelAndView cart(HttpServletRequest request, HttpSession session,
			@ModelAttribute("account") AccountDTO account) {
		ModelAndView mav = new ModelAndView();

		session = request.getSession();
		OrderDTO orderSession = (OrderDTO) session.getAttribute("cart");

		if (orderSession == null) {
			orderSession = new OrderDTO();

		}
		// check login and empty cart
		if (account.getUsername() == null) {
			request.setAttribute("msg", MessageUtil.ERROR_LOGIN);
		}
		if (orderSession.getItems().size() == 0) {
			request.setAttribute("msg", MessageUtil.ERROR_EMPTYCART);
		} else {
			orderSession.setAccountDTO(account);
			orderSession.setEmail(account.getEmail());
			orderSession.setAddress(account.getAddress());
			orderSession.setPhonenumber(account.getPhoneNumber());
			orderSession.setCustomerName(account.getFullName());
			request.setAttribute("msg", MessageUtil.SUCCESS_ORDER);
			orderService.save(orderSession);
		}
		session.removeAttribute("cart");

		mav.setViewName("web/cart");

		return mav;

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
		UserDetails userDetail = restFB.buildUser(user);
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
//		haha
		
		return "redirect:/home";
	}

}
