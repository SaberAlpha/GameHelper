package com.qianfeng.gamehelper.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qianfeng.gamehelper.widget.MyVideoview;

/**
 * Created by Administrator on 2016/10/25.
 */
public class WelComePagerAdapter extends PagerAdapter
{
    private final MyVideoview[] videoViews;
    private ImageView[] imageViews;
    public WelComePagerAdapter(MyVideoview[] videoViews)
    {
//        this.imageViews = imageViews;
        this.videoViews =videoViews;
    }

    @Override
    public int getCount()
    {
        return videoViews.length;
    }
    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
//        View view = imageViews[position];
        MyVideoview view = videoViews[position];
        view.start();
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
//        container.removeView(imageViews[position]);
        container.removeView(videoViews[position]);
    }
}
