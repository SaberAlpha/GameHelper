package com.qianfeng.gamehelper.modules.home.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.gamehelper.R;
import com.qianfeng.gamehelper.activity.BaseFragment;
import com.qianfeng.gamehelper.i.BaseCallBack;
import com.qianfeng.gamehelper.modules.home.bean.Giftcontenteinfo;
import com.qianfeng.gamehelper.modules.home.dao.Giftdao;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import it.sephiroth.android.library.picasso.Picasso;


/**
 * Created by Administrator on 2016/10/28 0028.
 */
public class giftcontentfragment extends BaseFragment {
    private int id=5125;
    private List<Giftcontenteinfo> gameinfoList;
    private ImageView imageViewicon;
    private TextView name,progresstext,stylesizetext2,timetext3,contentext,gifttext;
    private ProgressBar progressBar;
    private View backview;
    private String TAG="giftcontentfragment";
    private View shareview;


    @Override
    protected void findViews(View view) {
        imageViewicon = (ImageView) view.findViewById(R.id.biggift_icon);
        name= (TextView) view.findViewById(R.id.biggift_name);
        progresstext= (TextView) view.findViewById(R.id.biggift_textView);
        timetext3= (TextView) view.findViewById(R.id.biggift_text2);
        contentext= (TextView) view.findViewById(R.id.biggift_content);
        gifttext= (TextView) view.findViewById(R.id.biggift_way);
        progressBar = (ProgressBar) view.findViewById(R.id.biggift_progressBar);
        stylesizetext2= (TextView) view.findViewById(R.id.biggift_sizetyte);
        backview = view.findViewById(R.id.back_biggift);
        shareview = view.findViewById(R.id.biggift_share);
    }

    @Override
    protected void initEvent() {
        backview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        shareview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
    }

    private void showShare() {
        ShareSDK.initSDK(getContext());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(getContext());
    }

    @Override
    protected void init() {
        gameinfoList=new ArrayList<>();
    }

    @Override
    protected void loadData() {
        Bundle bundle = getArguments();
        int id = bundle.getInt("iditem");
        Giftdao.request(id, new BaseCallBack() {
            @Override
            public void success(Object data) {
                gameinfoList.clear();
                Log.e(TAG, "success: data"+data.toString() );
                List<Giftcontenteinfo> temlist= (List<Giftcontenteinfo>) data;
                Log.e(TAG, "success:temlist "+temlist );
                gameinfoList.addAll(temlist);
                Giftcontenteinfo messageinfo= gameinfoList.get(0);
                name.setText(messageinfo.getName());
                progresstext.setText(messageinfo.getRemian()+"/"+messageinfo.getTotal());
                timetext3.setText(messageinfo.getStime()+"\t"+messageinfo.getEtime());
                contentext.setText(messageinfo.getContent());
                gifttext.setText(messageinfo.getHowget());
                if(messageinfo.getType()!=null||messageinfo.getSize()!=null){
                    stylesizetext2.setText("类型:"+messageinfo.getType()+"\t"+"大小:"+messageinfo.getSize());
                }
//                if(!TextUtils.isEmpty(messageinfo.getType())&&!TextUtils.isEmpty(messageinfo.getSize())){
//
//                }
                int remain=Integer.parseInt(messageinfo.getRemian());
                int total=Integer.parseInt(messageinfo.getTotal());
                progressBar.setMax(total);
                progressBar.setProgress(remain);
                Picasso.with(getContext()).load(messageinfo.getIcon()).into(imageViewicon);


            }

            @Override
            public void failed(int errorCode, Object data) {
                Toast.makeText(getActivity(), data.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void requestNetData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.giftcontent;
    }
}
