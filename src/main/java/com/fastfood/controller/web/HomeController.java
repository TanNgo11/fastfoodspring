package com.fastfood.controller.web;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.dto.ItemDTO;
import com.fastfood.dto.OrderDTO;
import com.fastfood.dto.ProductDTO;
import com.fastfood.service.IAccountService;
import com.fastfood.service.IOrderService;
import com.fastfood.service.IProductService;
import com.fastfood.service.impl.AccountService;
import com.fastfood.utils.MessageUtil;
import com.fastfood.utils.SecurityUtils;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@Autowired
	private IProductService productService;

	@Autowired
	private IOrderService orderService;
	
	@Autowired 
	IAccountService accountService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage(@ModelAttribute("foodModel") ProductDTO foodModel,
			@ModelAttribute("drinkModel") ProductDTO drinkModel) {
		Pageable pageable = new PageRequest(0, 4);
		ModelAndView mav = new ModelAndView("web/home");
		foodModel.setListResult(productService.findByCategory_id(1, pageable));
		foodModel.splitImg();
		drinkModel.setListResult(productService.findByCategory_id(2, pageable));
		drinkModel.splitImg();
		mav.addObject("foodModel", foodModel);
		mav.addObject("drinkModel", drinkModel);
		return mav;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detailPage(@RequestParam(name = "pid") long pid,
			@ModelAttribute("productDetail") ProductDTO productDetail) {
		ModelAndView mav = new ModelAndView("web/detail");
		productDetail = productService.findById(pid);
		mav.addObject("productDetail", productDetail);
		return mav;
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView cartPage() {
		ModelAndView mav = new ModelAndView("web/cart");

		return mav;
	}

	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public ModelAndView cart(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		session = request.getSession();
		OrderDTO orderSession = (OrderDTO) session.getAttribute("cart");

		if (orderSession == null) {
			orderSession = new OrderDTO();

		}
		// check login and empty cart
		if (authentication instanceof AnonymousAuthenticationToken) {
			request.setAttribute("msg", MessageUtil.ERROR_LOGIN);
		}
		if (orderSession.getItems().size() == 0) {
			request.setAttribute("msg", MessageUtil.ERROR_EMPTYCART);
		}else {
			orderSession.setAccountDTO(accountService.findByUsername(SecurityUtils.getPrincipal().getUsername()));
			orderService.save(orderSession);
		}
		session.removeAttribute("cart");

		mav.setViewName("web/cart");

		return mav;

	}

}
