package com.bank.OurBank.controller;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.bank.OurBank.model.AccountDetails;

import com.bank.OurBank.model.FD;

import com.bank.OurBank.services.AccountDetailsRepoImp;
import com.bank.OurBank.services.FDRepoImp;
import com.bank.OurBank.services.UserRepoImp;
import com.bank.OurBank.vo.FdVO;
import com.bank.OurBank.vo.UserVO;

@RestController
@CrossOrigin(origins = "*")
public class UserController  {
	

	
	@Autowired
	FDRepoImp fdRepoImp;
	@Autowired
	UserRepoImp userRepoImp;
	@Autowired
	AccountDetailsRepoImp accountDetailsRepoImp;


	//view List of accounts (change return type while error occurred)
	@GetMapping(value="/userHome/{uid}")
	public List<AccountDetails> getUser(@PathVariable long uid){
		return userRepoImp.getUser(uid);	
	}
	
	//view account details
	@GetMapping(value="/userAccountSelect")
	public AccountDetails getAccountDetails(@RequestParam long accountnumber){
		return accountDetailsRepoImp.getByAccountnumber(accountnumber);
			
	}
	
	//FD creation
	@PostMapping(value="user/{uid}/userAccount/createFd")
	public String createFD(@PathVariable long uid,@RequestParam long accountNumber,@RequestBody FdVO fdvo) throws Exception {
		return fdRepoImp.createFD(uid, accountNumber, fdvo);
	}
	
	//Fund-Transaction
	@GetMapping(value="user/{uid}/userAccount/reciversAccount/transferFund")
	public String transferFund(@RequestParam("amount") long amount,@PathVariable long uid,@RequestParam long accountnumber,@RequestParam long reciversAccountNumber) {
		return accountDetailsRepoImp.transferFund(amount,uid,accountnumber,reciversAccountNumber);
	}
	
	//view List of FD (change return type)
	@GetMapping(value="/userHome/FDs")
	public List<FD> myAllFD(@RequestParam long accountnumber) {
		return accountDetailsRepoImp.myAllFD(accountnumber);
	}
	
	//delete FD
	@DeleteMapping(value="/user/{uid}/closeFD/{fdNumber}")
	public String closeFd(@PathVariable long uid,@PathVariable long fdNumber){
		return fdRepoImp.closeFd(uid,fdNumber);
		
	}
	
	//edit user details
	@PatchMapping(value="/user/{uid}/updateProfile")
	public String editUserDetails(@PathVariable long uid,@RequestBody UserVO uservo) {
		return userRepoImp.editUserDetails(uid, uservo);
	}
}
