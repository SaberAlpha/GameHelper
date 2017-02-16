package com.qianfeng.gamehelper.modules.game.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.gamehelper.R;
import com.qianfeng.gamehelper.activity.BaseFragment;
import com.qianfeng.gamehelper.i.BaseCallBack;
import com.qianfeng.gamehelper.modules.game.bean.GameMessageinfo;
import com.qianfeng.gamehelper.modules.game.dao.GameMessagedao;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import it.sephiroth.android.library.picasso.Picasso;


/**
 * Created by Administrator on 2016/10/27 0027.
 */
public class GameMessage extends BaseFragment {


    private ImageView shoticon ,icon;
    private TextView name,text;
    private RatingBar rating;
    private int id=1;
    private List<GameMessageinfo> gameinfoList;
    private RadioGroup radiogroup;
     private  Fragment item1,item2,item3;
    public List<Fragment> messagefragmentList=new ArrayList<>();
    private   int   itempostion=0;
    private  int lastpostion=0;
    private View backview;
    private String TAG="GameMessage";
    private View secondlayoutview;
    private View sumlayoutview;
    private View headview;
    private ImageView backgroundimage;
    private float headviewTranslationY;
    private float secondlayoutviewTranslationY;
    private float sumlayoutviewTranslationY;
    private View share;


    @Override
    protected void findViews(View view) {
        shoticon = (ImageView) view.findViewById(R.id.gamemessage_imageview);
        icon= (ImageView) view.findViewById(R.id.gamemessage_icon);
        name = (TextView) view.findViewById(R.id.gamemessage_name);
        text= (TextView) view.findViewById(R.id.gamemessage_text);
        rating = (RatingBar) view.findViewById(R.id.gamemessage_ratingBar);
        radiogroup = (RadioGroup) view.findViewById(R.id.gamemessage_radiogroup);
        backview = view.findViewById(R.id.game_back_gamemessage);
        secondlayoutview = view.findViewById(R.id.gamemessage_secendlayout);
        sumlayoutview = view.findViewById(R.id.gamemessage_firstlayout);
        headview = view.findViewById(R.id.gamemessage_headview);
        backgroundimage = (ImageView) view.findViewById(R.id.gamemessage_background1);
        share = view.findViewById(R.id.game_share_gamemessage);

    }

    @Override
    protected void initEvent() {
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                switch (checkedId){
                    case R.id.game_button1_message:
                     itempostion=0;
                        break;
                    case R.id.game_button2_message:

                        itempostion=1;
                        break;
                    case R.id.game_button3_message:

                        itempostion=2;
                        break;
                }
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.show(messagefragmentList.get(itempostion));
                transaction.hide(messagefragmentList.get(lastpostion));
                transaction.commit();

                lastpostion=itempostion;
            }
        });

        backview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((GameActivity)getActivity()).finish();
            }
        });

        shoticon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

//                TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
//                animation.setDuration(1000);
//
//                animation.setFillAfter(true);
//                animation.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                            sumlayoutview.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//
//                    }
//                });
//              sumlayoutview.startAnimation(animation);


                Log.e(TAG, "onTouch:shotion点击 ??????" );

               // backgroundimage.setVisibility(View.INVISIBLE);
                ObjectAnimator animator=ObjectAnimator.ofFloat(sumlayoutview,"xxx",0,1).setDuration(1000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                        float pragress = (float) animation.getAnimatedValue();
                        if(pragress==0){
                            headviewTranslationY = headview.getTranslationY();
                            secondlayoutviewTranslationY = secondlayoutview.getTranslationY();
                        }
                        Log.e(TAG, "onAnimationUpdate: sumlayoutview.getTranslationX()"+sumlayoutview.getHeight());
                        Log.e(TAG, "onAnimationUpdate:  headview.getTranslationY();"+ headview.getHeight());
                        Log.e(TAG, "onAnimationUpdate: secondlayoutview.getTranslationY()"+secondlayoutview.getHeight());
                        sumlayoutview.setTranslationY(pragress*sumlayoutview.getHeight());
                    }
                });
                sumlayoutviewTranslationY = sumlayoutview.getTranslationY();
                animator.start();

                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //backgroundimage.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                return true;
            }
        });

        backgroundimage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(TAG, "onTouch:background点击 ??????" );

               // backgroundimage.setVisibility(View.INVISIBLE);
                ObjectAnimator animator=ObjectAnimator.ofFloat(sumlayoutview,"xxx",1,0).setDuration(1000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float pragress = (float) animation.getAnimatedValue();
                        Log.e(TAG, "onAnimationUpdate: sumlayoutview.getTranslationX()"+sumlayoutview.getHeight());
                        Log.e(TAG, "onAnimationUpdate:  headview.getTranslationY();"+ headview.getHeight());
                        Log.e(TAG, "onAnimationUpdate: secondlayoutview.getTranslationY()"+secondlayoutview.getHeight());

                        Log.e(TAG, "onAnimationUpdate:进度>>>> "+pragress );
                        Log.e(TAG, "onAnimationUpdate: 高度>>>>"+ pragress*sumlayoutview.getHeight());
                        sumlayoutview.setTranslationY(pragress*sumlayoutview.getHeight());
                    }
                });
                animator.start();
