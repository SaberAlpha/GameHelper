package com.qianfeng.gamehelper.modules.game.util;

import com.qianfeng.gamehelper.modules.game.bean.Gameinfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26 0026.
 */
public class Gameparse {
    public static List<Gameinfo> parseList(String data) {
        List<Gameinfo> list=new ArrayList<>();
        Gameinfo info=null;
        try {
            JSONObject subobjest=new JSONObject(data);
            JSONArray jsonArray = subobjest.getJSONArray("info");
            for(int i=0;i<jsonArray.length();i++){
                try{
                    info=new Gameinfo();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    info.setId(jsonObject.getString("id"));
                    info.setName(jsonObject.getString("name"));
                    info.setIcon(jsonObject.getString("icon"));
                    info.setNumber(jsonObject.getString("count_dl"));
                    info.setScore((int) jsonObject.getDouble("score"));
                    info.setSize(jsonObject.getString("size"));
                    list.add(info);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
