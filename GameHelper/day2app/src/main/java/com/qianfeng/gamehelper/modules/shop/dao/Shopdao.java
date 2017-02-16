package com.qianfeng.gamehelper.modules.shop.dao;

import com.qianfeng.gamehelper.i.BaseCallBack;
import com.qianfeng.gamehelper.modules.game.bean.Gameinfo;
import com.qianfeng.gamehelper.modules.game.util.Gameparse;
import com.qianfeng.gamehelper.modules.shop.bean.Shopinfo;
import com.qianfeng.gamehelper.modules.shop.util.Shopparse;
import com.qianfeng.gamehelper.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26 0026.
 */
public class Shopdao {
    public static void request(int page , final BaseCallBack callBack){

        HashMap<String,String> params=new HashMap<>();
        params.put("type","1");
        params.put("page",page+"");
        HttpUtil.doHttpReqeust("POST", "http://www.yuu1.com/app_api/prize_list/", params, new BaseCallBack() {
            @Override
            public void success(Object data) {
                if(callBack!=null){

                    List<Shopinfo> list= Shopparse.parseList(data.toString());
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
