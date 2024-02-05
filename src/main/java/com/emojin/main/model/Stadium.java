package com.emojin.main.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stdId;
    private String stdName;
    private String stdLocation;
    private int stdCapacity;

    @OneToMany(mappedBy = "stadium",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Gallery> galleries;

    @OneToMany(mappedBy = "stadium",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Program> programs;

	public Stadium() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stadium(String stdName, String stdLocation, int stdCapacity, List<Gallery> galleries,
			List<Program> programs) {
		super();
		this.stdName = stdName;
		this.stdLocation = stdLocation;
		this.stdCapacity = stdCapacity;
		this.galleries = galleries;
		this.programs = programs;
	}

	public Long getStdId() {
		return stdId;
	}

	public void setStdId(Long stdId) {
		this.stdId = stdId;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public String getStdLocation() {
		return stdLocation;
	}

	public void setStdLocation(String stdLocation) {
		this.stdLocation = stdLocation;
	}

	public int getStdCapacity() {
		return stdCapacity;
	}

	public void setStdCapacity(int stdCapacity) {
		this.stdCapacity = stdCapacity;
	}

	public List<Gallery> getGalleries() {
		return galleries;
	}

	public void setGalleries(List<Gallery> galleries) {
		this.galleries = galleries;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

    // Getters and setters
}
