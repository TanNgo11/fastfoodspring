package com.fastfood.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.NewsDTO;
import com.fastfood.dto.ProductDTO;
import com.fastfood.mapper.AccountMapper;
import com.fastfood.service.IAccountService;
import com.fastfood.service.INewsService;
import com.fastfood.service.IProductService;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@Autowired
	private IProductService productService;

	@Autowired
	IAccountService accountService;

	@Autowired
	AccountMapper accountConverter;

	@Autowired
	private INewsService newsService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView homePage(@ModelAttribute("foodModel") ProductDTO foodModel,
			@ModelAttribute("drinkModel") ProductDTO drinkModel,
			@ModelAttribute("top4ProductSales") ProductDTO top4ProductSales) {
		Pageable pageable = new PageRequest(0, 4);
		ModelAndView mav = new ModelAndView("web/home");
		foodModel.setListResult(productService.findByCategory_idAndStatus(1, pageable, SystemConstant.ACTIVE_STATUS));

		drinkModel.setListResult(productService.findByCategory_idAndStatus(2, pageable, SystemConstant.ACTIVE_STATUS));

		top4ProductSales.setListResult(productService.getTop4ProductsBySales(pageable));

		List<NewsDTO> listNews = newsService.findTop2ByOrderByCreatedDateDesc();
		mav.addObject("top4ProductSales", top4ProductSales);
		mav.addObject("foodModel", foodModel);
		mav.addObject("drinkModel", drinkModel);
		mav.addObject("listNews", listNews);

		return mav;
	}

	@RequestMapping(value = "/detail/{slug}", method = RequestMethod.GET)
	public ModelAndView detailPage(@PathVariable String slug, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("web/detail");

		session.setAttribute("redirectUrlAfterLogin", request.getRequestURI());

		
		ProductDTO productDetail = productService.findBySlug(slug);
		mav.addObject("productId", productDetail.getId());
		mav.addObject("productDetail", productDetail);

		return mav;
	}

	@RequestMapping(value = "/category/**", method = RequestMethod.GET)
	public ModelAndView categoryPage(@ModelAttribute("foodModel") ProductDTO foodModel) {
		Pageable pageable = new PageRequest(0, 4);
		ModelAndView mav = new ModelAndView("web/category");

		return mav;
	}

}
