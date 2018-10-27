package com.sincle.cho.gwangjubus.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Cho on 2018-05-14.
 */

public interface ApiService {
    static final String BASE_URL = "http://api.gwangju.go.kr/";
    static final String SERVICE_KEY = "7LngW1qmqW4Afanjv%2BoaR4r5azO2vqLtV%2F%2Fy6eJ2hyGsSYgnu2ReBO48P6gdBVKT%2FDqHWXFjBUeDEzW1TtC91w%3D%3D";

//    @GET("json/stationInfo?servicekey=7LngW1qmqW4Afanjv%2BoaR4r5azO2vqLtV%2F%2Fy6eJ2hyGsSYgnu2ReBO48P6gdBVKT%2FDqHWXFjBUeDEzW1TtC91w%3D%3D")
    @GET("json/stationInfo")
    Call<JsonObject> getStation(@Query("servicekey") String servicekey);

    @GET("json/arriveInfo")
    Call<JsonObject> getArrive(@Query("servicekey") String servicekey, @Query("BUSSTOP_ID") String BUSSTOP_ID);

}
