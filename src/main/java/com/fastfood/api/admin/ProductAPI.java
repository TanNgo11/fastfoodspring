package com.fastfood.api.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.CategoryDTO;
import com.fastfood.dto.ImageDTO;
import com.fastfood.dto.ProductDTO;
import com.fastfood.repository.ProductRepository;
import com.fastfood.service.ICategoryService;
import com.fastfood.service.IImageService;
import com.fastfood.service.IProductService;
import com.fastfood.service.impl.CategoryService;
import com.fastfood.utils.FileUploadUtil;

@RestController(value = "productAPIOfAdmin")
@RequestMapping("/admin/api/v1/")
public class ProductAPI {

	@Autowired
	private IProductService productService;

	@Autowired
	private FileUploadUtil fileUploadUtil;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IImageService imageService;

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/products")
	public ResponseEntity<ProductDTO> getAllProducts(@RequestParam("page") int page, @RequestParam("limit") int limit) {

		ProductDTO result = new ProductDTO();
		result.setPage(page);
		result.setLimit(limit);

		Pageable pageable = new PageRequest(page - 1, limit);
		result.setListResult(productService.findAllByStatus(pageable, SystemConstant.ACTIVE_STATUS));
		result.setTotalItem(productService.getTotalItem());
		result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getLimit()));

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/draft/products")
	public ResponseEntity<ProductDTO> getAllDraftProducts(@RequestParam("page") int page,
			@RequestParam("limit") int limit) {

		ProductDTO result = new ProductDTO();
		result.setPage(page);
		result.setLimit(limit);

		Page<ProductDTO> pageResult = productService.findDraftAndScheduledProducts(page, limit);
		result.setListResult(pageResult.getContent());
		result.setTotalItem((int) pageResult.getTotalElements());
		result.setTotalPage(pageResult.getTotalPages());

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/products/{productId}")
	@Transactional
	public ResponseEntity<ApiResponse> deleteProductById(@PathVariable("productId") long productId) {

		ApiResponse response = productService.softDelete(productId);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping("/products")
	public ResponseEntity<ProductDTO> addNewProduct(@RequestParam("files") MultipartFile[] files,

			HttpServletRequest request, @RequestParam("productName") String productName,
			@RequestParam("price") double price, @RequestParam("salePrice") double salePrice,
			@RequestParam("description") String description, @RequestParam("category") String category,
			@RequestParam("inStock") int inStock, @RequestParam("relatedProductsId") List<Long> relatedProductsId,
			@RequestParam("status") int status,
			@RequestParam(name = "publishDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date publishDate) {

		ProductDTO result = new ProductDTO();
		CategoryDTO cateDTO = categoryService.findByType(category);

		result.setProductName(productName);
		result.setCategoryDTO(cateDTO);
		result.setPrice(price);
		result.setSalePrice(salePrice);
		result.setDescription(description);
		result.setInStock(inStock);
		result.setStatus(status);
		result.setPublishDate(publishDate);

		if (relatedProductsId != null) {
			List<ProductDTO> listRelatedProduct = new ArrayList<>();
			for (Long long1 : relatedProductsId) {
				ProductDTO product = productService.findById(long1);

				listRelatedProduct.add(product);
			}
			result.setRelatedProducts(listRelatedProduct);

		}

		result = productService.save(result);

		if (result != null) {

			List<String> imgs = fileUploadUtil.saveProductFiles(files, request, result.getId());
			List<ImageDTO> listImageDTO = new ArrayList<ImageDTO>();

			for (String imgURL : imgs) {
				ImageDTO imageDTO = new ImageDTO();
				imageDTO.setImageURL(imgURL);
				listImageDTO.add(imageDTO);
			}

			result.setListImage(listImageDTO);
			productService.save(result);

		}

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/products/product/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@RequestParam(required = false) MultipartFile[] files,
			HttpServletRequest request, @RequestParam("productName") String productName,
			@RequestParam("price") double price, @RequestParam("salePrice") double salePrice,
			@RequestParam("description") String description, @RequestParam("category") String category,
			@RequestParam("inStock") int inStock, @PathVariable long id, @RequestParam("status") int status,
			@RequestParam(name = "publishDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date publishDate) {

		ProductDTO result = productService.findById(id);
		CategoryDTO cateDTO = categoryService.findByType(category);
		result.setProductName(productName);
		result.setCategoryDTO(cateDTO);
		result.setPrice(price);
		result.setInStock(inStock);
		result.setSalePrice(salePrice);
		result.setDescription(description);
		result.setStatus(status);
		result.setPublishDate(publishDate);

		if (files != null && files.length > 0 && !files[0].isEmpty()) {
			fileUploadUtil.removeExistingFiles(request, result.getListImage());
			result.getListImage().forEach(image -> imageService.deleteById(image.getId()));
			List<String> imgs = fileUploadUtil.saveProductFiles(files, request, result.getId());

			if (!imgs.isEmpty()) {
				List<ImageDTO> listImageDTO = new ArrayList<ImageDTO>();
				for (String imgURL : imgs) {
					ImageDTO imageDTO = new ImageDTO();
					imageDTO.setImageURL(imgURL);
					listImageDTO.add(imageDTO);
				}
				result.setListImage(listImageDTO);

			}

		}

		productService.save(result);

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}
