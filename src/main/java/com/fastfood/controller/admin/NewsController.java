package com.fastfood.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.dto.ImageDTO;
import com.fastfood.dto.NewsDTO;
import com.fastfood.service.INewsService;
import com.fastfood.service.impl.NewsService;
import com.fastfood.utils.MessageUtil;

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
	
	@RequestMapping(value = "/admin/news/edit", method = RequestMethod.GET)
	public ModelAndView newsEditPage(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/news-edit");
		NewsDTO news = new NewsDTO();
		
		if (id != null) {
			news = newsService.findByID(id);
			
		}
		
		if (request.getParameter("msg") != null) {
			if (request.getParameter("msg").equals("add_success"))
				mav.addObject("msg", MessageUtil.SUCCESS_ADD);
			else {
				mav.addObject("msg", MessageUtil.ERROR_ADD);
			}
		}
		
		
		
		mav.addObject("news", news);
		return mav;
	}

}
