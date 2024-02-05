package com.emojin.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emojin.main.model.Stadium;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Long> {

	Stadium findByStdId(Long id);
	// You can add custom query methods here if needed
}
