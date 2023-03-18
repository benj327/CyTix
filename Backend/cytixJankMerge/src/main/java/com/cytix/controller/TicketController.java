package com.cytix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cytix.model.Tickets;
import com.cytix.service.TicketService;

@RestController
@RequestMapping("/api")
public class TicketController {
	
	@Autowired
	TicketService ticketService;
	
	@RequestMapping(value="/tickets", method=RequestMethod.POST)
	public Tickets createTicket(@RequestBody Tickets ticket) {
		return ticketService.createTicket(ticket);
	}
	
	@RequestMapping(value="/tickets", method=RequestMethod.GET)
	public List<Tickets> readTickets(){
		return ticketService.getTickets();
	}
	
	@RequestMapping(value="/tickets/{ticket_id}", method=RequestMethod.PUT)
	public Tickets updateTickets(@PathVariable(value = "ticket_id") Long id, @RequestBody Tickets ticketDetails) {
		return ticketService.updateTicket(id, ticketDetails);
	}
	
	@RequestMapping(value="tickets/{ticket_id}", method=RequestMethod.DELETE)
	public void deleteTicket(@PathVariable(value = "ticket_id") Long id) {
		ticketService.deleteTicket(id);
	}

}
