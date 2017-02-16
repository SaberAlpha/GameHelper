package com.qianfeng.gamehelper.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/10/24.
 */
public abstract class BaseActivity extends AppCompatActivity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // 隐藏ActioBar
        // supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(setLayoutId());
        // 初始化控件
        findViews();
        // 初始化控件的点击事件
        initEvent();
        // 初始化界面
        init();
        // 加载数据
        loadData();
    }

    // 初始化控件
    protected abstract void findViews();

    // 初始化控件的点击事件
    protected abstract void initEvent();

    // 初始化界面
    protected abstract void init();

    // 加载数据
    protected abstract void loadData();

    // 设置布局
    protected abstract int setLayoutId();
}
