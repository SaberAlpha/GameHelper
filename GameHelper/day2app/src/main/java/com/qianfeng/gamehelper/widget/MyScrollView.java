package com.qianfeng.gamehelper.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

import com.se7en.utils.DeviceUtils;

/**
 * Created by Administrator on 2016/10/25.
 */
public class MyScrollView extends ScrollView
{
    private int FACTOR = DeviceUtils.dip2px(76);

    private ScrollChangeUpdateListener listener;
    private String TAG="MyScrollView";

    public MyScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    /**
     * ScrollView的滑动监听方法
     * 
     * @param l
     *            在x轴上的滑动的位移
     * @param t
     *            在y轴上的滑动的位移
     * @param oldl
     *            记录上一次在x轴上的滑动的位移
     * @param oldt
     *            记录上一次在y轴上的滑动的位移
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.i("info", "l=" + l + ";t=" + t + ";oldl=" + oldl + ";oldt=" + oldt);
        Log.e(TAG, "onScrollChanged:FACTOR >>>>>"+FACTOR );
        float progress = t * 1.0f / FACTOR;
        Log.e(TAG, "onScrollChanged: "+progress );
        if (listener != null)
        {
            listener.progress(progress);
        }
    }

    public interface ScrollChangeUpdateListener
    {
        void progress(float progress);
    }

    public void setListener(ScrollChangeUpdateListener listener)
    {
        this.listener = listener;
    }

}
