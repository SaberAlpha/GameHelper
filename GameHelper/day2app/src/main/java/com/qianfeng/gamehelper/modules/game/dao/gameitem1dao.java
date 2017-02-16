package com.qianfeng.gamehelper.modules.game.dao;

import com.qianfeng.gamehelper.i.BaseCallBack;
import com.qianfeng.gamehelper.modules.game.bean.Gameitem1info;
import com.qianfeng.gamehelper.modules.game.util.Gameitem1parse;
import com.qianfeng.gamehelper.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26 0026.
 */
public class gameitem1dao {
    public static void request(int id , final BaseCallBack callBack){
        HashMap<String,String> params=new HashMap<>();
        params.put("id",id+"");
        params.put("platform","2");
        HttpUtil.doHttpReqeust("POST", "http://zhushou.72g.com/app/game/game_like/", params, new BaseCallBack() {
            @Override
            public void success(Object data) {
                if(callBack!=null){
                    List<Gameitem1info> list= Gameitem1parse.parseList(data.toString());
                    callBack.success(list);
                }
            }
            @Override
            public void failed(int errorCode, Object data) {
                callBack.failed(errorCode,data);
            }
        });
    }
}
