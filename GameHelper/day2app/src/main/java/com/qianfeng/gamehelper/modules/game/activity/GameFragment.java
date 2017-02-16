package com.qianfeng.gamehelper.modules.game.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.qianfeng.gamehelper.R;
import com.qianfeng.gamehelper.activity.BaseFragment;
import com.qianfeng.gamehelper.adapter.CommonAdapter;
import com.qianfeng.gamehelper.adapter.ViewHolder;
import com.qianfeng.gamehelper.i.BaseCallBack;
import com.qianfeng.gamehelper.modules.game.bean.Gameinfo;
import com.qianfeng.gamehelper.modules.game.dao.Gamedao;
import com.qianfeng.gamehelper.modules.game.util.XutilsManager;
import com.qianfeng.gamehelper.util.ThreadTask;

import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */
public class GameFragment extends BaseFragment {

    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CommonAdapter<Gameinfo> adapter;
    private List<Gameinfo> gameinfoList;
    private int page = 1;
    private View footview;
    private String TAG = "GameFragment";
    Fragment messagefragment = new GameMessage();
    @Override
    protected void findViews(View view) {
        listView = (ListView) view.findViewById(R.id.list_view);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
    }

    @Override
    protected void initEvent() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.DKGRAY);
                requestNetData();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemClick: 点击" + position);
                int itemid = Integer.parseInt(gameinfoList.get(position).getId());
                Bundle bundle = new Bundle();
                bundle.putInt("id", itemid);

                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("id", itemid);
                startActivity(intent);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    int lastVisiblePosition = listView.getLastVisiblePosition();
                    Log.d(TAG, "onScrollStateChanged:>>>>>>>>> " + lastVisiblePosition);

                    Log.d(TAG, "onScrollStateChanged:??????? " + (gameinfoList.size() - 1) + listView.getHeaderViewsCount());
                    Log.d(TAG, "onScrollStateChanged:???????gameinfoList.size()-1 " + (gameinfoList.size() - 1));
                    Log.d(TAG, "onScrollStateChanged:???????listView.getHeaderViewsCount() " + listView.getHeaderViewsCount());
                    if (lastVisiblePosition == gameinfoList.size()) {//注意这里
                        Log.d(TAG, "onScrollStateChanged:>>>>>> " + (gameinfoList.size() - 1) + listView.getHeaderViewsCount());
                        View childAt = listView.getChildAt(listView.getChildCount() - 1);
                        if (childAt.getBottom() == listView.getBottom()) {
                            page++;
                            requestNetData();
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    protected void init() {
        gameinfoList = new ArrayList<>();
        footview = getActivity().getLayoutInflater().inflate(R.layout.foot_layout_loading, listView, false);

        adapter = new CommonAdapter<Gameinfo>(getContext(), gameinfoList, R.layout.game_item_layout) {
            @Override
            public void convert(ViewHolder helper, int position, Gameinfo item) {
                helper.setImageByUrl(R.id.game_icon, item.getIcon());
                helper.setText(R.id.game_name, item.getName());
                helper.setText(R.id.game_text, item.getNumber() + "人下载" + "\t" + item.getSize());
                RatingBar ratingBar = helper.getView(R.id.game_ratingBar);
                ratingBar.setProgress(item.getScore());

            }
        };
        listView.addFooterView(footview);
        listView.setAdapter(adapter);
        listView.removeFooterView(footview);
    }

    @Override
    protected void loadData() {
        ThreadTask.getInstance().executorDBThread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Gameinfo> tempList = XutilsManager.getInstance().getDbManager().findAll(Gameinfo.class);
                    if (tempList != null) {
                        gameinfoList.addAll(tempList);
                        adapter.notifyDataSetChanged();
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        requestNetData();
                    }
                });
            }
        }, ThreadTask.ThreadPeriod.PERIOD_HIGHT);

    }
    protected void requestNetData() {
        Gamedao.request(page, new BaseCallBack() {
            @Override
            public void success(Object data) {
                swipeRefreshLayout.setRefreshing(false);
                final List<Gameinfo> temlist = (List<Gameinfo>) data;
                if (page == 1) {
                    ThreadTask.getInstance().executorDBThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                XutilsManager.getInstance().getDbManager().delete(Gameinfo.class);
                                XutilsManager.getInstance().getDbManager().save(temlist);
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                        }
                    },ThreadTask.ThreadPeriod.PERIOD_HIGHT);
                    gameinfoList.clear();
                }
                if (listView.getFooterViewsCount() == 0) {
                    listView.addFooterView(footview);
                }
                //注意这里:
                if (temlist.size() < 10) {
                    listView.removeFooterView(footview);
                    Toast.makeText(getActivity(), "数据加载完毕..", Toast.LENGTH_SHORT).show();
                }
                if (temlist != null) {
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
    protected int setLayoutId() {
        return R.layout.fragment_game;
    }
}
