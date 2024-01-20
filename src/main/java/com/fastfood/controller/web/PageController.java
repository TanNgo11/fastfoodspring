package com.fastfood.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	
	@RequestMapping(value = "/about-us", method = RequestMethod.GET)
	public ModelAndView categoryPage() {
		
		ModelAndView mav = new ModelAndView("web/aboutus");
		
		
		return mav;
	}

}
