package com.bank.OurBank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.OurBank.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{
	Admin findById(long id);
	Admin findByAdminname(String adminName);
}
