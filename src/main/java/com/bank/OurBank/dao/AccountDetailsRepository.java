package com.bank.OurBank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.OurBank.model.AccountDetails;


@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails,Long> {
	AccountDetails findById(long id);
	AccountDetails findByAccountnumber(long accountnumber);
	AccountDetails deleteByAccountnumber(long accountnumber);

}
