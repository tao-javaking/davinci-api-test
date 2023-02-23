package com.example.demo.domain;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class DataNode implements Serializable {

    public DataNode() {
    }

    public DataNode(Long date, String dfp, JSONObject flowRecord) {
        this.date = date;
        this.dfp = dfp;
        this.flowRecord = flowRecord;
    }

    private Long date;
    private String dfp;
    private JSONObject flowRecord;

    public JSONObject getFlowRecord() {
        return flowRecord;
    }

    public void setFlowRecord(JSONObject flowRecord) {
        this.flowRecord = flowRecord;
    }

    public String getDfp() {
        return dfp;
    }

    public void setDfp(String dfp) {
        this.dfp = dfp;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
