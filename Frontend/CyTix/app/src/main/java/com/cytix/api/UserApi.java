package com.cytix.api;

import com.cytix.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {

    @GET("api/users")
    Call<List<User>> getUsers();

    @POST("api/users")
    Call<User> createUser(@Body User newUser);
}
