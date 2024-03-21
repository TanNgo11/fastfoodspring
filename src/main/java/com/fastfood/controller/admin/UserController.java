package com.fastfood.controller.admin;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.AccountDTO;
import com.fastfood.dto.ProductDTO;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.IAccountService;
import com.fastfood.utils.MessageUtil;

@Controller(value = "userControllerOfAdmin")
public class UserController {

	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public ModelAndView userPage() {
		ModelAndView mav = new ModelAndView("admin/user");

		return mav;
	}

	@RequestMapping(value = "/admin/staffs", method = RequestMethod.GET)
	public ModelAndView staffPage() {
		ModelAndView mav = new ModelAndView("admin/staff");

		return mav;
	}

	@RequestMapping(value = "/admin/users/{id}", method = RequestMethod.GET)
	public ModelAndView profileUserPage(@PathVariable("id") long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/admin/user-edit");

		Optional<AccountDTO> user = accountService.findByIdAndRoleName(id, SystemConstant.ROLE_USER);
		if (!user.isPresent()) {
			mav.setViewName("/error");
			return mav;
		}
		mav.addObject("user", user);

		if (request.getParameter("msg") != null) {
			if (request.getParameter("msg").equals("success_change"))
				mav.addObject("msg", MessageUtil.SUCCESS_CHANGE);
			else {
				mav.addObject("msg", MessageUtil.ERROR_CHANGE);
			}
		}

		return mav;
	}

	@RequestMapping(value = "/admin/staffs/{id}", method = RequestMethod.GET)
	public ModelAndView profileStaffPage(@PathVariable("id") long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/admin/staff-edit");
		Optional<AccountDTO> user = accountService.findByIdAndRoleName(id, SystemConstant.ROLE_STAFF);
		if (userRepository.getOne(id).getRoles().get(0).getName().equals(SystemConstant.ROLE_ADMIN)) {
			user = accountService.findByIdAndRoleName(id, SystemConstant.ROLE_ADMIN);
		}

		if (!user.isPresent()) {
			mav.setViewName("/error");
			return mav;
		}
		mav.addObject("user", user);

		if (request.getParameter("msg") != null) {
			if (request.getParameter("msg").equals("success_change"))
				mav.addObject("msg", MessageUtil.SUCCESS_CHANGE);
			else {
				mav.addObject("msg", MessageUtil.ERROR_CHANGE);
			}
		}

		return mav;
	}

	@RequestMapping(value = "/admin/staffs/create", method = RequestMethod.GET)
	public ModelAndView createStaffPage(@ModelAttribute("user") AccountDTO newAccount,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/admin/staff-create");

		if (request.getParameter("msg") != null) {
			if (request.getParameter("msg").equals("success_change"))
				mav.addObject("msg", MessageUtil.SUCCESS_CHANGE);
			else {
				mav.addObject("msg", MessageUtil.ERROR_CHANGE);
			}
		}

		return mav;
	}

}
