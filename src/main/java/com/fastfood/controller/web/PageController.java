package com.fastfood.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.dto.ProductDTO;
import com.fastfood.service.IProductService;

@Controller
public class PageController {
	
	@Autowired
	private IProductService productService;

	@RequestMapping(value = "/about-us", method = RequestMethod.GET)
	public ModelAndView categoryPage() {

		ModelAndView mav = new ModelAndView("web/aboutus");

		return mav;
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contactPage() {

		ModelAndView mav = new ModelAndView("web/contact");

		return mav;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchPage(@RequestParam(name = "q", required = false) String q) {

		ModelAndView mav = new ModelAndView("web/search");
		List<ProductDTO> listProduct = productService.findProductBySearchKeys(q);
		mav.addObject("listProduct", listProduct);
		mav.addObject("searchKey", q);
		return mav;
	}

}
