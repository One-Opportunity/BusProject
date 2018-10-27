package com.sincle.cho.gwangjubus.dto;

/**
 * Created by Cho on 2018-05-15.
 */

public class ArriveDTO {
    String  REMAIN_STOP,        // 남은 정류소 개수
            BUS_ID,             // 버스ID

            METRO_FLAG,         /* 광역 노선 구분
                                 *  0 : 광주
                                 *  1 : 나주
                                 *  2 : 담양
                                 *  3 : 장성
                                 *  4 : 화순 */

            BUSSTOP_NAME,       // 현재 정류소 명칭
            CURR_STOP_ID,       // 현재 정류소 ID
            LINE_ID,            // 노선 ID
            REMAIN_MIN,         // 도착 예정 시간
            ENG_BUSSTOP_NAME,   // 현재 정류소 위치 (영문)
            DIR_START,          // 기점명
            DIR,                //
            DIR_END,            // 종점명
            LOW_BUS,            // 저상버스

            ARRIVE_FLAG,        /* 곧도착 FLAG
                                 *  0 : 일반
                                 *  1 : 곧도착 */

            LINE_NAME;          // 노선명

    public String getREMAIN_STOP() {
        return REMAIN_STOP;
    }

    public void setREMAIN_STOP(String REMAIN_STOP) {
        this.REMAIN_STOP = REMAIN_STOP;
    }

    public String getBUS_ID() {
        return BUS_ID;
    }

    public void setBUS_ID(String BUS_ID) {
        this.BUS_ID = BUS_ID;
    }

    public String getMETRO_FLAG() {
        return METRO_FLAG;
    }

    public void setMETRO_FLAG(String METRO_FLAG) {
        this.METRO_FLAG = METRO_FLAG;
    }

    public String getBUSSTOP_NAME() {
        return BUSSTOP_NAME;
    }

    public void setBUSSTOP_NAME(String BUSSTOP_NAME) {
        this.BUSSTOP_NAME = BUSSTOP_NAME;
    }

    public String getCURR_STOP_ID() {
        return CURR_STOP_ID;
    }

    public void setCURR_STOP_ID(String CURR_STOP_ID) {
        this.CURR_STOP_ID = CURR_STOP_ID;
    }

    public String getLINE_ID() {
        return LINE_ID;
    }

    public void setLINE_ID(String LINE_ID) {
        this.LINE_ID = LINE_ID;
    }

    public String getREMAIN_MIN() {
        return REMAIN_MIN;
    }

    public void setREMAIN_MIN(String REMAIN_MIN) {
        this.REMAIN_MIN = REMAIN_MIN;
    }

    public String getENG_BUSSTOP_NAME() {
        return ENG_BUSSTOP_NAME;
    }

    public void setENG_BUSSTOP_NAME(String ENG_BUSSTOP_NAME) {
        this.ENG_BUSSTOP_NAME = ENG_BUSSTOP_NAME;
    }

    public String getDIR_START() {
        return DIR_START;
    }

    public void setDIR_START(String DIR_START) {
        this.DIR_START = DIR_START;
    }

    public String getDIR() {
        return DIR;
    }

    public void setDIR(String DIR) {
        this.DIR = DIR;
    }

    public String getDIR_END() {
        return DIR_END;
    }

    public void setDIR_END(String DIR_END) {
        this.DIR_END = DIR_END;
    }

    public String getLOW_BUS() {
        return LOW_BUS;
    }

    public void setLOW_BUS(String LOW_BUS) {
        this.LOW_BUS = LOW_BUS;
    }

    public String getARRIVE_FLAG() {
        return ARRIVE_FLAG;
    }

    public void setARRIVE_FLAG(String ARRIVE_FLAG) {
        this.ARRIVE_FLAG = ARRIVE_FLAG;
    }

    public String getLINE_NAME() {
        return LINE_NAME;
    }

    public void setLINE_NAME(String LINE_NAME) {
        this.LINE_NAME = LINE_NAME;
    }

    @Override
    public String toString() {
        return "ArriveDTO{" +
                "REMAIN_STOP='" + REMAIN_STOP + '\'' +
                ", BUS_ID='" + BUS_ID + '\'' +
                ", METRO_FLAG='" + METRO_FLAG + '\'' +
                ", BUSSTOP_NAME='" + BUSSTOP_NAME + '\'' +
                ", CURR_STOP_ID='" + CURR_STOP_ID + '\'' +
                ", LINE_ID='" + LINE_ID + '\'' +
                ", REMAIN_MIN='" + REMAIN_MIN + '\'' +
                ", ENG_BUSSTOP_NAME='" + ENG_BUSSTOP_NAME + '\'' +
                ", DIR_START='" + DIR_START + '\'' +
                ", DIR='" + DIR + '\'' +
                ", DIR_END='" + DIR_END + '\'' +
                ", LOW_BUS='" + LOW_BUS + '\'' +
                ", ARRIVE_FLAG='" + ARRIVE_FLAG + '\'' +
                ", LINE_NAME='" + LINE_NAME + '\'' +
                '}';
    }
}
