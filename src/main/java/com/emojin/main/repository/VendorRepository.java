package com.emojin.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emojin.main.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

	Vendor findByEmail(String email);
    // You can add custom query methods here if needed

	Vendor findByEmailAndPassword(String email, String password);

	Vendor findByVendorId(long id);
}
