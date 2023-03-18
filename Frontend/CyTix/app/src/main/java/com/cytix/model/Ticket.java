package com.cytix.model;

public class Ticket {
    private Long ticket_id;
    private Long owner;
    private Long event;
    private boolean checked;



    public Ticket() {
        this.checked = false;
    }

    public Long getTicketId() { return ticket_id; }

    public void setTicketId(Long id) {
        this.ticket_id = id;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long givenOwner) {
        this.owner = givenOwner;
    }

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long givenEvent) {
        this.event = givenEvent;
    }

    public boolean getChecked() { return checked; }

    public void setChecked(boolean checked) { this.checked = checked; }


}