//                Log.e(TAG, "onTouch: headviewTranslationY,sumlayoutviewTranslationY,secondlayoutviewTranslationY"+ headviewTranslationY+">>"+sumlayoutviewTranslationY+"??"+secondlayoutviewTranslationY);
//                ObjectAnimator headAnimator=ObjectAnimator.ofFloat(headview,"translationY",sumlayoutviewTranslationY,-500f);
//                Log.e(TAG, "onTouch:background点击11 ??????" );
//                ObjectAnimator secondAnimator=ObjectAnimator.ofFloat(secondlayoutview,"translationY",sumlayoutviewTranslationY,-300f);
//                AnimatorSet animatorSet=new AnimatorSet();
//                Log.e(TAG, "onTouch:background点击21 ??????" );
//
//
//                animatorSet.play(headAnimator).with(secondAnimator);
//                Log.e(TAG, "onTouch:background点击31 ??????" );
//                animatorSet.setDuration(1000);
//                animatorSet.start();
//                Log.e(TAG, "onTouch:background点击41 ??????" );
                return true;
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
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
        gameinfoList = new ArrayList<>();

    }


    @Override
    protected void loadData() {
        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        GameMessagedao.request(id, new BaseCallBack() {
            @Override
            public void success(Object data) {
                gameinfoList.clear();
                Log.e(TAG, "success: data"+data.toString() );
                List<GameMessageinfo> temlist= (List<GameMessageinfo>) data;
                Log.e(TAG, "success:temlist "+temlist );
               gameinfoList.addAll(temlist);
                GameMessageinfo messageinfo= gameinfoList.get(0);
                name.setText(messageinfo.getName());
                text.setText("版本:"+messageinfo.getVersion()+"|"+messageinfo.getSize()+"\t"+messageinfo.getNumber()+"人下载");
                rating.setProgress(messageinfo.getScore());
                Picasso.with(getContext()).load(messageinfo.getIcon()).into(icon);
                Picasso.with(getContext()).load(messageinfo.getSnapshot()).into(shoticon);
                Picasso.with(getContext()).load(messageinfo.getSnapshot()).into(backgroundimage);

                item1=new GameMessageItem1();
                item2=new GameMessageItem2();
                item3=new GameMessageItem3();

                Bundle bun=new Bundle();
                bun.putString("content",gameinfoList.get(0).getGamedesc());
                bun.putInt("item1id",Integer.parseInt(gameinfoList.get(0).getId()));
                item1.setArguments(bun);

                messagefragmentList.add(item1);
                messagefragmentList.add(item2);
                messagefragmentList.add(item3);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.gamemessage_container,item1);
                transaction.add(R.id.gamemessage_container,item2);
                transaction.add(R.id.gamemessage_container,item3);
                transaction.hide(item2);
                transaction.hide(item3);

                transaction.commit();

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
        return R.layout.fragment_gamemessage;
    }
}
