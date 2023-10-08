package com.bank.OurBank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.OurBank.model.FD;

@Repository
public interface FDRepository extends JpaRepository<FD, Long> {
	Integer deleteById(long id);
}
