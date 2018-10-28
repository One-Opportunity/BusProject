package com.sincle.cho.gwangjubus.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.sincle.cho.gwangjubus.R;
import com.sincle.cho.gwangjubus.dto.StationInfoDTO;

import java.util.List;

public class AdapterStationSearch extends BaseAdapter {

    private Context context;
    private List<StationInfoDTO> list;
    private LayoutInflater inflate;
    private StationInfoDTO stationInfoDTO;
    private ViewHolder viewHolder;

    public AdapterStationSearch(List<StationInfoDTO> list, Context context){
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
            view = inflate.inflate(R.layout.station_listview, null);

            viewHolder = new ViewHolder();
            viewHolder.search_station = (TextView) view.findViewById(R.id.search_station);
            viewHolder.search_station_next = (TextView) view.findViewById(R.id.search_station_next);
            viewHolder.search_station_num = (TextView) view.findViewById(R.id.search_station_num);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        stationInfoDTO = list.get(i);
        // 리스트에 있는 데이터를 리스트뷰 셀에 뿌린다.

        viewHolder.search_station.setText(stationInfoDTO.getBUSSTOP_NAME());
        if (!"null".equals(stationInfoDTO.getNEXT_BUSSTOP()+"")) {
            viewHolder.search_station_next.setText(stationInfoDTO.getNEXT_BUSSTOP() + " 방향");
        }
        if (!"null".equals(stationInfoDTO.getARS_ID()+"")) {
            viewHolder.search_station_num.setText(" <" + stationInfoDTO.getARS_ID().trim() + ">");
        }

        return view;
    }

    class ViewHolder{
        public TextView search_station;
        public TextView search_station_next;
        public TextView search_station_num;
    }
}