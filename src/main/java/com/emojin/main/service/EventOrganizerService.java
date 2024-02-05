package com.emojin.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emojin.main.model.EventOrganizer;
import com.emojin.main.model.Stadium;
import com.emojin.main.repository.EventOrganizerRepository;
import com.emojin.main.repository.StadiumRepository;

import java.util.List;

@Service
public class EventOrganizerService {
    private final EventOrganizerRepository eventOrganizerRepository;
    @Autowired
	StadiumRepository stRepo;
    @Autowired
    public EventOrganizerService(EventOrganizerRepository eventOrganizerRepository) {
        this.eventOrganizerRepository = eventOrganizerRepository;
    }

    public List<EventOrganizer> getAllEventOrganizers() {
        return eventOrganizerRepository.findAll();
    }

    public EventOrganizer getEventOrganizerById(Long id) {
        return eventOrganizerRepository.findById(id).orElse(null);
    }

    public EventOrganizer saveEventOrganizer(EventOrganizer eventOrganizer) {
        return eventOrganizerRepository.save(eventOrganizer);
    }

    public void deleteEventOrganizer(Long id) {
        eventOrganizerRepository.deleteById(id);
    }

	public boolean findByEmail(String email) {
		EventOrganizer org=eventOrganizerRepository.findByEmail(email);
		if(org!=null) return true;
		else return false;
	}
	
	public Stadium findStadium(Long id) {
		return stRepo.findByStdId(id);
	}
}
