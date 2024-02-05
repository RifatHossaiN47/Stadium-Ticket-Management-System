package com.emojin.main.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Component
@Entity
//@Table(name="customer",uniqueConstraints= @UniqueConstraint(columnNames="email"))
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(nullable = false)
    private String customerName;
    private String dateOfBirth;
    private String gender;
    private String mobileNo;
    private String address;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ReviewsAndComplaints> reviewsAndComplaints;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Payment> payment;
    
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cart> cart;
    
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Customer(String customerName, String dateOfBirth, String gender, String mobileNo, String address,
			String email, String password, List<ReviewsAndComplaints> reviewsAndComplaints, List<Ticket> tickets,
			List<Payment> payment,List<Cart> cart) {
		super();
		this.customerName = customerName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.mobileNo = mobileNo;
		this.address = address;
		this.email = email;
		this.password = password;
		this.reviewsAndComplaints = reviewsAndComplaints;
		this.tickets = tickets;
		this.payment = payment;
		this.cart = cart;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public List<Cart> getCart() {
		return cart;
	}



	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<ReviewsAndComplaints> getReviewsAndComplaints() {
		return reviewsAndComplaints;
	}

	public void setReviewsAndComplaints(List<ReviewsAndComplaints> reviewsAndComplaints) {
		this.reviewsAndComplaints = reviewsAndComplaints;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}

	
    // Getters and setters
}
