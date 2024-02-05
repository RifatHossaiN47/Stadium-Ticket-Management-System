package com.emojin.main.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gId;
    private String gName;
    private String gLocation;
    private int gCapacity;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @OneToMany(mappedBy = "gallery",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ticket> tickets;
    
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

	public Gallery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Gallery(String gName, String gLocation, int gCapacity, Vendor vendor, Stadium stadium,
			List<Ticket> tickets,Program program) {
		super();
		this.gName = gName;
		this.gLocation = gLocation;
		this.gCapacity = gCapacity;
		this.vendor = vendor;
		this.stadium = stadium;
		this.tickets = tickets;
		this.program = program;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Long getgId() {
		return gId;
	}

	public void setgId(Long gId) {
		this.gId = gId;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getgLocation() {
		return gLocation;
	}

	public void setgLocation(String gLocation) {
		this.gLocation = gLocation;
	}

	public int getgCapacity() {
		return gCapacity;
	}

	public void setgCapacity(int gCapacity) {
		this.gCapacity = gCapacity;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Stadium getStadium() {
		return stadium;
	}

	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

    // Getters and setters
}
