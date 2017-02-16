package com.qianfeng.gamehelper.modules.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.qianfeng.gamehelper.R;
import com.qianfeng.gamehelper.activity.BaseFragment;
import com.qianfeng.gamehelper.activity.MainActivity;
import com.qianfeng.gamehelper.adapter.CommonAdapter;
import com.qianfeng.gamehelper.adapter.ViewHolder;
import com.qianfeng.gamehelper.i.BaseCallBack;
import com.qianfeng.gamehelper.modules.home.bean.Gamegiftinfo;
import com.qianfeng.gamehelper.modules.home.dao.Gamegiftdao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/27 0027.
 */
public class GamegiftFragment extends BaseFragment {

    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CommonAdapter<Gamegiftinfo> adapter;
    private List<Gamegiftinfo> gameinfoList;
    private int page=1;
    private View footview;
    private String TAG="GameFragment";
    private ImageButton button;

    @Override
    protected void findViews(View view) {
        listView= (ListView) view .findViewById(R.id.list_view_gamegift);
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout_gamegift);
        button = (ImageButton) view.findViewById(R.id.game_back_gamegift);
    }

    @Override
    protected void initEvent() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                swipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.GREEN,Color.DKGRAY);
                loadData();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemid=Integer.parseInt(gameinfoList.get(position).getId());
                Bundle bundle=new Bundle();
                bundle.putInt("id",itemid);

                Intent intent=new Intent(getActivity(),GiftActivity.class);
                intent.putExtra("id",itemid);
                startActivity(intent);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState==SCROLL_STATE_IDLE){
                    int lastVisiblePosition = listView.getLastVisiblePosition();
                    Log.d(TAG, "onScrollStateChanged:>>>>>>>>> "+lastVisiblePosition);

                    Log.d(TAG, "onScrollStateChanged:??????? "+(gameinfoList.size()-1)+listView.getHeaderViewsCount());
                    Log.d(TAG, "onScrollStateChanged:???????gameinfoList.size()-1 "+(gameinfoList.size()-1));
                    Log.d(TAG, "onScrollStateChanged:???????listView.getHeaderViewsCount() "+listView.getHeaderViewsCount());
                    if(lastVisiblePosition==gameinfoList.size()){//注意这里
                        Log.d(TAG, "onScrollStateChanged:>>>>>> "+(gameinfoList.size()-1)+listView.getHeaderViewsCount());
                        View childAt = listView.getChildAt(listView.getChildCount() - 1);
                        if( childAt.getBottom()==listView.getBottom()){
                            page++;
                            loadData();
                        }


                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(((MainActivity)getActivity()).fragmentList.get(0));
                transaction.show(((MainActivity)getActivity()).bmHome.getFragment());
                transaction.commit();

            }
        });
    }

    @Override
    protected void init() {
        gameinfoList = new ArrayList<>();
        footview = getActivity().getLayoutInflater().inflate(R.layout.foot_layout_loading, listView, false);

        adapter=new CommonAdapter<Gamegiftinfo>(getContext(), gameinfoList,R.layout.gamegift_layout_item) {
            @Override
            public void convert(ViewHolder helper, int position, Gamegiftinfo item) {
                helper.setImageByUrl(R.id.game_icon_gamegift,item.getIcon());
                helper.setText(R.id.game_name_gamegift,item.getName());
                helper.setText(R.id.game_text1_gamegift,"剩余:"+item.getRemain());
                helper.setText(R.id.game_text2_gamegift,item.getContent());


            }
        };
        listView.addFooterView(footview);
        listView.setAdapter(adapter);
        listView.removeFooterView(footview);
    }

    @Override
    protected void loadData() {

        Gamegiftdao.request(page, new BaseCallBack() {
            @Override
            public void success(Object data) {
                swipeRefreshLayout.setRefreshing(false);
                List<Gamegiftinfo> temlist= (List<Gamegiftinfo>) data;
                if(page==1){
                    gameinfoList.clear();
                }
                if(listView.getFooterViewsCount()==0){
                    listView.addFooterView(footview);
                }
                //注意这里:
                if(temlist.size()<10){
                    listView.removeFooterView(footview);
                    Toast.makeText(getActivity(), "数据加载完毕..", Toast.LENGTH_SHORT).show();
                }


                if(temlist!=null){
                    gameinfoList.addAll(temlist);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(), data.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void requestNetData() {
        
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_gamegift;
    }
}
