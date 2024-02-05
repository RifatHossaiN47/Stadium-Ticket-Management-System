package com.emojin.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emojin.main.model.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

	 @Query(value = "select * from program where event_organizer_id=?",nativeQuery=true)
	List<Program> findByOrganizerId(Long organizerId);
    
}
