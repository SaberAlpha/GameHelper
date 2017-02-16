package com.qianfeng.gamehelper.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.qianfeng.gamehelper.R;
import com.qianfeng.gamehelper.adapter.WelComePagerAdapter;
import com.qianfeng.gamehelper.widget.MyVideoview;
import com.se7en.utils.SystemUtil;

/**
 * Created by Administrator on 2016/10/25.
 */
public class WelcomeActivity extends BaseActivity {
    private ViewPager viewPager;

    private ImageView ivStart, ivText, ivIcon;

    private final String VERSION_CODE = "VERSION_CODE";

    private WelComePagerAdapter adapter;

    private ImageView[] imageViews;
    private MyVideoview[] videoViews;

    @Override
    protected void findViews() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ivStart = (ImageView) findViewById(R.id.iv_start);
        ivText = (ImageView) findViewById(R.id.iv_logo_text);
        ivIcon = (ImageView) findViewById(R.id.iv_logo_icon);
    }

    @Override
    protected void initEvent() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // TODO 滑到最后页就显示开始体验按钮
                if (position == videoViews.length - 1) {
                    ivStart.setVisibility(View.VISIBLE);
                } else {
                    ivStart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ivStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 跳转到主页
                jumpToMainActivity();
                //将当前应用的版本号设置到sp中，以用作下次对比使用
                SystemUtil.setSharedInt(VERSION_CODE, SystemUtil.getSystemVersionCode());
            }
        });
    }

    @Override
    protected void init() {
        // 判断如果是第一次进入应用或者是更新后的第一次进入应用时要显示viewpager控件
        int oldVersionCode = SystemUtil.getSharedInt(VERSION_CODE, -1);
        int newVersionCode = SystemUtil.getSystemVersionCode();// 获取应用当前的版本号
        if (oldVersionCode == -1 || oldVersionCode < newVersionCode) {
            // TODO 显示viewpager控件
            initViewpager();
        } else {
            // TODO 隐藏viewpager并实现动画
            //由于activity需要时间，所以以免错过动画，可以延时启动动画
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    viewPager.setVisibility(View.GONE);
                    startLogoText();
                }
            }, 1000);
        }
    }

    /**
     * 启动文字进入动画
     */
    private void startLogoText() {
        ivText.setVisibility(View.VISIBLE);
        //从左边屏幕的中间移动到当前屏幕的中间
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
        animation.setDuration(1000);
        animation.setInterpolator(new OvershootInterpolator());//加入渲染效果（超速）
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //TODO 开始LOGO掉落的动画
                startLogoAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivText.startAnimation(animation);
    }

    /**
     * 开始LOGO掉落的动画
     */
    private void startLogoAnimation() {
        ivIcon.setVisibility(View.VISIBLE);
        //从左边屏幕的中间移动到当前屏幕的中间
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, -1, Animation.RELATIVE_TO_PARENT, 0);
        animation.setDuration(1000);
        animation.setInterpolator(new BounceInterpolator());//加入渲染效果(回弹)
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //TODO 进入主页面
                jumpToMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivIcon.startAnimation(animation);
    }

    private void initViewpager() {
//        imageViews = new ImageView[4];
//        ImageView iv = new ImageView(this);
//        setImageBitmap(iv, R.mipmap.bg_guide_01);
//        iv.setScaleType(ImageView.ScaleType.FIT_XY);
//        imageViews[0] = iv;
//        iv = new ImageView(this);
//        setImageBitmap(iv, R.mipmap.bg_guide_02);
//        iv.setScaleType(ImageView.ScaleType.FIT_XY);
//        imageViews[1] = iv;
//
//        iv = new ImageView(this);
//        setImageBitmap(iv, R.mipmap.bg_guide_03);
//        iv.setScaleType(ImageView.ScaleType.FIT_XY);
//        imageViews[2] = iv;
//
//        iv = new ImageView(this);
//        setImageBitmap(iv, R.mipmap.bg_guide_04);
//        iv.setScaleType(ImageView.ScaleType.FIT_XY);
//        imageViews[3] = iv;
        int[] splalshs=new int[]{R.raw.splash_1,R.raw.splash_2,R.raw.splash_4,R.raw.splash_5};

        videoViews = new MyVideoview[4];
        for(int i=0;i<4;i++){
            MyVideoview videoView=new MyVideoview(this,null);
            videoView.setVideoURI(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+"://"+getPackageName()
                    +"/"+splalshs[i]));
            videoViews[i]=videoView;
        }
        adapter = new WelComePagerAdapter(videoViews);
        viewPager.setAdapter(adapter);
    }

    /**
     * 将一个资源图片进行二次采样并设置到控件中
     *
     * @param iv
     * @param resId
     */
    private void setImageBitmap(ImageView iv, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;//将bitmap宽和高都压缩2倍
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = false;//解码整张图片
        Bitmap bm = BitmapFactory.decodeResource(getResources(), resId, options);
        iv.setImageBitmap(bm);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_welcome;
    }

    private void jumpToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
