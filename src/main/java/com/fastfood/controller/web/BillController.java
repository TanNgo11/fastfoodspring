package com.fastfood.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.dto.OrderDTO;
import com.fastfood.service.IOrderService;
import com.fastfood.utils.SecurityUtils;

@Controller
public class BillController {

	@Autowired
	private IOrderService orderServive;

	@RequestMapping(value = "/bill", method = RequestMethod.GET)
	public ModelAndView billPage() {
		ModelAndView mav = new ModelAndView("/web/bill");

		return mav;
	}

	@RequestMapping(value = "/bill/{id}", method = RequestMethod.GET)
	public ModelAndView billPageDetail(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("/web/bill-detail");
		OrderDTO order = orderServive.findByIdAndUserId(id, SecurityUtils.getPrincipal().getId());

		mav.addObject("order", order);
		return mav;
	}

}
