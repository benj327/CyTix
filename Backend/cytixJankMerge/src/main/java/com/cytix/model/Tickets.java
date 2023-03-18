package com.cytix.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tickets") 
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //add column annotations to variables
    @Column(name="ticket_id")
    private long ticket_id;
    
    //foreign key to user table, user_id column
    @Column(name="ticket_owner")
    private long owner;
    
    //foreign key to event table, event_id column
    @Column(name="ticket_event")
    private long event;
    
//    //value to see if ticket is checked, defaults to false
    @Column(name="ticket_checked")
    private boolean checked = false;

	public long getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(long ticket_id) {
		this.ticket_id = ticket_id;
	}

	public long getOwner() {
		return owner;
	}

	public void setOwner(long owner) {
		this.owner = owner;
	}

	public long getEvent() {
		return event;
	}

	public void setEvent(long event) {
		this.event = event;
	}
	
	public boolean getChecked() {
		return checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
