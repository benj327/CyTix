package com.cytix.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "events") 
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id")
    private long event_id;
    
    @Column(name="event_name")
    private String event_name;
    
    @Column(name="event_time")
    private String event_time;
    
    @Column(name="event_location")
    private String event_location;
    
    //foreign key to User table, user_id column
    //should only have 1 entry in db
    @Column(name="event_host")
    private long event_host;
    
    //foreign key to User table, column consists of user_id 's of attendees
    //ultimately should be a list but for demo2 is a long)
    @OneToMany (mappedBy="event")
    private List<User> event_attendees;
    
    //foreing key to User table, column consists of user_id 's of checkers
    //ultimately should be a list but for demo2 is a long
    @OneToMany(mappedBy = "event")
    private List<User> event_checkers;

	public Long getEvent_id() {
		return event_id;
	}

	public void setEvent_id(long event_id) {
		this.event_id = event_id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEvent_time() {
		return event_time;
	}

	public void setEvent_time(String event_time) {
		this.event_time = event_time;
	}

	public String getEvent_location() {
		return event_location;
	}

	public void setEvent_location(String event_location) {
		this.event_location = event_location;
	}

	public long getEvent_host() {
		return event_host;
	}

	public void setEvent_host(long event_host) {
		this.event_host = event_host;
	}

	public List<User> getEvent_attendees() {
		return event_attendees;
	}

	public void setEvent_attendees(List<User> event_attendees) {
		this.event_attendees = event_attendees;
	}
	
	public void addAttendee(User attendee) {
		this.event_attendees.add(attendee);
	}
	

	public List<User> getEvent_checkers() {
		return event_checkers;
	}

	public void setEvent_checkers(List<User> event_checkers) {
		this.event_checkers = event_checkers;
	}
	
	public void addChecker(User checker) {
		this.event_checkers.add(checker);
	}
    
    //private int numTickets;
}
