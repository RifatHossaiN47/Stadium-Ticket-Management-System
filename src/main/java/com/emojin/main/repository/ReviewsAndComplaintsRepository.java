package com.emojin.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emojin.main.model.ReviewsAndComplaints;

@Repository
public interface ReviewsAndComplaintsRepository extends JpaRepository<ReviewsAndComplaints, Long> {
    // You can add custom query methods here if needed
}
