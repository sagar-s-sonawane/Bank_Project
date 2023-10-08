package com.bank.OurBank.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bank.OurBank.dao.AccountDetailsRepository;
import com.bank.OurBank.dao.FDRepository;
import com.bank.OurBank.dao.UserRepository;
import com.bank.OurBank.model.AccountDetails;
import com.bank.OurBank.model.FD;
import com.bank.OurBank.model.User;
import com.bank.OurBank.vo.FdVO;

@Service

public class FDRepoImp {

	@Autowired
	FDRepository fdRepository;
	@Autowired
	UserRepoImp userRepoImp;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountDetailsRepoImp accountDetailsRepoImp;
	@Autowired
	AccountDetailsRepository accountDetailsRepository;
	
	@Transactional
	public String closeFd( long uid, long fdNumber){
		try{
			User user=userRepoImp.getById(uid);
			user.setIsLogin(true);
			if(user.getIsLogin()) {
				List<AccountDetails>listAccount=new ArrayList<>();
				listAccount=user.getList();
				for(AccountDetails account:listAccount) {
					List<FD>listFD=new ArrayList<>();
					listFD=account.getFd();
					for(FD fd:listFD) {
						System.out.println(fd.getFdid()+" "+fdNumber);
						if(fd.getFdid()==fdNumber) {
							account.receiveFund(fd.getAmount());
							fdRepository.delete(fd);
							listFD.remove(fd);
							user.setIsLogin(false);
							return "FD closed";
						}
					}
				}
			}
			else {
				user.setIsLogin(false);
				return "FD is not closed yet,Try again later";
			}
		user.setIsLogin(false);
		return "Invalid FD number";
		}
		catch(Exception e) {return "User is not valid to create FD";}
	}
	
	public String createFD(long uid,long accountNumber,FdVO fdvo) throws Exception {
		try {
			User user=userRepository.getById(uid);
			user.setIsLogin(true);
			if(user.getIsLogin()) {
				List<AccountDetails>listAccount=new ArrayList<>();
				listAccount=user.getList();
				for(AccountDetails account:listAccount) {
					if(account.getAccountnumber()==accountNumber) {
						if(fdvo.getAmount()<=account.getBalance()) {
							FD userFd = new FD();
							userFd.setDurationmonth(fdvo.getDurationmonth());
							userFd.setAccountBalance(account.getBalance());
							userFd.setTotalInterest();
							userFd.setAmount(fdvo.getAmount());
							long balance=account.getBalance()-userFd.getAmount();
							account.setBalance(balance);
							userFd.setAccountDetails(account);
							accountDetailsRepository.save(account);
							try {
							fdRepository.save(userFd);
							user.setIsLogin(false);
							return "FD successfully created";
							}catch(Exception e) {user.setIsLogin(false); return "FD not created by some server error"; }
						}
						else {
							user.setIsLogin(false);
							return "Insuficent balance in your account tp create this FD";
						}
					} 
				}
				user.setIsLogin(false);
				return "Account number is not valid";
							
			}
			else {
				user.setIsLogin(false);
				return "Login first then try again";
			}
			
		}
		catch(Exception e) {
			return "Invalid user";
		}	
	}
}
