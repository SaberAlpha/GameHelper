package com.qianfeng.gamehelper.modules.shop.activity;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.qianfeng.gamehelper.R;
import com.qianfeng.gamehelper.activity.BaseFragment;
import com.qianfeng.gamehelper.activity.MainActivity;
import com.qianfeng.gamehelper.adapter.CommonAdapter;
import com.qianfeng.gamehelper.adapter.ViewHolder;
import com.qianfeng.gamehelper.i.BaseCallBack;
import com.qianfeng.gamehelper.modules.shop.bean.Shopinfo;
import com.qianfeng.gamehelper.modules.shop.dao.Shopdao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */
public class shopFragment extends BaseFragment{

    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CommonAdapter<Shopinfo> adapter;
    private List<Shopinfo> gameinfoList;
    private int page=1;
    private View footview;
    private View realtyview;
    private View headview;
    @Override
    protected void findViews(View view) {

        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout_shop);
        listView= (ListView) view.findViewById(R.id.list_view_shop);


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

            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState==SCROLL_STATE_IDLE){
                    int lastVisiblePosition = listView.getLastVisiblePosition();
                    if(lastVisiblePosition==(gameinfoList.size()+1)/2+listView.getHeaderViewsCount()){
                        View childAt = listView.getChildAt(listView.getChildCount() - 1);
                        if( childAt.getBottom() == listView.getBottom()){
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

//        realtyview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction transaction = getFragmentManager()
//                        .beginTransaction();
//                transaction.show(((MainActivity)getActivity()).fragmentList.get(1));
//                transaction.hide(((MainActivity)getActivity()).bmShop.getFragment());
//                transaction.commit();
//            }
//        });
    }

    @Override
    protected void init() {
        gameinfoList = new ArrayList<>();
        footview = getActivity().getLayoutInflater().inflate(R.layout.foot_layout_loading, listView, false);
        headview = getActivity().getLayoutInflater().inflate(R.layout.shop_headview, listView, false);
        adapter=new CommonAdapter<Shopinfo>(getContext(), gameinfoList,R.layout.shop_layout_item) {
            @Override
            public void convert(ViewHolder helper, int position, Shopinfo item) {

                Shopinfo leftshopinfo = gameinfoList.get(position * 2);
                helper.setImageByUrl(R.id.shop_image_left,leftshopinfo.getIcon());
                helper.setText(R.id.shop_text_left,leftshopinfo.getName());
                helper.setText(R.id.shop_remian_left,leftshopinfo.getRemain());
                helper.setText(R.id.shop_U_left,leftshopinfo.getConsume());

                if(position*2+1==gameinfoList.size()){
                    helper.getView(R.id.right_layout).setVisibility(View.INVISIBLE);

                }else{
                    Shopinfo rightshopinfo = gameinfoList.get(position * 2 + 1);
                    helper.setImageByUrl(R.id.shop_image_right,rightshopinfo.getIcon());
                    helper.setText(R.id.shop_text_right,rightshopinfo.getName());
                    helper.setText(R.id.shop_remian_right,rightshopinfo.getRemain());
                    helper.setText(R.id.shop_U_right,rightshopinfo.getConsume());
                }

            }
        };

        listView.addHeaderView(headview);
//        adapter.setCount((gameinfoList.size()+1)/2);
        listView.addFooterView(footview);
        listView.setAdapter(adapter);
        listView.removeFooterView(footview);
        realtyview = headview.findViewById(R.id.realty_view);

        realtyview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();
                transaction.show(((MainActivity)getActivity()).fragmentList.get(1));
                transaction.hide(((MainActivity)getActivity()).bmShop.getFragment());
                transaction.commit();
            }
        });
    }

    @Override
    protected void loadData() {

        Shopdao.request(page, new BaseCallBack() {
            @Override
            public void success(Object data) {
                swipeRefreshLayout.setRefreshing(false);
                List<Shopinfo> temlist= (List<Shopinfo>) data;
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
                    adapter.setCount((gameinfoList.size()+1)/2);
//                    adapter.notifyDataSetChanged();
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
        return R.layout.fragment_shop;
    }
}
