package com.fastfood.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.utils.DateUtil;
@Controller
public class OrderController {
	
	
	
	
	@RequestMapping(value = "/admin/order", method = RequestMethod.GET)
	public ModelAndView orderPage() {
		ModelAndView mav = new ModelAndView("admin/order");
		DateUtil dateUtil = new DateUtil();
		dateUtil.populateMonthsAndYears();
		List<String> listMonthAndYear = dateUtil.getMonthsAndYears();
		mav.addObject("listMonthAndYear", listMonthAndYear);
		return mav;
	}

}
