package com.emojin.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emojin.main.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public Customer findByEmailAndPassword(String email, String password);

	public Customer findByEmail(String email);
}
