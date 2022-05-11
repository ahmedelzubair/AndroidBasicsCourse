package com.ahmedelzubair.androidfinalcourseprojectweatherapi.api;

import com.ahmedelzubair.androidfinalcourseprojectweatherapi.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {


    @GET("/current?access_key=10af470a4af41d475522e489c414e84b")
    Call<ApiResponse> getWeather(@Query("query") String search);


}
