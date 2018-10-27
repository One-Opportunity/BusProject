package com.sincle.cho.gwangjubus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sincle.cho.gwangjubus.adapter.AdapterArriveInfo;
import com.sincle.cho.gwangjubus.adapter.AdapterStationSearch;
import com.sincle.cho.gwangjubus.dto.ArriveDTO;
import com.sincle.cho.gwangjubus.dto.StationInfoDTO;
import com.sincle.cho.gwangjubus.service.ApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.security.AccessController.getContext;

public class StationInfo extends AppCompatActivity {
    private Intent intent;
    String stationId;
    private TextView stationName;
    private Retrofit retrofit;
    private ApiService apiService;
    private ArrayList<ArriveDTO> list;
    private AdapterArriveInfo adapter;
    private ListView listView;
    ArriveDTO[] array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_info);
        intent = getIntent();
        stationId = intent.getStringExtra("stationId");
        getArrive();

    }

    public void getArrive(){
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.BASE_URL)
                .build();

        apiService = retrofit.create(ApiService.class);
        Call<JsonObject> station = apiService.getArrive(ApiService.SERVICE_KEY , stationId);

        station.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject object = response.body();
                    JsonArray busstopList = object.getAsJsonArray("BUSSTOP_LIST");
                    Gson gson = new Gson();
                    array = gson.fromJson(busstopList, ArriveDTO[].class);
                    list = new ArrayList<>(Arrays.asList(array));
                    adapter = new AdapterArriveInfo(list, StationInfo.this);
                    listView = (ListView)findViewById(R.id.arrive_list);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}
