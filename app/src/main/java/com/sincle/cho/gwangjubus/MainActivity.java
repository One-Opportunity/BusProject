package com.sincle.cho.gwangjubus;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sincle.cho.gwangjubus.dto.ArriveDTO;
import com.sincle.cho.gwangjubus.dto.StationInfoDTO;
import com.sincle.cho.gwangjubus.service.ApiService;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    TextView latText, lonText, weather, weather2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getStation();

    }

    @Override
    public void onRefresh() {

    }

    private void initView(){
        //뷰세팅
        weather = (TextView) findViewById(R.id.weather);
        weather2 = (TextView) findViewById(R.id.weather2);
    }

    @Override
    public void onClick(View view) {

    }

    private void getStation(){
        Retrofit retrofit =new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.BASE_URL)
                .build();
        //정류장 가져오기
        final ApiService apiService = retrofit.create(ApiService.class);
        Call<JsonObject> station = apiService.getStation(ApiService.SERVICE_KEY);


        station.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.isSuccessful()){
                    // 정류장 데이터 받기
                    JsonObject object = response.body();
                    JsonArray stationList = object.getAsJsonArray("STATION_LIST");
                    Gson gson = new Gson();
                    // json을 list로 옮겨담기
                    StationInfoDTO[] array = gson.fromJson(stationList, StationInfoDTO[].class);
                    List<StationInfoDTO> list = Arrays.asList(array);

                    if (list != null) {
                        //도착정보 가져오기
                        Call<JsonObject> arrive = apiService.getArrive(ApiService.SERVICE_KEY, list.get(555).getBUSSTOP_ID());
                        Call<JsonObject> arrive2 = apiService.getArrive(ApiService.SERVICE_KEY, list.get(1566).getBUSSTOP_ID());


                        arrive.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                if(response.isSuccessful()){
                                    JsonObject objectArrive = response.body();
                                    JsonArray arriveList = objectArrive.getAsJsonArray("BUSSTOP_LIST");
                                    Gson gson = new Gson();

                                    ArriveDTO[] array = gson.fromJson(arriveList, ArriveDTO[].class );
                                    List<ArriveDTO> list = Arrays.asList(array);
                                    if(list != null) {
                                        weather.setText("신가동 가는 방향\n");

                                        for (ArriveDTO arriveDTO: list) {
                                            weather.setText(weather.getText() + "\n" + arriveDTO.getLINE_NAME() + "\n현재 : " + arriveDTO.getBUSSTOP_NAME() + "\n남은 시간 : " + arriveDTO.getREMAIN_MIN()+"분\n");
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {

                            }
                        });
                        arrive2.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                if(response.isSuccessful()){
                                    JsonObject objectArrive = response.body();
                                    JsonArray arriveList = objectArrive.getAsJsonArray("BUSSTOP_LIST");
                                    Gson gson = new Gson();

                                    ArriveDTO[] array = gson.fromJson(arriveList, ArriveDTO[].class );
                                    List<ArriveDTO> list = Arrays.asList(array);
                                    if(list != null) {
                                        weather2.setText("목련마을 6단지 가는 방향\n");
                                        for (ArriveDTO arriveDTO: list) {
                                            weather2.setText(weather2.getText() + "\n" + arriveDTO.getLINE_NAME() + "\n현재 : " + arriveDTO.getBUSSTOP_NAME() + "\n남은 시간 : " + arriveDTO.getREMAIN_MIN()+"분\n");
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {

                            }
                        });

                        //데이터가 null 이 아니라면 데이터를 텍스트뷰로 보여주기
                        weather.setText(list.get(555).getBUSSTOP_ID() + ", " +list.get(1566).getBUSSTOP_ID());
                    }

                        Log.d("데이터 : ", "" + list);

                }
            }
            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {

            }
        });


    }
}
