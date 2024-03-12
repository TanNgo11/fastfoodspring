package com.fastfood.api.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.AccountDTO;
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.TopSpenderDTO;
import com.fastfood.service.IAccountService;
import com.fastfood.service.IOrderService;
import com.fastfood.utils.SecurityUtils;

@RestController(value = "userAPIofAdmin")
@RequestMapping("/admin/api/v1")
public class UserAPI {

	@Autowired
	private IAccountService accountService;

	@Autowired
	private IOrderService orderService;

	@PostMapping("/user")
	public AccountDTO createUser(@RequestBody AccountDTO accountDTO) {
		return accountDTO;
	}

	@GetMapping("/users")
	public ResponseEntity<AccountDTO> showAllUsers(
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setPage(page);
		accountDTO.setLimit(limit);
		Sort sort = new Sort(Sort.Direction.DESC, "createdDate");

		Pageable pageable = new PageRequest(page - 1, limit, sort);
		Page<AccountDTO> pageResult = accountService.findAllByRole(SystemConstant.ROLE_USER, pageable);
		accountDTO.setListResult(pageResult.getContent());
		accountDTO.setTotalPage(pageResult.getTotalPages());

		return new ResponseEntity<>(accountDTO, HttpStatus.OK);
	}

	@GetMapping("/users/top-spenders")
	public ResponseEntity<List<TopSpenderDTO>> showTop10UsersSpender() {

		return new ResponseEntity<>(orderService.findTopBuyers(), HttpStatus.OK);
	}

	@GetMapping("/staffs")
	public ResponseEntity<AccountDTO> showAllStaffs(
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setPage(page);
		accountDTO.setLimit(limit);
		Sort sort = new Sort(Sort.Direction.DESC, "createdDate");

		Pageable pageable = new PageRequest(page - 1, limit, sort);
		Page<AccountDTO> pageResultStaff = accountService.findAllByRole(SystemConstant.ROLE_STAFF, pageable);
		pageResultStaff.forEach(t->t.setRoleName(SystemConstant.ROLE_STAFF));
		Page<AccountDTO> pageResultAdmin = accountService.findAllByRole(SystemConstant.ROLE_ADMIN, pageable);
		pageResultAdmin.forEach(t->t.setRoleName(SystemConstant.ROLE_ADMIN));
		List<AccountDTO> combinedResults = new ArrayList<>();
	    combinedResults.addAll(pageResultStaff.getContent());
	    combinedResults.addAll(pageResultAdmin.getContent());
	    
	   

	    accountDTO.setListResult(combinedResults);

	    
	    accountDTO.setTotalPage((int) Math.ceil(combinedResults.size() / (double) limit));
	    accountDTO.setTotalItem(combinedResults.size());

		

		return new ResponseEntity<>(accountDTO, HttpStatus.OK);
	}

	@PutMapping("/users")
	public ResponseEntity<AccountDTO> modifyUser(@RequestBody AccountDTO accountDTO) {

		return new ResponseEntity<>(accountService.update(accountDTO), HttpStatus.OK);
	}

	@PutMapping("/staffs")
	public ResponseEntity<?> modifyStaff(@RequestBody AccountDTO accountDTO) {

		return new ResponseEntity<>(accountService.update(accountDTO), HttpStatus.OK);
	}
	
	
	@PostMapping("/staffs")
	public AccountDTO createStaffOrAdmin(@RequestBody AccountDTO accountDTO) {
		return accountService.saveAccountStaffOrAdmin(accountDTO);
	}

}
