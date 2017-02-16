package com.qianfeng.gamehelper.modules.game.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qianfeng.gamehelper.R;
import com.qianfeng.gamehelper.activity.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

 class Problem {
    private String name;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
class MyAdapter extends RecyclerView.Adapter<MyAdapter.BaseHolder>{

    private  Context context;
    private  List<Problem> list;

    public MyAdapter(Context context, List<Problem> list) {
        this.context =context;
        this.list =list;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("print", "onCreateViewHolder: >>>>>>>>1" );
        View view= LayoutInflater.from(context).inflate(R.layout.problem_layout,parent,false);
        Log.e("print", "onCreateViewHolder: >>>>>>>>1" );
        return new BaseHolder(view);
    }

    @Override
    public void onBindViewHolder(final BaseHolder holder, int position) {
        Log.e("print", "onBindViewHolder:position>> "+position );
        holder.nametext.setText(list.get(position).getName());
        holder.contenttext.setText(list.get(position).getContent());
        holder.itemView.setTag(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("print", "onClick: >>>>>>>" );
                if((Boolean) v.getTag()){
                    Log.e("print", "onClick: >>>>>>>判断一" );
                    setHeight(v,60,200);
                    holder.itemView.setTag(false);
                }else {
                    Log.e("print", "onClick: >>>>>>>判断二" );
                    setHeight(v,200,60);
                    holder.itemView.setTag(true);
                }
            }
        });
    }
    private void setHeight(final View view, final int startheight, final int endheight) {
        Log.e("print", "setHeight: >>>>>>" );
        ObjectAnimator animator=ObjectAnimator.ofFloat(this,"sss",0,1).setDuration(500);
        Log.e("print", "setHeight: >>>>>>animator" );
        final ViewGroup.LayoutParams params = view.getLayoutParams();
        Log.e("print", "setHeight: >>>>>>params" );
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                params.height= (int) (startheight+(endheight-startheight)*progress);
                view.setLayoutParams(params);
            }
        });
        animator.start();
    }

    @Override
    public int getItemCount() {
        Log.e("print", "getItemCount: "+list.size() +list.get(2).getContent());
        return list.size();
    }

    class BaseHolder extends RecyclerView.ViewHolder{
         TextView nametext,contenttext;
        public BaseHolder(View itemView) {
            super(itemView);
            nametext= (TextView) itemView.findViewById(R.id.problem_1);
            contenttext= (TextView) itemView.findViewById(R.id.content_problem);
        }
    }
}

public class GameMessageItem3 extends BaseFragment  {

    private String TAG="GameMessageItem3";
    private RecyclerView recyclerview;
    private List<Problem> list;
    private MyAdapter adapter;

    @Override
    protected void findViews(View view) {
        recyclerview = (RecyclerView) view.findViewById(R.id.recycler_View);
    }
    @Override
    protected void initEvent() {

    }
    @Override
    protected void init() {

        list = new ArrayList<>();

        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyAdapter(getContext(),list);
        recyclerview.setAdapter(adapter);
        loadmydata();
    }

    private void loadmydata() {
        Problem pro=new Problem();
        pro.setName("1.怎样才能提现?");
        pro.setContent("答:提现是必须绑定支付宝或银行卡,具体步骤:点击[商城]-[提现]-[体现到支付宝]\n" +
                "        -[支付宝帐号]进行绑定,或[商城]-[提现]-[提现到银行卡]-[选择银行卡]进行绑定.");

        list.add(pro);
        Problem pro1=new Problem();
        pro1.setName("2.如何提交截图?");
        pro1.setContent("答:提现是必须绑定支付宝或银行卡,具体步骤:点击[商城]-[提现]-[体现到支付宝]\n" +
                "        -[支付宝帐号]进行绑定,或[商城]-[提现]-[提现到银行卡]-[选择银行卡]进行绑定.");

        list.add(pro1);

        Problem pro2=new Problem();
        pro2.setName("3.解析包错误");
        pro2.setContent("答:提现是必须绑定支付宝或银行卡,具体步骤:点击[商城]-[提现]-[体现到支付宝]\n" +
                "        -[支付宝帐号]进行绑定,或[商城]-[提现]-[提现到银行卡]-[选择银行卡]进行绑定.");

        list.add(pro2);

        Problem pro3=new Problem();
        pro3.setName("4.为什么下载后显示未成功");
        pro3.setContent("答:提现是必须绑定支付宝或银行卡,具体步骤:点击[商城]-[提现]-[体现到支付宝]\n" +
                "        -[支付宝帐号]进行绑定,或[商城]-[提现]-[提现到银行卡]-[选择银行卡]进行绑定.");

        list.add(pro3);
        Log.e(TAG, "loadData:>>>>>>>> "+list.size()+list.toString() );
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void loadData() {
    }

    @Override
    protected void requestNetData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.gamemessage_item3;
    }
}
