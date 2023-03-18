package com.cytix.api;

import com.cytix.model.Event;
import com.cytix.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EventApi {

    @POST("api/events")
    Call<Event> createEvent(@Body Event newEvent);

    @GET("api/events")
    Call<List<Event>> getEvents();

    @PUT("api/events/{event_id}")
    Call<Event> updateEvent(@Path("event_id") Long event_id, @Body Event event);

    @DELETE("api/events/{event_id}")
    Call<Event> deleteEvent(@Path("event_id") Long event_id, @Body Event event);

    @POST("api/events/{event_id}/{user_id}")
    Call<Event> addAttendee(@Path("event_id") Long event_id, @Body Long attendee);

}
