package com.cytix.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.cytix.model.Event;
import com.cytix.repository.EventRepository;

class EventServiceTests {

	@Mock
	EventRepository repo;
	

	EventService eventService = mock(EventService.class);
	
	@Test
	public void createAndGetEvent() {
		Event event = new Event();
		eventService.createEvent(event);
		List<Event> list = new ArrayList<Event>();
		list = eventService.getEvents();
		assertNotEquals(null, list);
	}
	
	@Test
	public void deleteEvent() {
		Event event = new Event();
		event.setEvent_id((long)1234);
		eventService.createEvent(event);
		List<Event> list = new ArrayList<Event>();
		list = eventService.getEvents();
		assertNotEquals(null, list);
		eventService.deleteEvent(event.getEvent_id());
		list = eventService.getEvents();
		List<Event> test = new ArrayList<Event>();
		assertEquals(test, list);
	}

}
