package com.fastfood.controller.admin;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.dto.ImageDTO;
import com.fastfood.dto.ProductDTO;
import com.fastfood.service.impl.CategoryService;
import com.fastfood.service.impl.ProductService;
import com.fastfood.utils.MessageUtil;

@Controller
public class ProductController {



	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;

	

	@RequestMapping(value = "/admin/product/edit", method = RequestMethod.GET)
	public ModelAndView createPage(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/edit");
		ProductDTO product = new ProductDTO();
		if (id != null) {
			product = productService.findById(id);
			List<ImageDTO> listImg =product.getListImage();
			mav.addObject("listImg", listImg);
		}

		if (request.getParameter("msg") != null) {
			if (request.getParameter("msg").equals("add_success"))
				mav.addObject("msg", MessageUtil.SUCCESS_ADD);
			else {
				mav.addObject("msg", MessageUtil.ERROR_ADD);
			}
		}

		mav.addObject("product", product);
		mav.addObject("categories",
				categoryService.findAll().stream().map(cate -> cate.getType()).collect(Collectors.toList()));
		return mav;
	}

}
