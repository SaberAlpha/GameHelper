package com.qianfeng.gamehelper.modules.home.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.qianfeng.gamehelper.activity.MainActivity;

/**
 * Created by Administrator on 2016/10/25.
 */
public class ScaleView extends RelativeLayout {

    private final float FACTOR = 0.2F;

    private Paint paint;

    private int RADIU_MAX;

    private int radiu=-1;

    private float centerX, centerY;
    private OnstateChangedListener listener;

    public interface OnstateChangedListener{
        void onstateChanged();
    }

    public void setOnstateChangedListener(OnstateChangedListener listener){
        this.listener = listener;
    }

    public ScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        paint.setDither(true);//抗抖动
        paint.setColor(Color.parseColor("#55ffffff"));
        paint.setStyle(Paint.Style.FILL);

        //可以在post方法获取控件的高宽
        post(new Runnable() {
            @Override
            public void run() {
                RADIU_MAX = (int) Math.sqrt(Math.pow(getWidth(), 2) + Math.pow(getHeight(), 2));
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {

            startScaleAnimation();
            radiu=0;//设置为初始值
            //getX代表在控件上的坐标，getRawX代表在屏幕上的坐标
            centerX = event.getX();
            centerY = event.getY();
            invalidate();//触发onDraw
        }
        return true;
    }

    private void startScaleAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "xxx", 0, 1).setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //TODO 更新界面
                float progress = (float) animation.getAnimatedValue();
                setPivotY(getHeight() / 2);
                setPivotX(getWidth() / 2);
                if (progress < 0.5f) {
                    //TODO 放大的动画
                    setScaleX(1 + FACTOR * progress);
                    setScaleY(1 + FACTOR * progress);
                } else {
                    //TODO 缩小的动画
                    setScaleX(1 + FACTOR / 2 - FACTOR * (progress - 0.5f));
                    setScaleY(1 + FACTOR / 2 - FACTOR * (progress - 0.5f));
                }

                Log.i("info", "scaleX=" + getScaleX());
            }
        });


        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                performClick();//第一种方法
                    if(listener!=null){//第二种方法
                        //jumpToGameFragment();
                    listener.onstateChanged();
                    }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (radiu==-1||radiu > RADIU_MAX) {
            return;
        }
        canvas.drawCircle(centerX,centerY,radiu,paint);
        radiu+=50;//此值的大小决定了扩散的速度
        invalidate();
    }
}
