package com.cytix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cytix.model.Event;
import com.cytix.repository.EventRepository;


@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;    
    
    public Event createEvent(Event event) {
    	return eventRepository.save(event);
    }
    
    public List<Event> getEvents(){
    	return eventRepository.findAll();
    }
    
    public void deleteEvent(Long event_id) {
    	eventRepository.deleteById(event_id);
    }
    
    public Event updateEvent(Long event_id, Event eventDetails) {
    	Event event = eventRepository.findById(event_id).get();
    	event.setEvent_id(eventDetails.getEvent_id());
    	event.setEvent_name(eventDetails.getEvent_name());
    	event.setEvent_time(eventDetails.getEvent_time());
    	event.setEvent_location(eventDetails.getEvent_location());
    	event.setEvent_host(eventDetails.getEvent_host());
    	event.setEvent_attendees(eventDetails.getEvent_attendees());
    	event.setEvent_checkers(eventDetails.getEvent_checkers());
    	
    	return eventRepository.save(event);
    }
    
    public Event findEvent(Long event_id) {
    	Event output = eventRepository.getById(event_id);
    	
    	return output;
    }
}
