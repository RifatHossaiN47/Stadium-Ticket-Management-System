package com.emojin.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emojin.main.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	@Query(value = "select * from cart where customer_id=?",nativeQuery=true)
	   public List<Cart> findByCustomerId(Long id);
}
