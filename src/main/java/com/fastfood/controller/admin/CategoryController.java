package com.fastfood.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.service.impl.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	

	@RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("/admin/category");
		return mav;
	}
	
	

}