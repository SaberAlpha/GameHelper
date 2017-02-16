package com.qianfeng.gamehelper.modules.home.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qianfeng.gamehelper.R;
import com.qianfeng.gamehelper.activity.BaseActivity;
import com.qianfeng.gamehelper.modules.game.activity.GameMessage;

public class GiftActivity extends BaseActivity {

    private Fragment biggiftfragment;
    @Override
    protected void findViews() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        int id = getIntent().getIntExtra("id",5125 );
        Bundle bundle=new Bundle();
        bundle.putInt("iditem",id);
        biggiftfragment=new giftcontentfragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        biggiftfragment.setArguments(bundle);
        transaction.add(R.id.biggift_container,biggiftfragment);
        transaction.commit();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_gift;
    }
}
