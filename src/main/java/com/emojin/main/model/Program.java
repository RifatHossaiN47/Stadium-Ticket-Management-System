package com.emojin.main.model;


import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Component
@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long progId;
    private String eventTitle;
    private String duration;
    private String date;
    private String image;

    @ManyToOne
    @JoinColumn(name = "event_organizer_id")
    private EventOrganizer eventOrganizer;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @OneToMany(mappedBy = "program",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ticket> tickets;
    
    @OneToMany(mappedBy = "program",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Gallery> gallery;

	public Program() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Program(String eventTitle, String duration, String date, String image, EventOrganizer eventOrganizer,
			Stadium stadium, List<Ticket> tickets,List<Gallery> gallery) {
		super();
		this.eventTitle = eventTitle;
		this.duration = duration;
		this.date = date;
		this.image = image;
		this.eventOrganizer = eventOrganizer;
		this.stadium = stadium;
		this.tickets = tickets;
		this.gallery=gallery;
	}


	public List<Gallery> getGallery() {
		return gallery;
	}


	public void setGallery(List<Gallery> gallery) {
		this.gallery = gallery;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public Long getProgId() {
		return progId;
	}

	public void setProgId(Long progId) {
		this.progId = progId;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public EventOrganizer getEventOrganizer() {
		return eventOrganizer;
	}

	public void setEventOrganizer(EventOrganizer eventOrganizer) {
		this.eventOrganizer = eventOrganizer;
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
