package com.fastfood.controller.admin;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.dto.ProductDTO;
import com.fastfood.service.impl.CategoryService;
import com.fastfood.service.impl.PieChartService;
import com.fastfood.service.impl.ProductService;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {

	@Autowired
	PieChartService chart;

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("admin/home");
		return mav;
	}

	@RequestMapping(value = "/admin/chart", method = RequestMethod.GET)
	public ModelAndView chartPage(ModelMap modelMap) {
		List<List<Map<Object, Object>>> canvasjsDataList = chart.getCanvasjsChartData();
		modelMap.addAttribute("dataPointsList", canvasjsDataList);
		ModelAndView mav = new ModelAndView("admin/chart");
		return mav;
	}

	@RequestMapping(value = "/admin/product/edit", method = RequestMethod.GET)
	public ModelAndView createPage(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/edit");
		ProductDTO product = new ProductDTO();
		if (id != null) {
			product = productService.findById(id);
		}

		mav.addObject("product", product);
		mav.addObject("categories", categoryService.findAll());
		return mav;
	}

}
