package com.emojin.main.model;


import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ReviewsAndComplaints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rcId;
    private String name;
    private String email;
    private LocalDate dateSubmitted;
    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

	public ReviewsAndComplaints() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewsAndComplaints(String name, String email, LocalDate dateSubmitted, String description,
			Customer customer) {
		super();
		this.name = name;
		this.email = email;
		this.dateSubmitted = dateSubmitted;
		this.description = description;
		this.customer = customer;
	}

	public Long getRcId() {
		return rcId;
	}

	public void setRcId(Long rcId) {
		this.rcId = rcId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(LocalDate dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

    // Getters and setters
}
