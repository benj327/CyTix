package com.cytix.model;

import java.util.List;
import java.io.Serializable;

public class Event implements Serializable {
    private Long event_id;
    private String event_name;
    private String event_time;
    private String event_location;
    private Long event_host;
    private List<User> event_attendees;
    private List<User> event_checkers;

    public Event() {}

    public Event(String name, String date, String location) {
        this.event_name = name;
        this.event_time = date;
        this.event_location = location;
    }

    public Long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Long event_id) {
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

    public List<User> getEvent_checkers() {
        return event_checkers;
    }

    public void addEvent_checker(User new_event_checker) {
        this.event_checkers.add(new_event_checker);
    }

    public Long getEvent_host() {
        return event_host;
    }

    public void setEvent_host(Long new_event_host) {
        this.event_host = new_event_host;
    }

    public List<User> getEvent_attendees() {
        return event_attendees;
    }

    public void addEvent_attendee(User new_event_attendee) {
        this.event_attendees.add(new_event_attendee);
    }


}
