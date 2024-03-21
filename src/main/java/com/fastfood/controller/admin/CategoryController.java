package com.fastfood.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.constant.SystemConstant;
import com.fastfood.service.impl.CategoryService;
import com.fastfood.utils.SecurityUtils;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("/admin/category");

		Map<Integer, String> mapAction = setListActionForStaff();
		if (SecurityUtils.getAuthorities().contains(SystemConstant.ROLE_ADMIN)) {
			mapAction = setListActionForAdmin();
		}

		mav.addObject("mapAction", mapAction);
		return mav;
	}

	private Map<Integer, String> setListActionForStaff() {
		Map<Integer, String> mapAction = new HashMap<>();

		mapAction.put(SystemConstant.PUBLIC, "Acitve");


		return mapAction;

	}

	private Map<Integer, String> setListActionForAdmin() {
		Map<Integer, String> mapAction = new HashMap<>();
		mapAction.put(SystemConstant.DRAFT, "Remove");
		mapAction.put(SystemConstant.PUBLIC, "Acitve");

		return mapAction;

	}

}
