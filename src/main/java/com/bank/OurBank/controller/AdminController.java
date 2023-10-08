package com.bank.OurBank.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.OurBank.dao.AccountDetailsRepository;
import com.bank.OurBank.dao.AdminRepository;
import com.bank.OurBank.dao.UserRepository;
import com.bank.OurBank.model.AccountDetails;

import com.bank.OurBank.model.User;
import com.bank.OurBank.services.AccountDetailsRepoImp;
import com.bank.OurBank.services.AdminRepoImp;
import com.bank.OurBank.services.UserRepoImp;
import com.bank.OurBank.vo.AccountDetailsVO;
import com.bank.OurBank.vo.AdminVO;
import com.bank.OurBank.vo.UserVO;

@RestController
//@CrossOrigin(origins = "*")
public class AdminController {

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AdminRepoImp adminRepoImp;
	@Autowired
	UserRepoImp userRepoImp;
	@Autowired
	AccountDetailsRepository accountDetailsRepository;
	@Autowired
	AccountDetailsRepoImp accountDetailsRepoImp;

	// view user details by id 
	@GetMapping(value = "/admin/{aid}/user/{uid}")
	public User getUser(@RequestParam String adminName, @RequestParam String password, @PathVariable long uid,
			@PathVariable long aid) throws EntityNotFoundException {
		return adminRepoImp.getUser(adminName, password, uid, aid);
	}

	// view List of all users
	@GetMapping(value = "/admin/users")
	public List<User> getAllUsers() {
		return adminRepoImp.getAllUser();
	}

	// view userAccount details by id
	@GetMapping(value = "/admin/{aid}/userAccountDetails/{accountNumber}")
	public AccountDetails getUserAccountDetails(@RequestParam String adminName, @RequestParam String password,
			@PathVariable long accountNumber, @PathVariable long aid) {
		return adminRepoImp.getAccountDetails(adminName, password, accountNumber, aid);
	}

	// same method as above
	@GetMapping(value = "/admin/userAccountDetails/{accountNumber}")
	public AccountDetails getUserAccountDetails1(@PathVariable long accountNumber) {
		return adminRepoImp.getAccountDetails1(accountNumber);
	}

	// register user in bank
	@PostMapping(value = "/admin/{aid}/registerUser")
	public String registerUser(@PathVariable long aid, @RequestBody UserVO uservo) {
		String adminName = "sagar"; 
		String password = "sagar123";
		return adminRepoImp.registerUser(adminName, password, aid, uservo);
	}

	// create user's account in bank
	@PostMapping(value = "/admin/{aid}/user/{uid}/createUserAccount")
	public String createaccount(@RequestParam String adminName, @RequestParam String password, @PathVariable long uid,
			@RequestBody AccountDetailsVO accountDetailsvo, @PathVariable long aid) {
		return adminRepoImp.createAccount(adminName, password, uid, accountDetailsvo, aid);
	}

	// delete account of user

	@DeleteMapping(value = "/admin/{aid}/deleteUserAccount")
	public String deleteAccountDetails(@RequestParam String adminName, @RequestParam String password,
			@RequestParam long accountNumber, @PathVariable long aid) {
		return adminRepoImp.deleteAccountDetails(adminName, password, accountNumber, aid);
	}

	// delete user
	@DeleteMapping(value = "/admin/{aid}/deleteUser/{uid}")
	public String deleteUser(@RequestParam String adminName, @RequestParam String password, @PathVariable long uid,
			@PathVariable long aid) {
		return adminRepoImp.deleteUser(adminName, password, uid, aid);
	}

	@PutMapping(value = "/adminLogin")
	public boolean adminLogin(@RequestBody AdminVO adminvo) {
		return adminRepoImp.isAdminLogin(adminvo);
	}

}
