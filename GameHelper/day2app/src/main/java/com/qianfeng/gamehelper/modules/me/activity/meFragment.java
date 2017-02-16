package com.qianfeng.gamehelper.modules.me.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.qianfeng.gamehelper.R;
import com.qianfeng.gamehelper.activity.BaseFragment;
import com.qianfeng.gamehelper.util.BitmapUtil;

/**
 * Created by Administrator on 2016/10/25.
 */
public class meFragment extends BaseFragment{

    private ImageView imageView;

    @Override
    protected void findViews(View view) {
        imageView = (ImageView) view.findViewById(R.id.me_touxiang);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        Bitmap circleImage = BitmapUtil.createCircleImage(BitmapFactory.decodeResource(getResources(), R.mipmap.logo_qq));
         imageView.setImageBitmap(circleImage);

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void requestNetData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_me;
    }
}
