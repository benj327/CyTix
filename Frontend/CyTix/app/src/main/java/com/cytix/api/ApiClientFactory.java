package com.cytix.api;

import com.cytix.model.Ticket;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientFactory {

    static Retrofit apiClientSeed = null;



    static Retrofit getApiClientSeed() {
        if (apiClientSeed == null) {
            apiClientSeed = new Retrofit.Builder()
                    .baseUrl("http://coms-309-002.class.las.iastate.edu:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }//https://78c7c668-a367-4b66-9ab8-4cb91fec4e6d.mock.pstmn.io/

        return apiClientSeed;
    }

    public static UserApi getUserApi() {
        return getApiClientSeed().create(UserApi.class);
    }
    public static TicketApi getTicketApi() { return getApiClientSeed().create(TicketApi.class); }
    public static EventApi getEventApi() { return getApiClientSeed().create(EventApi.class); }


}
