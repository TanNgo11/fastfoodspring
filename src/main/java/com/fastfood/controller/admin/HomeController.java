package com.fastfood.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.service.impl.PieChartService;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {

	@Autowired
	PieChartService chart;

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
}
