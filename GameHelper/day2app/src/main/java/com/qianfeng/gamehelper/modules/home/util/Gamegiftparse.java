package com.qianfeng.gamehelper.modules.home.util;

import com.qianfeng.gamehelper.modules.game.bean.Gameinfo;
import com.qianfeng.gamehelper.modules.home.bean.Gamegiftinfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26 0026.
 */
public class Gamegiftparse {
    public static List<Gamegiftinfo> parseList(String data) {
        List<Gamegiftinfo> list=new ArrayList<>();
        Gamegiftinfo info=null;
        try {
            JSONObject subobjest=new JSONObject(data);
            JSONArray jsonArray = subobjest.getJSONArray("info");
            for(int i=0;i<jsonArray.length();i++){
                try{
                    info=new Gamegiftinfo();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    info.setId(jsonObject.getString("id"));
                    info.setName(jsonObject.getString("name"));
                    info.setIcon(jsonObject.getString("icon"));
                    info.setRemain(jsonObject.getString("remain"));
                    info.setContent(jsonObject.getString("content"));
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
