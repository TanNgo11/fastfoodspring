package com.fastfood.api.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.CategoryDTO;
import com.fastfood.dto.ProductDTO;
import com.fastfood.service.IProductService;
import com.fastfood.service.impl.CategoryService;
import com.fastfood.utils.FileUploadUtil;

@RestController(value = "productAPIOfAdmin")
@RequestMapping("admin/api/v1/products")
public class ProductAPI {

	@Autowired
	IProductService productService;

	@Autowired
	FileUploadUtil fileUploadUtil;
	
	@Autowired
	CategoryService CategoryService;

	@GetMapping
	public ResponseEntity<ProductDTO> getAllProducts(@RequestParam("page") int page, @RequestParam("limit") int limit) {

		ProductDTO result = new ProductDTO();
		result.setPage(page);
		result.setLimit(limit);

		Pageable pageable = new PageRequest(page - 1, limit);
		result.setListResult(productService.findAllByStatus(pageable, SystemConstant.ACTIVE_STATUS));
		result.setTotalItem(productService.getTotalItem());
		result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getLimit()));
		result.splitImg();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{productId}")
	@Transactional
	public ResponseEntity<ApiResponse> deleteProductById(@PathVariable("productId") long productId) {

		ApiResponse response = productService.softDelete(productId);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping()
	public ResponseEntity<ProductDTO> addNewProduct(@RequestParam("files") MultipartFile[] files,
			HttpServletRequest request, @RequestParam("productName") String name) {
		ApiResponse response = fileUploadUtil.saveFiles(files, request, 100);

		if (!response.getSuccess()) {
			return null;
		}
		ProductDTO dto = new ProductDTO();
		CategoryDTO cate = CategoryService.findAll().get(0);
		dto.setProductName(name);
		dto.setPrice(10);
		dto.setSalePrice(20);
		dto.setCategoryDTO(cate);
		ProductDTO result = productService.save(dto);

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}
