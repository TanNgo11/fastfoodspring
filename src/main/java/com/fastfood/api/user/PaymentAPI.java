package com.fastfood.api.user;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.AccountDTO;
import com.fastfood.dto.OrderDTO;
import com.fastfood.entity.AccountEntity;
import com.fastfood.entity.PaymentDetail;
import com.fastfood.mapper.AccountMapper;

import com.fastfood.repository.UserRepository;
import com.fastfood.service.IOrderService;
import com.fastfood.service.IVNPayService;
import com.fastfood.service.impl.OrderService;
import com.fastfood.utils.MessageUtil;
import com.fastfood.utils.SecurityUtils;
import com.restfb.types.Payment;

@RestController
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

	@PostMapping("/neworder")
	public String createPayemnt(@RequestParam("amount") int orderTotal, HttpServletRequest request)
			throws UnsupportedEncodingException {

		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		String vnpayUrl = VNPayService.createOrder(orderTotal, baseUrl);
		return vnpayUrl;

	}

	@GetMapping("/vnpay-payment")
	public ModelAndView GetMapping(HttpServletRequest request, HttpSession session) {
		int paymentStatus = VNPayService.orderReturn(request);

		String orderInfo = request.getParameter("vnp_OrderInfo");
		String paymentTime = request.getParameter("vnp_PayDate");
		String transactionId = request.getParameter("vnp_TransactionNo");
		String totalPrice = request.getParameter("vnp_Amount");

		PaymentDetail paymentDetail = PaymentDetail.builder().orderInfo(orderInfo).totalPrice(totalPrice)
				.transactionId(transactionId).paymentTime(paymentTime).build();

		ModelAndView mav = new ModelAndView();

		AccountEntity userEntity = userRepository.findOneByUserNameAndStatus(SecurityUtils.getPrincipal().getUsername(),
				SystemConstant.ACTIVE_STATUS);
		AccountDTO account = accountMapper.mapToDTO(userEntity);

		session = request.getSession();
		OrderDTO orderSession = (OrderDTO) session.getAttribute("cart");

		if (orderSession == null) {
			orderSession = new OrderDTO();

		}
System.out.println(account.toString());
		// check login and empty cart
		if (account.getUsername() == null) {
			request.setAttribute("msg", MessageUtil.ERROR_LOGIN);
		}
		if (orderSession.getItems().size() == 0) {
			request.setAttribute("msg", MessageUtil.ERROR_EMPTYCART);
		} else {
			orderSession.setAccountDTO(account);
			orderSession.setEmail(account.getEmail());
			orderSession.setAddress(account.getAddress());
			orderSession.setPhonenumber(account.getPhoneNumber());
			orderSession.setCustomerName(account.getFullName());
			request.setAttribute("msg", MessageUtil.SUCCESS_ORDER);
			orderService.save(orderSession, paymentDetail);
		}

		session.removeAttribute("cart");

		mav.setViewName("web/cart");
		System.out.println(paymentStatus);

		return mav;

//		return paymentStatus == 1 ? "ordersuccess" : "orderfail";
	}

}
