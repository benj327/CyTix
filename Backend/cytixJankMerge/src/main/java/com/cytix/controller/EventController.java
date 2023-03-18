package com.cytix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cytix.model.Event;
import com.cytix.model.User;
import com.cytix.service.EventService;
import com.cytix.service.UserService;
import java.lang.Long;

@RestController
@RequestMapping("/api")
public class EventController {

	@Autowired
	EventService eventService;

	@Autowired
	UserService userService;
	
	

	@RequestMapping(value = "/events", method = RequestMethod.POST)
	public Event createEvent(@RequestBody Event event) {
		return eventService.createEvent(event);
	}

	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public List<Event> getEvents() {
		return eventService.getEvents();
	}

	@RequestMapping(value = "/events/{event_id}", method = RequestMethod.PUT)
	public Event readEvents(@PathVariable(value = "event_id") Long id, @RequestBody Event eventDetails) {
		return eventService.updateEvent(id, eventDetails);
	}

	@PostMapping(value = "/events/{event_id}/{user_id}")
	public Event addAttendee(@PathVariable("event_id") long event_id, @PathVariable("user_id") long user_id) {
		
		
		
		Event output = eventService.findEvent(event_id);
		User toAdd = userService.findUser(user_id);
		
		output.addAttendee(toAdd);

		
		return output;
	}
	
	@PostMapping(value = "/checkers/{event_id}/{user_id}")
	public Event addChecker(@PathVariable("event_id") long event_id, @PathVariable("user_id") long user_id) {
		
		
		
		Event output = eventService.findEvent(event_id);
		User toAdd = userService.findUser(user_id);
		
		output.addChecker(toAdd);
		
		return output;
	}

	@RequestMapping(value = "/event/{event_id}", method = RequestMethod.DELETE)
	public void deleteEvent(@PathVariable(value = "event_id") Long id) {
		eventService.deleteEvent(id);
	}

}
