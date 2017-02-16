package com.qianfeng.gamehelper.modules.game.dao;

import android.util.Log;

import com.qianfeng.gamehelper.i.BaseCallBack;
import com.qianfeng.gamehelper.modules.game.bean.GameMessageinfo;
import com.qianfeng.gamehelper.modules.game.bean.Gameinfo;
import com.qianfeng.gamehelper.modules.game.util.GameMessageparse;
import com.qianfeng.gamehelper.modules.game.util.Gameparse;
import com.qianfeng.gamehelper.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26 0026.
 */
public class GameMessagedao {
    public static void request(int id , final BaseCallBack callBack){

        HashMap<String,String> params=new HashMap<>();
        params.put("id",id+"");
        HttpUtil.doHttpReqeust("POST", "http://zhushou.72g.com/app/game/game_info/", params, new BaseCallBack() {
            @Override
            public void success(Object data) {
                if(callBack!=null){

                    List<GameMessageinfo> list= GameMessageparse.parseList(data.toString());
                    Log.e("print",list.toString()+"gamemessagedao>>>>>>>>>>>>");
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
