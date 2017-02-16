package com.qianfeng.gamehelper.modules.game.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.gamehelper.R;
import com.qianfeng.gamehelper.activity.BaseFragment;
import com.qianfeng.gamehelper.i.BaseCallBack;
import com.qianfeng.gamehelper.modules.game.bean.Gameitem1info;
import com.qianfeng.gamehelper.modules.game.dao.gameitem1dao;
import com.qianfeng.gamehelper.modules.game.widget.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Administrator on 2016/10/28 0028.
 */
class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.BaseHolder>{

    private Context context;
    private List<Gameitem1info> list;
    private OnClickListener onClickListener;

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public MyAdapter1(Context context, List<Gameitem1info> list) {
        this.context =context;
        this.list =list;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("print", "onCreateViewHolder: >>>>>>>>1" );
        View view= LayoutInflater.from(context).inflate(R.layout.gameitem1_adapter,parent,false);
        Log.e("print", "onCreateViewHolder: >>>>>>>>1" );
        return new BaseHolder(view);
    }

    @Override
    public void onBindViewHolder(final BaseHolder holder, int position) {
        Log.e("print", "onBindViewHolder:position>> "+position );
            holder.nametext.setText(list.get(position).getName());
        holder.contenttext.setText(list.get(position).getNumber()+"次下载");
        Picasso.with(context).load(list.get(position).getIcon()).into(holder.imageView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickListener!=null){
                        onClickListener.onItemClick(v,holder.getLayoutPosition());
                    }
                }
            });
    }


    @Override
    public int getItemCount() {
//        Log.e("print", "getItemCount: "+list.size() +list.get(2).getContent());
        return list.size();
    }

    class BaseHolder extends RecyclerView.ViewHolder{
        TextView nametext,contenttext;
        ImageView imageView;
        public BaseHolder(View itemView) {
            super(itemView);
         nametext= (TextView) itemView.findViewById(R.id.gameitem1_title);
            contenttext= (TextView) itemView.findViewById(R.id.gameitem1_number);
            imageView= (ImageView) itemView.findViewById(R.id.gameitem1_image);
        }
    }

    interface OnClickListener{
        void onItemClick(View view, int position);
    }

}

public class GameMessageItem1 extends BaseFragment {


    private RecyclerView recyclerView;
    private  List<Gameitem1info> list;
    private  MyAdapter1 adapter;
    private TextView contexttext;

    @Override
    protected void findViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.gamemessageitem1_recyclerview);
        contexttext = (TextView) view.findViewById(R.id.gamemessage_content);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        list = new ArrayList<>();
        FullyLinearLayoutManager manager=new FullyLinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        adapter = new MyAdapter1(getContext(),list);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new MyAdapter1.OnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int itemid = Integer.parseInt(list.get(position).getId());
                Bundle bundle = new Bundle();
                bundle.putInt("id", itemid);

                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("id", itemid);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {

        Bundle bundle = getArguments();
        int id = bundle.getInt("item1id");
        String stringcontent = bundle.getString("content");
        contexttext.setText(stringcontent);
        gameitem1dao.request(id, new BaseCallBack() {
            @Override
            public void success(Object data) {

                List<Gameitem1info> temlist = (List<Gameitem1info>) data;
                if (temlist != null) {
                    list.addAll(temlist);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void failed(int errorCode, Object data) {
                Toast.makeText(getActivity(), data.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void requestNetData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.gamemessage_item1;
    }
}
