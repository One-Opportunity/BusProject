package com.sincle.cho.gwangjubus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class StationInfo extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    Intent intent;
    String stationSeq, stationName, nextStation, stationNum;
    TextView tv_stationName, tv_nextStation, tv_stationNum, tv_hide;
    Retrofit retrofit;
    ApiService apiService;
    ArrayList<ArriveDTO> list;
    AdapterArriveInfo adapter;
    ListView listView;
    ArriveDTO[] array;
    SwipeRefreshLayout swipeRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_info);
        init();
        getArrive();

    }

    private void init() {
        tv_stationName = (TextView)findViewById(R.id.station_name);
        tv_stationNum = (TextView)findViewById(R.id.station_num);
        tv_nextStation = (TextView)findViewById(R.id.station_next);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setOnRefreshListener(this);
        intent = getIntent();
        stationSeq = intent.getStringExtra("stationSeq");
        stationName = intent.getStringExtra("stationName");
        nextStation = intent.getStringExtra("nextStation");
        stationNum = intent.getStringExtra("stationNum");
        tv_stationName.setText(stationName);
        if(!"null".equals(stationNum+"")) {
            tv_stationNum.setText("<" + stationNum.trim() + ">");
        }

        if (!"null".equals(nextStation+"")) {
            tv_nextStation.setText(nextStation + " 방향");
        }
    }

    public void getArrive(){
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.BASE_URL)
                .build();

        apiService = retrofit.create(ApiService.class);
        Call<JsonObject> station = apiService.getArrive(ApiService.SERVICE_KEY , stationSeq);

        station.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject object = response.body();
                    JsonArray busstopList = null;
                    Log.d("ROW_COUNT", object.get("ROW_COUNT") +"");
                    if(object != null) {
                        busstopList = object.getAsJsonArray("BUSSTOP_LIST");
                    }
                    Gson gson = new Gson();
                    array = gson.fromJson(busstopList, ArriveDTO[].class);
                    list = new ArrayList<>(Arrays.asList(array));
                    adapter = new AdapterArriveInfo(list, StationInfo.this);
                    listView = (ListView)findViewById(R.id.arrive_list);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void onRefresh() {
        swipeRefresh.setRefreshing(true);

                adapter.notifyDataSetChanged();
                getArrive();
                swipeRefresh.setRefreshing(false);



    }
}
