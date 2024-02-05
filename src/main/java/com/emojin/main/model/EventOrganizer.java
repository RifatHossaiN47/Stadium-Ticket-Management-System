package com.emojin.main.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="eventOrganizer")
public class EventOrganizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizerId;
    private String organizationName;
    private String designation;
    private String department;
    private String mobileNo;
    private String address;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "eventOrganizer",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Program> programs;

	public EventOrganizer() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public EventOrganizer(String organizationName, String designation, String department, String mobileNo,
			String address, String email, String password, List<Program> programs) {
		super();
		this.organizationName = organizationName;
		this.designation = designation;
		this.department = department;
		this.mobileNo = mobileNo;
		this.address = address;
		this.email = email;
		this.password = password;
		this.programs = programs;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Long getOrganizerId() {
		return organizerId;
	}

	public void setOrganizerId(Long organizerId) {
		this.organizerId = organizerId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

    // Getters and setters
}
