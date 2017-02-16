package com.qianfeng.gamehelper.modules.game.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qianfeng.gamehelper.R;
import com.qianfeng.gamehelper.activity.BaseActivity;

public class GameActivity extends BaseActivity {

    Fragment fragmentmessage;

    @Override
    protected void findViews() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {

        int id = getIntent().getIntExtra("id", 0);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        fragmentmessage=new GameMessage();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragmentmessage.setArguments(bundle);
        transaction.add(R.id.game_fragment_container,fragmentmessage);
        transaction.commit();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_game;
    }
}
