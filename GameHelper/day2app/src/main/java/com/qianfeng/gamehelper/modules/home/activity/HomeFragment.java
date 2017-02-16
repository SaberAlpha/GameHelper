package com.qianfeng.gamehelper.modules.home.activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qianfeng.gamehelper.R;
import com.qianfeng.gamehelper.activity.BaseFragment;
import com.qianfeng.gamehelper.activity.MainActivity;
import com.qianfeng.gamehelper.modules.home.widget.ScaleView;
import com.qianfeng.gamehelper.widget.MyScrollView;
import com.se7en.utils.DeviceUtils;

/**
 * Created by Administrator on 2016/10/25.
 */
public class HomeFragment extends BaseFragment
        implements MyScrollView.ScrollChangeUpdateListener
{
    private MyScrollView myScrollView;

    private ImageView ivHeader;

    private LinearLayout llUserInfo;

    private TextView tvStart;
    private String TAG="HomeFragment";
    private ScaleView gameScaleview;
    private ScaleView gamegiftscaleView;


    @Override
    protected void findViews(View view)
    {
        myScrollView = (MyScrollView) view.findViewById(R.id.my_scrollview);
        ivHeader= (ImageView) view.findViewById(R.id.iv_header);
        llUserInfo= (LinearLayout) view.findViewById(R.id.ll_user_info);
        tvStart= (TextView) view.findViewById(R.id.tv_start);
        gameScaleview = (ScaleView) view.findViewById(R.id.home_game);
        gamegiftscaleView = (ScaleView) view.findViewById(R.id.shouyoulibao);
    }

    @Override
    protected void initEvent()
    {
        myScrollView.setListener(this);
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).jumpToGameFragment();
                Log.e(TAG, "onClick: ?????????" );
            }
        });
        Log.e(TAG, "onClick: >>>>>>>>>>>1" );



        Log.d(TAG, "initEvent:>>>>>>>>> gamegiftscaleView ");
        //利用触摸事件来监听跳转
//        gamegiftscaleView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.d(TAG, "initEvent:>>>>>>>>>1 gamegiftscaleView ");
//                ((MainActivity)getActivity()).hidegiftFragment();
//
//                return true;
//            }
//        });

        gamegiftscaleView.setOnstateChangedListener(new ScaleView.OnstateChangedListener() {
            @Override
            public void onstateChanged() {
                ((MainActivity)getActivity()).hidegiftFragment();
            }
        });
//        gameScaleview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((MainActivity)getActivity()).jumpToGameFragment();
//            }
//        });

        gameScaleview.setOnstateChangedListener(new ScaleView.OnstateChangedListener() {
            @Override
            public void onstateChanged() {
                ((MainActivity)getActivity()).jumpToGameFragment();
            }
        });
    }

    @Override
    protected void init()
    {

    }

    @Override
    protected void loadData()
    {

    }

    @Override
    protected void requestNetData() {

    }

    @Override
    protected int setLayoutId()
    {
        return R.layout.fragment_home;
    }

    @Override
    public void progress(float progress)
    {
        Log.e(TAG, "progress:>>>>>>>>>>> "+progress );
        //llUserInfo的动画
        int offSetMax= DeviceUtils.getScreenWdith()/2+llUserInfo.getWidth()/2;
        llUserInfo.setTranslationX(progress*offSetMax);

        //ivHeader的动画
        //1.ivHeader位移的动画
        ivHeader.setTranslationX(-offSetMax*progress);
        offSetMax=llUserInfo.getHeight()+DeviceUtils.dip2px(12);
        if(progress<0.5f){
            ivHeader.setTranslationY(progress*offSetMax*2);
        }
        //2.ivHeader缩放的动画
        ivHeader.setPivotX(ivHeader.getWidth()/2);
        ivHeader.setPivotY(ivHeader.getHeight());

        float minScale=0.7f;
        ivHeader.setScaleX(1-progress*minScale);
        ivHeader.setScaleY(1-progress*minScale);
    }
}
