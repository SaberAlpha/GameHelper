package com.qianfeng.gamehelper.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by Administrator on 2016/10/31.
 */
public class MyVideoview extends VideoView {
    public MyVideoview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth=MeasureSpec.getSize(widthMeasureSpec);

        int measuredHeight=MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(measuredWidth,measuredHeight);
    }
}
