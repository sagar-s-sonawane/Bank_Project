package com.bank.OurBank.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bank.OurBank.dao.AccountDetailsRepository;
import com.bank.OurBank.dao.AdminRepository;
import com.bank.OurBank.dao.UserRepository;
import com.bank.OurBank.model.AccountDetails;
import com.bank.OurBank.model.Admin;
import com.bank.OurBank.model.User;
import com.bank.OurBank.vo.AccountDetailsVO;
import com.bank.OurBank.vo.AdminVO;
import com.bank.OurBank.vo.UserVO;

@Service

public class AdminRepoImp {

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountDetailsRepoImp accountDetailsRepoImp;
	@Autowired
	AccountDetailsRepository accountDetailsRepository;
	@Autowired
	UserRepoImp userRepoImp;

	public Admin getById(long id) {
		return adminRepository.findById(id);
	}

	public Admin getByAdminname(String adminName) {
		return adminRepository.findByAdminname(adminName);
	}

	public boolean isAdminLogin(AdminVO adminvo) {
		try {
			Admin admin = getByAdminname(adminvo.getAdminname());
			System.out.println(admin);
			if (adminvo.getPassword().equals(admin.getPassword())
					&& adminvo.getAdminname().equals(admin.getAdminname())) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isAdminLogin(long aid, String adminName, String password) {
		try {
			Admin admin = getById(aid);
			if (password.equals(admin.getPassword()) && adminName.equals(admin.getAdminname())) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	// change return null to message
	public AccountDetails getAccountDetails(String adminName, String password, long accountNumber, long aid) {
		if (isAdminLogin(aid, adminName, password)) {
			try {
				AccountDetails accountDetails = accountDetailsRepoImp.getByAccountnumber(accountNumber);
				if (accountDetails.getAccountnumber() == 0) {
					return new AccountDetails("Account number is worng");
				} else {
					return accountDetails;
				}
			} catch (Exception e) {
				return new AccountDetails("Account number is worng");
			}
		} else {
			return new AccountDetails("Admin is not valid");
		}
	}

	// same as above method
	public AccountDetails getAccountDetails1(long accountNumber) {

		try {
			AccountDetails accountDetails = accountDetailsRepoImp.getByAccountnumber(accountNumber);
			if (accountDetails.getAccountnumber() == 0) {
				return new AccountDetails("Account number is worng");
			} else {
				return accountDetails;
			}
		} catch (Exception e) {
			return new AccountDetails("Account number is worng");
		}

	}

	public String registerUser(String adminName, String password, long aid, UserVO uservo) {

		if (isAdminLogin(aid, adminName, password)) {
			User user = new User();
			if (!(uservo.getFirstname().equals("")) && uservo.getFirstname() != null) {
				user.setFirstname(uservo.getFirstname());
			} else {
				return "Fill the data";
			}
			if (!(uservo.getLastname().equals("")) && uservo.getLastname() != null) {
				user.setLastname(uservo.getLastname());
			} else {
				return "Fill the data";
			}
			user.setAadharnumber(uservo.getAadharnumber());
			if (!(uservo.getAddress().equals("")) && uservo.getAddress() != null) {
				user.setAddress(uservo.getAddress());
			} else {
				return "Fill the data";
			}
			user.setMobilenumber(uservo.getMobilenumber());
			user.setEmail(uservo.getEmail());
			try {
				System.out.println(user);
				userRepository.save(user);
				return "user registered";
			} catch (Exception e) {
				return "user already registered";
			}
		} else {
			return "adminName or Password is wrong";
		}

	}

	public String createAccount(String adminName, String password, long uid, AccountDetailsVO accountDetailsvo,
			long aid) {
		if (isAdminLogin(aid, adminName, password)) {
			try {
				User user = userRepoImp.getById(uid);
				AccountDetails accountDetails = new AccountDetails();
				accountDetails.setCifnumber(accountDetailsvo.getCifnumber());
				accountDetails.setAccounttype(accountDetailsvo.getAccounttype());
				accountDetails.setAccountnumber(accountDetailsvo.getAccountnumber());
				accountDetails.setUid(accountDetailsvo.getUid());
				accountDetails.setBalance(accountDetailsvo.getBalance());
				accountDetails.setIfsc(accountDetailsvo.getIfsc());
				accountDetails.setUser(user);
				if (accountDetails.getUser() != null) {
					accountDetailsRepository.save(accountDetails);
				} else {
					return "Enter valid user id";
				}

				return "Bank account successfuly created";
			} catch (Exception e) {
				return "Account number or cif number is not available";
			}
		} else {
			return "Admin is not logged in";
		}
	}

	@Transactional
	public String deleteAccountDetails(String adminName, String password, long accountNumber, long aid) {
		try {
			Admin admin = getById(aid);
			if (password.equals(admin.getPassword()) && adminName.equals(admin.getAdminname())) {
				try {
					return accountDetailsRepoImp.deleteByAccountnumber(accountNumber);
				} catch (Exception e) {
					return "bank account number is not valid";
				}
			}
			return "Enter valid adminName and password";
		} catch (Exception e) {
			return "Enter valid Admin id";
		}
	}

	public String deleteUser(String adminName, String password, long uid, long aid) {
		if (isAdminLogin(aid, adminName, password)) {
			try {
				userRepository.deleteById(uid);
				return "user successfully deleted";
			} catch (Exception e) {
				return "user id is not valid";
			}
		}
		return "Enter valid adminName and password";
	}

	public User getUser(String adminName, String password, long uid, long aid) {
		if (isAdminLogin(aid, adminName, password)) {
			try {
				User user = userRepoImp.getById(uid);
				if (user.getUid() <= 0) {
					return new User("User id is not valid");
				} else {
					return user;
				}
			} catch (Exception e) {
				return new User("User id is not valid");
			}
		} else {
			return new User("Admin is not allow to see this user details");
		}
	}

	public List<User> getAllUser() {
		List<User> users = new ArrayList<>();
		users = userRepository.findAll();
		return users;
	}
}
