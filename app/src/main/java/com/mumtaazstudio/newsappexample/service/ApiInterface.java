package com.mumtaazstudio.newsappexample.service;

import com.mumtaazstudio.newsappexample.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<Response> getLatestNews(
            @Query("country") String country,
            @Query("apiKey") String apiKey);

}
