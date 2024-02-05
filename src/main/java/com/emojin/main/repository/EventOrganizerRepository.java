package com.emojin.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emojin.main.model.EventOrganizer;

@Repository
public interface EventOrganizerRepository extends JpaRepository<EventOrganizer, Long> {
    public EventOrganizer findByEmail(String email);

	public EventOrganizer findByEmailAndPassword(String email, String password);

}
