package com.sincle.cho.gwangjubus.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sincle.cho.gwangjubus.R;
import com.sincle.cho.gwangjubus.StationInfo;
import com.sincle.cho.gwangjubus.adapter.AdapterStationSearch;
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

public class FragmentStation extends android.support.v4.app.Fragment {
    // private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private AdapterStationSearch adapter;      // 리스트뷰에 연결할 아답터
    List<StationInfoDTO> list;
    private Retrofit retrofit;
    private ApiService apiService;
    StationInfoDTO[] array;
    ProgressDialog pd;
    private Intent intent;
    public FragmentStation(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_station, container, false);

        init(view);         // 기본세팅
        loading();          // json data 불러올 때 로딩화면 띄우기
        getStation();       // 정류장 정보 json 데이터 가져온 후 adaptor 담기
        searchEvent();      // 검색 중 나타나는 이벤트
        stationClick();     // 리스트뷰에 띄운 정류장 클릭 시 이벤트

        return view ;
    }

    private void init(View view) {
        editSearch = (EditText)view.findViewById(R.id.editSearch);
        listView = (ListView) view.findViewById(R.id.listView);

    }

    private void stationClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(getContext(), StationInfo.class);
                intent.putExtra("stationSeq", list.get(i).getBUSSTOP_ID());
                intent.putExtra("stationName", list.get(i).getBUSSTOP_NAME());
                intent.putExtra("nextStation", list.get(i).getNEXT_BUSSTOP());
                intent.putExtra("stationNum", list.get(i).getARS_ID());

                startActivity(intent);
            }
        });
    }

    private void searchEvent() {
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editSearch.getText().toString();
                search(text);       // 검색 시 리스트 찾는 로직
            }

        });
    }

    public void search(String charText) {

        list.clear();


        if(charText.length() == 0) {
            list.clear();

        } else {
            for(int i = 0; i < Arrays.asList(array).size(); i++) {
                if(Arrays.asList(array).get(i).getBUSSTOP_NAME().toLowerCase().contains(charText)){
                    list.add(Arrays.asList(array).get(i));
                    listView.setAdapter(adapter);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void getStation(){
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.BASE_URL)
                .build();

        apiService = retrofit.create(ApiService.class);
        Call<JsonObject> station = apiService.getStation(ApiService.SERVICE_KEY);

        station.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    JsonObject object = response.body();
                    JsonArray stationList = object.getAsJsonArray("STATION_LIST");
                    Gson gson = new Gson();

                    array = gson.fromJson(stationList, StationInfoDTO[].class);
                    list = new ArrayList<>(Arrays.asList(array));
                    adapter = new AdapterStationSearch(list, getContext());
                    pd.cancel();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public void loading(){
        pd = new ProgressDialog(getContext());
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("잠시만 기다려주세요.");
        pd.setCancelable(false);
        pd.show();
    }
}