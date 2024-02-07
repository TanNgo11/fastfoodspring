package com.fastfood.api.user;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.AccountDTO;
import com.fastfood.dto.ItemDTO;
import com.fastfood.dto.OrderDTO;
import com.fastfood.entity.AccountEntity;
import com.fastfood.entity.PaymentDetail;
import com.fastfood.mapper.AccountMapper;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.IOrderService;
import com.fastfood.service.IVNPayService;
import com.fastfood.service.impl.ProductService;
import com.fastfood.utils.MessageUtil;
import com.fastfood.utils.SecurityUtils;

@Controller
@RequestMapping("/api/v1/payment")
public class PaymentAPI {

	@Autowired
	private IVNPayService VNPayService;

	@Autowired
	private IOrderService orderService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private ProductService productService;

	@PostMapping("/neworder")
	@ResponseBody
	public String createPayemnt(@RequestParam String phonenumber, @RequestParam String email,
			@RequestParam String address, @RequestParam("amount") int orderTotal, HttpServletRequest request,
			HttpSession session) throws UnsupportedEncodingException {

		session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");

		if (userInfo == null) {
			userInfo = new UserInfo(email, address, phonenumber);

		}else {
			userInfo.phonenumber = phonenumber;
			userInfo.email = email;
			userInfo.address = address;
			
		}
		
		session.setAttribute("userInfo", userInfo);

		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		String vnpayUrl = VNPayService.createOrder(orderTotal, baseUrl);

		return vnpayUrl;

	}

	@GetMapping("/vnpay-payment")
	@Transactional
	public String GetMapping(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
		int paymentStatus = VNPayService.orderReturn(request);

		String orderInfo = request.getParameter("vnp_OrderInfo");
		String paymentTime = request.getParameter("vnp_PayDate");
		String transactionId = request.getParameter("vnp_TransactionNo");
		String totalPriceString = request.getParameter("vnp_Amount");

		session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		String phonenumber = userInfo.phonenumber;
		String email = userInfo.email;
		String address = userInfo.address;

		session.removeAttribute("userInfo");

		double totalPriceDouble = Double.parseDouble(totalPriceString) / 100;

		PaymentDetail paymentDetail = PaymentDetail.builder().orderInfo(orderInfo)
				.totalPrice(String.valueOf(totalPriceDouble)).transactionId(transactionId).paymentTime(paymentTime)
				.provider(SystemConstant.VNPAY).build();

		AccountEntity userEntity = userRepository.findOneByUserNameAndStatus(SecurityUtils.getPrincipal().getUsername(),
				SystemConstant.ACTIVE_STATUS);
		AccountDTO account = accountMapper.mapToDTO(userEntity);

		session = request.getSession();
		OrderDTO orderSession = (OrderDTO) session.getAttribute("cart");

		if (orderSession == null) {
			orderSession = new OrderDTO();

		}

		// check login and empty cart
		if (account.getUsername() == null) {
			request.setAttribute("msg", MessageUtil.ERROR_LOGIN);
		}
		if (orderSession.getItems().size() == 0) {
			request.setAttribute("msg", MessageUtil.ERROR_EMPTYCART);
		} else {
			orderSession.setAccountDTO(account);
			orderSession.setEmail(email);
			orderSession.setAddress(address);
			orderSession.setPhonenumber(phonenumber);
			orderSession.setCustomerName(account.getFullName());
			redirectAttributes.addFlashAttribute("msg", MessageUtil.SUCCESS_ORDER);
			List<ItemDTO> listItem = orderSession.getItems();
			for (ItemDTO itemDTO : listItem) {
				long productID = itemDTO.getProductDTO().getId();
				productService.decreaseStock(productID, itemDTO.getQuantity());
				itemDTO.setTotalPay(itemDTO.getQuantity() * itemDTO.getPrice());

			}
			orderSession.setItems(listItem);
			orderService.save(orderSession, paymentDetail);
		}

		session.removeAttribute("cart");

		return "redirect:/cart";

//		return paymentStatus == 1 ? "ordersuccess" : "orderfail";
	}

	public static class UserInfo {
		private String email;
		private String address;
		private String phonenumber;

		public UserInfo(String email, String address, String phonenumber) {
			this.email = email;
			this.address = address;
			this.phonenumber = phonenumber;
		}

	}

}
