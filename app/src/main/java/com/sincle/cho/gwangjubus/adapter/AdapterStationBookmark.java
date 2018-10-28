package com.sincle.cho.gwangjubus.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.sincle.cho.gwangjubus.R;
import com.sincle.cho.gwangjubus.StationInfo;
import com.sincle.cho.gwangjubus.dto.StationInfoDTO;

import java.util.ArrayList;
import java.util.List;

public class AdapterStationBookmark extends BaseAdapter {

    private Context context;
    private ArrayList<StationInfoDTO> list;
    private LayoutInflater inflate;
    ViewHolder viewHolder;

    public AdapterStationBookmark(){
        list = new ArrayList<>();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    public boolean hasDeplicate(StationInfoDTO stationInfoDTO){
        boolean isDuplicate = false;
        for(int i = 0; i < list.size(); i++){
            StationInfoDTO current = list.get(i);
            if(current.getBUSSTOP_ID() == stationInfoDTO.getBUSSTOP_ID()) {
                isDuplicate = true;
            }
        }
        return isDuplicate;
    }

    public void addStationInfo(StationInfoDTO stationInfoDTO){
        list.add(stationInfoDTO);
        notifyDataSetChanged();
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        context = viewGroup.getContext();
        final StationInfoDTO stationInfoDTO = list.get(i);

        if (view == null) {
            inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate(R.layout.bookmark_listview, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.search_station = (TextView) view.findViewById(R.id.bookmark_station);
            viewHolder.search_station_next = (TextView) view.findViewById(R.id.bookmark_station_next);
            viewHolder.search_station_num = (TextView) view.findViewById(R.id.bookmark_station_num);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        // 리스트에 있는 데이터를 리스트뷰 셀에 뿌린다.
        viewHolder.search_station.setText(stationInfoDTO.getBUSSTOP_NAME());
        if (!"null".equals(list.get(i).getNEXT_BUSSTOP()+"")) {
            viewHolder.search_station_next.setText(stationInfoDTO.getNEXT_BUSSTOP() + " 방향");
        }
        if (!"null".equals(list.get(i).getARS_ID()+"")) {
            viewHolder.search_station_num.setText(" <" + stationInfoDTO.getARS_ID() + ">");
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StationInfo.class);
                intent.putExtra("stationId", stationInfoDTO.getBUSSTOP_ID());
                context.startActivity(intent);
            }
        });
        return view;
    }

    class ViewHolder{
        public TextView search_station;
        public TextView search_station_next;
        public TextView search_station_num;
    }
}
