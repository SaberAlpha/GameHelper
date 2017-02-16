package com.qianfeng.gamehelper.modules.game.util;

import com.qianfeng.gamehelper.modules.game.bean.GameMessageinfo;
import com.qianfeng.gamehelper.modules.game.bean.Gameinfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26 0026.
 */
public class GameMessageparse {
    public static List<GameMessageinfo> parseList(String data) {
        List<GameMessageinfo> list=new ArrayList<>();
        GameMessageinfo info=null;
        try {
            JSONObject subobjest=new JSONObject(data);
            info=new GameMessageinfo();
            JSONObject jsonObject = subobjest.getJSONObject("info");
                    info.setId(jsonObject.getString("id"));
                    info.setName(jsonObject.getString("name"));
                    info.setIcon(jsonObject.getString("icon"));
                    info.setNumber(jsonObject.getString("count_dl"));
                    info.setScore((int) jsonObject.getDouble("score"));
                    info.setSize(jsonObject.getString("size"));
                    info.setApk(jsonObject.getString("android_dl"));
                    info.setGamedesc(jsonObject.getString("game_desc"));
                    info.setJifen(jsonObject.getString("dl_back_jifen"));
                    info.setVersion(jsonObject.getString("version"));
                    info.setSnapshot(jsonObject.getString("snapshot"));
                    list.add(info);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
