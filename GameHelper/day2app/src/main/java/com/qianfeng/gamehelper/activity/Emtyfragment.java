package com.qianfeng.gamehelper.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.qianfeng.gamehelper.R;
import com.qianfeng.gamehelper.modules.game.activity.GameMessage;
import com.qianfeng.gamehelper.modules.home.activity.GamegiftFragment;
import com.qianfeng.gamehelper.modules.shop.activity.RealtyFragment;

/**
 * Created by Administrator on 2016/10/27 0027.
 */
public class Emtyfragment extends BaseFragment {

    private Fragment gamemessagefragment,gamegiftfragment,realtyfragment;
    @Override
    protected void findViews(View view) {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        gamegiftfragment=new GamegiftFragment();
        gamemessagefragment=new GameMessage();
        realtyfragment=new RealtyFragment();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.contain_emty,gamegiftfragment);
        transaction.add(R.id.contain_emty,gamemessagefragment);
        transaction.add(R.id.contain_emty,realtyfragment);
        transaction.commit();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void requestNetData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.emtyfragment_layout;
    }
}
