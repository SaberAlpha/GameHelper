package com.qianfeng.gamehelper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/10/24.
 */
public abstract class BaseFragment extends Fragment
{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(setLayoutId(),container,false);
        // 初始化控件
        findViews(view);
        // 初始化控件的点击事件
        initEvent();
        // 初始化界面
        init();
        // 加载数据
        loadData();
        requestNetData();
        return view;
    }



    // 初始化控件
    protected abstract void findViews(View view);

    // 初始化控件的点击事件
    protected abstract void initEvent();

    // 初始化界面
    protected abstract void init();

    // 加载数据
    protected abstract void loadData();
    protected abstract void requestNetData();

    // 设置布局
    protected abstract int setLayoutId();
}
