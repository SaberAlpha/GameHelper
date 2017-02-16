package com.qianfeng.gamehelper.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qianfeng.gamehelper.R;

/**
 * Created by Administrator on 2016/10/25.
 */
public class BottomMenu extends RelativeLayout
{
    private TextView tvMenu;

    private ImageView ivMenu;

    private String menuText;

    private int resId;

    private int resSelectedId;

    private final float FACTOR = 0.2f;

    private Fragment fragment;

    public BottomMenu(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        // 将layout_bottom_menu布局和当前控件进行绑定
        View view = View.inflate(context, R.layout.layout_bottom_menu, this);
        tvMenu = (TextView) view.findViewById(R.id.tv_menu);
        ivMenu = (ImageView) view.findViewById(R.id.iv_menu);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.BottomMenu);
        menuText = a.getString(R.styleable.BottomMenu_menu_text);
        resId = a.getResourceId(R.styleable.BottomMenu_menu_icon_normal,
                R.mipmap.ic_launcher);
        resSelectedId = a.getResourceId(
                R.styleable.BottomMenu_menu_icon_selected,
                R.mipmap.ic_launcher);
        tvMenu.setText(menuText);
        ivMenu.setImageResource(resId);
    }

    /**
     * 将菜单设置为已选中状态
     */
    public void selectMenu()
    {
        tvMenu.setVisibility(GONE);
        ivMenu.setImageResource(resSelectedId);
        updateView(true);
    }

    /**
     * 将菜单设置为未选中状态
     */
    public void unSelectMenu()
    {
        tvMenu.setVisibility(VISIBLE);
        ivMenu.setImageResource(resId);
        updateView(false);
    }

    /**
     * 封装动画的业务
     * 
     * @param flag
     *            true:放大动画，false:缩小动画
     */
    private void updateView(boolean flag)
    {
        ObjectAnimator animator = null;
        if (flag)
        {
            animator = ObjectAnimator.ofFloat(this, "xxx", 0, 1)
                    .setDuration(200);
        }
        else
        {
            animator = ObjectAnimator.ofFloat(this, "xxx", 1, 0)
                    .setDuration(200);
        }
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                // TODO 更新界面
                float progress = (float) animation.getAnimatedValue();
                Log.i("info", progress + "");
                setPivotX(getWidth() / 2);
                setPivotY(0);
                setScaleX(1 + FACTOR * progress);
                setScaleY(1 + FACTOR * progress);
            }
        });
        animator.start();
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

}
