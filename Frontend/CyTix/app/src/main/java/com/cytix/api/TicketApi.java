package com.cytix.api;

import com.cytix.model.Event;
import com.cytix.model.Ticket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TicketApi {

    @GET("api/tickets")
    Call<List<Ticket>> getTickets();

    @POST("api/tickets")
    Call<Ticket> createTicket(@Body Ticket newTicket);

    @POST("api/tickets/{ticket_id}")
    Call<Ticket> updateTicket(@Path("ticket_id") Long ticket_id, @Body Ticket ticket);
}
