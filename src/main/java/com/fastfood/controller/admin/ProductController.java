package com.fastfood.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.ImageDTO;
import com.fastfood.dto.ProductDTO;
import com.fastfood.service.ICategoryService;
import com.fastfood.service.IProductService;
import com.fastfood.service.impl.CategoryService;
import com.fastfood.service.impl.ProductService;
import com.fastfood.utils.MessageUtil;
import com.fastfood.utils.SecurityUtils;

@Controller
public class ProductController {

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IProductService productService;

	@RequestMapping(value = "/admin/products", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("/admin/product");

		return mav;
	}

	@RequestMapping(value = "/admin/draft/products", method = RequestMethod.GET)
	public ModelAndView draftsproductPage() {
		ModelAndView mav = new ModelAndView("/admin/drafts-product");

		return mav;
	}

	@RequestMapping(value = "/admin/product/edit", method = RequestMethod.GET)
	public ModelAndView createPage(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) throws JsonProcessingException {
		ModelAndView mav = new ModelAndView("admin/edit");
		ProductDTO product = new ProductDTO();
		if (id != null) {
			product = productService.findById(id);
			List<ImageDTO> listImg = product.getListImage();
			mav.addObject("listImg", listImg);
			
		}
		List<ProductDTO> publishProduct = productService.findAllByStatus(SystemConstant.ACTIVE_STATUS);
		Map<Long, String> mapRelatedProducts = new HashMap<>();

		for (ProductDTO productDTO : publishProduct) {
			mapRelatedProducts.put(productDTO.getId(), productDTO.getProductName());
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
		mav.addObject("mapProduct", mapRelatedProducts);
		mav.addObject("product", product);
		
	

		mav.addObject("categories",
				categoryService.findAll().stream().map(cate -> cate.getType()).collect(Collectors.toList()));
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
