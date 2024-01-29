package com.fastfood.controller.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.dto.NewsDTO;
import com.fastfood.service.INewsService;

@Controller
public class NewsController {
	@Autowired
	private INewsService newsService;

	@RequestMapping(value = "/news/{slug}", method = RequestMethod.GET)
	public ModelAndView newsPageDetail(@PathVariable String slug) {
		NewsDTO newsDTO = newsService.findBySlug(slug);

		ModelAndView mav = new ModelAndView("/web/news-detail");
		List<NewsDTO> listNews = new ArrayList<NewsDTO>();
		listNews.add(newsDTO);
		mav.addObject("news", listNews);

		return mav;
	}

	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public ModelAndView newsPage() {

		ModelAndView mav = new ModelAndView("/web/news");

		return mav;
	}

}
