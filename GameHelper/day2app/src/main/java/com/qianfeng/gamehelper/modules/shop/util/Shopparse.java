package com.qianfeng.gamehelper.modules.shop.util;

import com.qianfeng.gamehelper.modules.game.bean.Gameinfo;
import com.qianfeng.gamehelper.modules.shop.bean.Shopinfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26 0026.
 */
public class Shopparse {
    public static List<Shopinfo> parseList(String data) {
        List<Shopinfo> list=new ArrayList<>();
        Shopinfo info=null;
        try {
            JSONObject subobjest=new JSONObject(data);
            JSONArray jsonArray = subobjest.getJSONArray("info");
            for(int i=0;i<jsonArray.length();i++){
                try{
                    info=new Shopinfo();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    info.setId(jsonObject.getString("id"));
                    info.setName(jsonObject.getString("name"));
                    info.setIcon(jsonObject.getString("icon"));
                    info.setConsume(jsonObject.getString("consume"));
                    info.setRemain(jsonObject.getString("remain"));
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
