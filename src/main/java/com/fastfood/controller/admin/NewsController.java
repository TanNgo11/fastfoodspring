package com.fastfood.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.NewsDTO;
import com.fastfood.service.INewsService;
import com.fastfood.utils.MessageUtil;
import com.fastfood.utils.SecurityUtils;

@Controller(value = "newsControllerOfAdmin")
public class NewsController {

	@Autowired
	private INewsService newsService;

	@RequestMapping(value = "/admin/news", method = RequestMethod.GET)
	public ModelAndView newsPage() {
		ModelAndView mav = new ModelAndView("admin/news");
		NewsDTO news = new NewsDTO();
		mav.addObject(news);
		return mav;
	}
	
	@RequestMapping(value = "/admin/draft/news", method = RequestMethod.GET)
	public ModelAndView draftNewsPage() {
		ModelAndView mav = new ModelAndView("admin/drafts-news");
		NewsDTO news = new NewsDTO();
		mav.addObject(news);
		return mav;
	}

	@RequestMapping(value = "/admin/news/edit", method = RequestMethod.GET)
	public ModelAndView newsEditPage(@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/news-edit2");
		NewsDTO news = new NewsDTO();

		if (id != null) {
			news = newsService.findByID(id);

		}

		if (request.getParameter("msg") != null) {
			if (request.getParameter("msg").equals("add_success"))
				mav.addObject("msg", MessageUtil.SUCCESS_ADD);
			else if (request.getParameter("msg").equals("success_update")) {
				mav.addObject("msg", MessageUtil.SUCCESS_UPDATE);
			} else if (request.getParameter("msg").equals("error_update")) {
				mav.addObject("msg", MessageUtil.ERROR_UPDATE);
			} else
				mav.addObject("msg", MessageUtil.ERROR_ADD);

		}
		Map<Integer, String> mapAction = setListActionForStaff();
		if (SecurityUtils.getAuthorities().contains(SystemConstant.ROLE_ADMIN)) {
			mapAction = setListActionForAdmin();
		}


		mav.addObject("mapAction", mapAction);

		mav.addObject("news", news);
		return mav;
	}
	private Map<Integer, String> setListActionForStaff() {
		Map<Integer, String> mapAction = new HashMap<>();

		mapAction.put(SystemConstant.DRAFT, "Draft");

		return mapAction;

	}
	private Map<Integer, String> setListActionForAdmin() {
		Map<Integer, String> mapAction = new HashMap<>();
		mapAction.put(SystemConstant.SCHEDULED, "Schedule");
		mapAction.put(SystemConstant.DRAFT, "Draft");
		mapAction.put(SystemConstant.PUBLIC, "Publish");

		return mapAction;

	}


}
