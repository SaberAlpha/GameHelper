package com.qianfeng.gamehelper.modules.home.util;

import com.qianfeng.gamehelper.modules.game.bean.GameMessageinfo;
import com.qianfeng.gamehelper.modules.home.bean.Giftcontenteinfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26 0026.
 */
public class Giftparse {
    public static List<Giftcontenteinfo> parseList(String data) {
        List<Giftcontenteinfo> list=new ArrayList<>();
        Giftcontenteinfo info=null;
        try {
            JSONObject subobjest=new JSONObject(data);
            info=new Giftcontenteinfo();
            JSONObject jsonObject = subobjest.getJSONObject("info");
                    info.setId(jsonObject.getString("id"));
                    info.setName(jsonObject.getString("name"));
                    info.setIcon(jsonObject.getString("icon"));
                   info.setContent(jsonObject.getString("content"));
                  info.setApkload(jsonObject.getString("android_dl"));
                    info.setSize(jsonObject.getString("size"));
                   info.setStime(jsonObject.getString("stime"));
                    info.setEtime(jsonObject.getString("etime"));
                    info.setHowget(jsonObject.getString("howget"));
                    info.setTotal(jsonObject.getString("total"));
                    info.setType(jsonObject.getString("game_type"));
                    info.setRemian(jsonObject.getString("remain"));
                    list.add(info);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
