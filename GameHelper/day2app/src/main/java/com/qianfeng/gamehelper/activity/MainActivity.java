package com.qianfeng.gamehelper.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.qianfeng.gamehelper.R;
import com.qianfeng.gamehelper.modules.game.activity.GameFragment;
import com.qianfeng.gamehelper.modules.game.activity.GameMessage;
import com.qianfeng.gamehelper.modules.home.activity.GamegiftFragment;
import com.qianfeng.gamehelper.modules.home.activity.HomeFragment;
import com.qianfeng.gamehelper.modules.me.activity.meFragment;
import com.qianfeng.gamehelper.modules.shop.activity.RealtyFragment;
import com.qianfeng.gamehelper.modules.shop.activity.shopFragment;
import com.qianfeng.gamehelper.widget.BottomMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener
{
    public static BottomMenu bmHome;
    public static BottomMenu bmGame;
    public static BottomMenu bmShop;
    public static BottomMenu bmMe;
    public static BottomMenu lastSelectMenu;

    private Fragment homeFragment, gameFragment, shopFragment, meFragment,giftFragment,
    realtyFragment;

    public static List<Fragment> fragmentList=new ArrayList<>();
    @Override
    protected void findViews()
    {
        bmHome = (BottomMenu) findViewById(R.id.bm_home);
        bmGame = (BottomMenu) findViewById(R.id.bm_game);
        bmShop = (BottomMenu) findViewById(R.id.bm_shop);
        bmMe = (BottomMenu) findViewById(R.id.bm_me);
    }

    @Override
    protected void initEvent()
    {
        bmHome.setOnClickListener(this);
        bmGame.setOnClickListener(this);
        bmShop.setOnClickListener(this);
        bmMe.setOnClickListener(this);
    }

    @Override
    protected void init()
    {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        homeFragment = new HomeFragment();
        gameFragment = new GameFragment();
        shopFragment = new shopFragment();
        meFragment = new meFragment();
        giftFragment=new GamegiftFragment();
        realtyFragment=new RealtyFragment();

        // 将对应的菜单和fragment进行绑定，以便于在点击菜单的时候，将其对应的fragment设置为show状态
        bmHome.setFragment(homeFragment);
        bmGame.setFragment(gameFragment);
        bmShop.setFragment(shopFragment);
        bmMe.setFragment(meFragment);

        fragmentList.add(giftFragment);
        fragmentList.add(realtyFragment);

        transaction.add(R.id.fragment_container, meFragment);
        transaction.add(R.id.fragment_container, shopFragment);
        transaction.add(R.id.fragment_container, gameFragment);
        transaction.add(R.id.fragment_container, homeFragment);
        transaction.add(R.id.fragment_container, giftFragment);
        transaction.add(R.id.fragment_container, realtyFragment);


        transaction.hide(gameFragment);
        transaction.hide(shopFragment);
        transaction.hide(meFragment);
        transaction.hide(giftFragment);
        transaction.hide(realtyFragment);


        transaction.commit();
        // 默认把首页设置为选中状态
        bmHome.selectMenu();
        lastSelectMenu = bmHome;
    }

    @Override
    protected void loadData()
    {
    }

    @Override
    protected int setLayoutId()
    {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v)
    {
        // lastSelectMenu代表上次被选中的控件，使用它作为中间变量，和本次点击的控件进行对比，如果是同一个控件就什么都不做
        if (lastSelectMenu == v)
        {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (lastSelectMenu != null)
        {
            lastSelectMenu.unSelectMenu();
            transaction.hide(lastSelectMenu.getFragment());
        }
        ((BottomMenu) v).selectMenu();
        transaction.show(((BottomMenu) v).getFragment());
        lastSelectMenu = (BottomMenu) v;
        transaction.commit();
    }

    public void jumpToGameFragment()
    {
        bmGame.performClick();// 控件的模拟点击
    }

    public void hidegiftFragment(){
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.hide(bmHome.getFragment());
        transaction.show(fragmentList.get(0));
        transaction.commit();

    }


}
