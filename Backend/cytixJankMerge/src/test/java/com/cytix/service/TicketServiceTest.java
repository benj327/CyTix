package com.cytix.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.cytix.model.Tickets;
import com.cytix.repository.TicketRepository;

class TicketServiceTest {
	
	@Mock
	TicketRepository repo;
	
	TicketService ticketService = mock(TicketService.class);

	@Test
	public void createAndGetTickets() {
		Tickets ticket = new Tickets();
		ticketService.createTicket(ticket);
		List<Tickets> list = new ArrayList<Tickets>();
		list = ticketService.getTickets();
		assertNotEquals(null, list);
	}
	
	@Test
	public void deleteTicket() {
		Tickets ticket = new Tickets();
		ticket.setTicket_id((long)1234);
		ticketService.createTicket(ticket);
		List<Tickets> list = new ArrayList<Tickets>();
		list = ticketService.getTickets();
		assertNotEquals(null, list);
		ticketService.deleteTicket(ticket.getTicket_id());
		list = ticketService.getTickets();
		List<Tickets> test = new ArrayList<Tickets>();
		assertEquals(test, list);
	}
	

}
