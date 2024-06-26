package com.fastfood.api.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastfood.dto.ItemDTO;
import com.fastfood.dto.OrderDTO;
import com.fastfood.dto.ProductDTO;
import com.fastfood.service.IProductService;

@RestController(value = "cartAPI")
public class CartAPI {
	@Autowired
	private IProductService productService;

	@RequestMapping(value = "/api/cart", method = RequestMethod.POST)
	public OrderDTO addToCart(@RequestParam(name = "idP") long idP,
			@RequestParam(name = "quantity", required = false, defaultValue = "1") int quantity,
			HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		OrderDTO orderSession = new OrderDTO();
		ProductDTO product = productService.findById(idP);
		ItemDTO item = new ItemDTO();
		if (session.getAttribute("cart") == null) {
			session.setAttribute("cart", orderSession);
		}
		orderSession = (OrderDTO) session.getAttribute("cart");
		boolean check = false;
		List<ItemDTO> listItem = orderSession.getItems();
		if (listItem.size() == 0) {
			listItem = new ArrayList<>();
			item.setQuantity(quantity);
			double currentPrice = product.getPrice();
			if (product.getSalePrice() != 0 && product.getSalePrice() < product.getPrice()) {
				currentPrice = product.getSalePrice();
			}
			item.setPrice(currentPrice);
			item.setProductDTO(product);
			listItem.add(item);

		} else {
			for (ItemDTO itemDTO : listItem) {

				if (itemDTO.getProductDTO().getId().equals(product.getId())) {
					itemDTO.setQuantity(itemDTO.getQuantity() + quantity);

					check = true;
				}

			}
			if (check == false) {
				item.setQuantity(quantity);
				double currentPrice = product.getPrice();
				if (product.getSalePrice() != 0 && product.getSalePrice() < product.getPrice()) {
					currentPrice = product.getSalePrice();
				}
				item.setPrice(currentPrice);
				item.setProductDTO(product);
				listItem.add(item);

			}

		}

		orderSession.setItems(listItem);

		orderSession.setTotalPay(calculateTheTotal(listItem));

		return orderSession;
	}

	@RequestMapping(value = "/api/cart", method = RequestMethod.GET)
	public OrderDTO viewCart(@RequestParam(name = "idP", required = false) Long idP,
			@RequestParam(name = "mode", required = false) String mode, HttpServletRequest request,
			HttpSession session) {
		session = request.getSession();
		OrderDTO orderSession = new OrderDTO();
		ItemDTO item = new ItemDTO();
		if (session.getAttribute("cart") == null) {
			session.setAttribute("cart", orderSession);
		}
		orderSession = (OrderDTO) session.getAttribute("cart");
		List<ItemDTO> listItem = orderSession.getItems();
		if (mode != null) {
			doFunctionByMode(listItem, idP, mode);
		}
		

		orderSession.setItems(listItem);
		orderSession.setTotalPay(calculateTheTotal(listItem));

		return orderSession;
	}

	private void doFunctionByMode(List<ItemDTO> listItem, Long idP, String mode) {
		for (ItemDTO obj : listItem) {
			if (obj.getProductDTO().getId() == idP.longValue() && mode.equals("plusQuantity")) {
				obj.setQuantity(obj.getQuantity() + 1);
				break;
			} else if (obj.getProductDTO().getId() == idP.longValue() && mode.equals("minusQuantity")
					&& obj.getQuantity() > 0) {
				int quantity = obj.getQuantity() - 1;
				if (quantity == 0)
					listItem.remove(obj);
				else
					obj.setQuantity(quantity);
				break;
			} else if (obj.getProductDTO().getId() == idP.longValue() && mode.equals("delete")) {
				listItem.remove(obj);
				break;
			}
		}
	}

	private Double calculateTheTotal(List<ItemDTO> listItem) {
		if (listItem != null) {
			double total = 0;
			for (ItemDTO obj : listItem) {

				total += obj.getPrice() * obj.getQuantity();
			}
			return total;
		}
		return (double) 0;

	}

}
