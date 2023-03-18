package com.cytix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cytix.model.Tickets;
import com.cytix.repository.TicketRepository;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;    
    
    public Tickets createTicket(Tickets ticket) {
    	return ticketRepository.save(ticket);
    }
    
    public List<Tickets> getTickets(){
    	return ticketRepository.findAll();
    }
    
    public void deleteTicket(Long ticket_id) {
    	ticketRepository.deleteById(ticket_id);
    }
    
    public Tickets updateTicket(Long ticket_id, Tickets ticketDetails) {
    	Tickets ticket = ticketRepository.findById(ticket_id).get();
    	ticket.setOwner(ticketDetails.getOwner());
    	ticket.setEvent(ticketDetails.getEvent());
    	
    	return ticketRepository.save(ticket);
    }
}
