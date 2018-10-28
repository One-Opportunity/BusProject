package com.sincle.cho.gwangjubus.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.sincle.cho.gwangjubus.R;
import com.sincle.cho.gwangjubus.dto.ArriveDTO;
import com.sincle.cho.gwangjubus.dto.StationInfoDTO;

import java.security.AccessControlContext;
import java.util.List;

import retrofit2.Callback;

public class AdapterArriveInfo extends BaseAdapter {

    private Context context;
    private List<ArriveDTO> list;
    private LayoutInflater inflate;
    private ViewHolder viewHolder;
    private ArriveDTO arriveDTO;

    public AdapterArriveInfo(List<ArriveDTO> list, Context context){
        this.list = list;
        this.context = context;
        this.inflate = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = inflate.inflate(R.layout.arrive_listview, null);

            viewHolder = new ViewHolder();
            viewHolder.search_station = (TextView) view.findViewById(R.id.arrive_bus);
            viewHolder.search_station_next = (TextView) view.findViewById(R.id.arrive_pre_station);
            viewHolder.search_station_num = (TextView) view.findViewById(R.id.arrive_time);
            viewHolder.hide_none = (TextView) view.findViewById(R.id.hide_none);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        arriveDTO = list.get(i);
        // 리스트에 있는 데이터를 리스트뷰 셀에 뿌린다.
        if(arriveDTO.getARRIVE_FLAG().equals("1")){
            viewHolder.search_station_num.setTextColor(Color.RED);
            viewHolder.search_station_num.setText("곧도착");
        } else {
            viewHolder.search_station_num.setTextColor(Color.BLACK);
            viewHolder.search_station_num.setText(arriveDTO.getREMAIN_MIN() + "분");
        }
        viewHolder.search_station.setText(arriveDTO.getLINE_NAME());
        viewHolder.search_station_next.setText(arriveDTO.getBUSSTOP_NAME());

            //viewHolder.hide_none.setVisibility(View.VISIBLE);



        return view;
    }

    class ViewHolder{
        public TextView search_station;
        public TextView search_station_next;
        public TextView search_station_num;
        public TextView hide_none;

    }
}
