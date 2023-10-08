package com.bank.OurBank.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.OurBank.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	User findById(long id);
	
}
