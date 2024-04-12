package com.example.logic;
import org.json.JSONObject;

public class Req {
    private final String type;
    private final JSONObject data;

    public Req(String type, JSONObject data){
        this.type = type;
        this.data = data;
    }
    public String getType(){
        return this.type;
    }
    public JSONObject getData(){
        return this.data;
    }
}
