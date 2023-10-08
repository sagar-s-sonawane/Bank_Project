package com.bank.OurBank.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bank.OurBank.dao.AccountDetailsRepository;
import com.bank.OurBank.dao.UserRepository;
import com.bank.OurBank.model.AccountDetails;
import com.bank.OurBank.model.FD;
import com.bank.OurBank.model.User;

@Service
public class AccountDetailsRepoImp {

	@Autowired
	AccountDetailsRepository accountDetailsRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserRepoImp userRepoImp;
	
	public AccountDetails getById(long id) {
		return accountDetailsRepository.findById(id);
	}
	public AccountDetails getByAccountnumber(long accountnumber) {
		return accountDetailsRepository.findByAccountnumber(accountnumber);
	}
	public String deleteByAccountnumber(long accountnumber) {
		AccountDetails accountDetails=accountDetailsRepository.findByAccountnumber(accountnumber);;
		System.out.println(accountDetails.getUid());
		accountDetailsRepository.deleteById(accountDetails.getUid());
		return "bank account deleted";
	}
	
	
	public List<FD> myAllFD(long accountnumber) {
		try {
		AccountDetails account=getByAccountnumber(accountnumber);
		List<FD>list=new ArrayList<>();
		list=account.getFd();
		return list;
		}catch(Exception e) {return new ArrayList<>();}
	}
	
	@Transactional
	public String transferFund(long amount,long uid,long sendersAccountnumber,long reciversAccountNumber) {
		try {
		User user=userRepoImp.getById(uid);
		user.setIsLogin(true);
		if(user.getIsLogin()) {
		List<AccountDetails>list=new ArrayList<>();
		list=user.getList();
			for(AccountDetails obj:list) {
				if(obj.getAccountnumber()==sendersAccountnumber) {
					AccountDetails sendersAccount=getByAccountnumber(sendersAccountnumber);
					long balance=sendersAccount.getBalance();
					if(balance>=amount) {
						try {
						AccountDetails receiversAccount=getByAccountnumber(reciversAccountNumber);
						receiversAccount.receiveFund(amount);
						sendersAccount.sendFund(amount);
						accountDetailsRepository.save(sendersAccount);
						accountDetailsRepository.save(receiversAccount);
						user.setIsLogin(false);
						return "Transaction succesful";
						}catch(Exception e) {return "Invalid reciever's account number";}
					}
					else {
						user.setIsLogin(false);
						return "Insuficient balance"; 
					}
			
				}
				else {
					user.setIsLogin(false);
					return "Invalid Account Number";
				}
			}
		}
		else {
			user.setIsLogin(false);
			return "Login first then try again";
		}
		}
		catch(Exception e) {
			return "Invalid user";
		}
		return "Internal server error";
		
		
	}
		
}
