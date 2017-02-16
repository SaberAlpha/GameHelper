package com.qianfeng.gamehelper.modules.home.dao;

import com.qianfeng.gamehelper.i.BaseCallBack;
import com.qianfeng.gamehelper.modules.home.bean.Gamegiftinfo;
import com.qianfeng.gamehelper.modules.home.util.Gamegiftparse;
import com.qianfeng.gamehelper.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26 0026.
 */
public class Gamegiftdao {
    public static void request(int page , final BaseCallBack callBack){

        HashMap<String,String> params=new HashMap<>();
        params.put("platform","2");
        params.put("gifttype","1");
        params.put("page",page+"");
        HttpUtil.doHttpReqeust("POST", "http://zhushou.72g.com/app/gift/gift_list/", params, new BaseCallBack() {
            @Override
            public void success(Object data) {
                if(callBack!=null){

                    List<Gamegiftinfo> list= Gamegiftparse.parseList(data.toString());
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
