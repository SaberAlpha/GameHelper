package com.qianfeng.gamehelper.modules.shop.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/10/29 0029.
 */
public class InnerListView extends ListView {

    public ScrollView scrollViewparent;



    public InnerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2
                , MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);


    }


    @Override
    public
    boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                setParentScrollAble(false);//当手指触到listview的时候，让父ScrollView交出ontouch权限，也就是让父scrollview停住不能滚动

            case MotionEvent.ACTION_MOVE:

                break;

            case MotionEvent.ACTION_UP:


            case MotionEvent.ACTION_CANCEL:


                setParentScrollAble(true);//当手指松开时，让父ScrollView重新拿到onTouch权限

                break;
            default:
                break;

        }
        return true;

    }

    /**
     * 是否把滚动事件交给父scrollview
     *
     * @param
    flag
     */
    private void setParentScrollAble(boolean flag) {
     //这里的parentScrollView就是listview外面的那个scrollview
//        scrollViewparent.requestDisallowInterceptTouchEvent(!flag);
        getParent().requestDisallowInterceptTouchEvent(!flag);

    }


}
