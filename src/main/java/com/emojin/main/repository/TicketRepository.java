package com.emojin.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emojin.main.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
   @Query(value = "select * from ticket where program_id=?",nativeQuery=true)
   public List<Ticket> findByProgId(Long id);
}

