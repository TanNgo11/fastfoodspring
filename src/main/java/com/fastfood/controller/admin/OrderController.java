package com.fastfood.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.dto.OrderDTO;
import com.fastfood.service.IOrderService;
import com.fastfood.service.impl.OrderService;
import com.fastfood.utils.DateUtil;

@Controller
public class OrderController {
	
	@Autowired
	private IOrderService orderService;

	@RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
	public ModelAndView orderPage() {
		ModelAndView mav = new ModelAndView("/admin/order");
		DateUtil dateUtil = new DateUtil();
		dateUtil.populateMonthsAndYears();
		List<String> listMonthAndYear = dateUtil.getMonthsAndYears();
		mav.addObject("listMonthAndYear", listMonthAndYear);
		return mav;
	}
	
	
	@RequestMapping(value = "/admin/order/{id}", method = RequestMethod.GET)
	public ModelAndView orderDetailPage(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("/admin/order-detail");
		
		OrderDTO order = orderService.getOrderbyId(id);
	
		mav.addObject("order", order);
		return mav;
	}
	
	
	

}
