package com.bank.OurBank.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bank.OurBank.dao.UserRepository;
import com.bank.OurBank.model.AccountDetails;
import com.bank.OurBank.model.User;
import com.bank.OurBank.vo.UserVO;

@Service

public class UserRepoImp {

	@Autowired
	UserRepository userRepository;
	
	public User getById(long id) {
		return userRepository.findById(id);
	}
	
	public List<AccountDetails> getUser( long uid){
		try {
		User user=getById(uid);
		user.setIsLogin(true);
		if(user.getIsLogin()) {
		List<AccountDetails>list=new ArrayList<>();
		list=user.getList();
		user.setIsLogin(false);
		return list;
		}
		else {
			user.setIsLogin(false);
			return new ArrayList<>();
		}
		}catch(Exception e) { return new ArrayList<AccountDetails>();}
	}
	
	@Transactional
	public String editUserDetails( long uid,UserVO uservo){
		try {
		User user=userRepository.getById(uid);
		System.out.println(uservo);
		user.setIsLogin(true);
		if(user.getIsLogin()) {
		if(!uservo.getAddress().equals("") || uservo.getAddress()==null) {
			user.setAddress(uservo.getAddress());  
			}
		if(!uservo.getEmail().equals("") || uservo.getEmail()==null) {
			user.setEmail(uservo.getEmail());  
			}
		if(!uservo.getFirstname().equals("") || uservo.getFirstname()==null) {
				user.setFirstname(uservo.getFirstname());  
		}
		if(!uservo.getLastname().equals("") || uservo.getLastname()==null) {
			user.setLastname(uservo.getLastname());  
			}
		if(uservo.getMobilenumber()!=0) {
			user.setMobilenumber(uservo.getMobilenumber());
			}
		
		
		user.setIsLogin(false);
		System.out.println(user);
		userRepository.save(user);
		return "profile updated";
		}
		else {
			user.setIsLogin(false);
			return "Login first then try again";
		}
		}
	catch(Exception e) {return "User id is not valid";}
	
	}
	
	
}
