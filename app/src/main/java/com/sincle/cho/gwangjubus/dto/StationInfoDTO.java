package com.sincle.cho.gwangjubus.dto;

import java.util.List;

/**
 * Created by Cho on 2018-05-14.
 */

public class StationInfoDTO {

    private String STATION_NUM = null;
    private String BUSSTOP_NAME = null;
    private String ARS_ID = null;
    private String NEXT_BUSSTOP = null;
    private String BUSSTOP_ID = null;
    private String LONGITUDE = null;
    private String NAME_E = null;
    private String LATITUDE = null;
    private List<ArriveDTO> arrive = null;

    @Override
    public String toString() {
        return "StationInfoDTO{" +
                "STATION_NUM='" + STATION_NUM + '\'' +
                ", BUSSTOP_NAME='" + BUSSTOP_NAME + '\'' +
                ", ARS_ID='" + ARS_ID + '\'' +
                ", NEXT_BUSSTOP='" + NEXT_BUSSTOP + '\'' +
                ", BUSSTOP_ID='" + BUSSTOP_ID + '\'' +
                ", LONGITUDE='" + LONGITUDE + '\'' +
                ", NAME_E='" + NAME_E + '\'' +
                ", LATITUDE='" + LATITUDE + '\'' +
                ", arrive=" + arrive +
                '}';
    }

    public List<ArriveDTO> getArrive() {
        return arrive;
    }

    public void setArrive(List<ArriveDTO> arrive) {
        this.arrive = arrive;
    }

    public String getSTATION_NUM() {
        return STATION_NUM;
    }

    public void setSTATION_NUM(String STATION_NUM) {
        this.STATION_NUM = STATION_NUM;
    }

    public String getBUSSTOP_NAME() {
        return BUSSTOP_NAME;
    }

    public void setBUSSTOP_NAME(String BUSSTOP_NAME) {
        this.BUSSTOP_NAME = BUSSTOP_NAME;
    }

    public String getARS_ID() {
        return ARS_ID;
    }

    public void setARS_ID(String ARS_ID) {
        this.ARS_ID = ARS_ID;
    }

    public String getNEXT_BUSSTOP() {
        return NEXT_BUSSTOP;
    }

    public void setNEXT_BUSSTOP(String NEXT_BUSSTOP) {
        this.NEXT_BUSSTOP = NEXT_BUSSTOP;
    }

    public String getBUSSTOP_ID() {
        return BUSSTOP_ID;
    }

    public void setBUSSTOP_ID(String BUSSTOP_ID) {
        this.BUSSTOP_ID = BUSSTOP_ID;
    }

    public String getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(String LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    public String getNAME_E() {
        return NAME_E;
    }

    public void setNAME_E(String NAME_E) {
        this.NAME_E = NAME_E;
    }

    public String getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(String LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StationInfoDTO && BUSSTOP_ID.equals(((StationInfoDTO) obj).getBUSSTOP_ID());
    }
}
