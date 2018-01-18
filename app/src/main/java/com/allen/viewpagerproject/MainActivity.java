package com.allen.viewpagerproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allen.viewpagerproject.utils.Const;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements PageAdapter.ItemOnclickListenler{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

   protected void initView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PageAdapter pageAdapter = new PageAdapter(this);
        pageAdapter.setItemOnclickListenler(this);
        recyclerView.setAdapter(pageAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

   }


    @Override
    public void onClickListener(View view, int position) {
//        Toast.makeText(this, "postion:"+position, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,Const.activities[position]));
    }
}

class PageAdapter extends RecyclerView.Adapter<PageAdapter.PageViewHolder>{

    public interface ItemOnclickListenler
    {
        void onClickListener(View view ,int position);

    }
ItemOnclickListenler itemOnclickListenler;

    private Context context;

    public PageAdapter(Context context){
        this.context = context;

    }

    public void setItemOnclickListenler(ItemOnclickListenler itemOnclickListenler) {
        this.itemOnclickListenler = itemOnclickListenler;
    }

    @Override
    public PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PageViewHolder holder = new PageViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final PageViewHolder holder, final int position) {
        holder.itemTxt.setText(Const.titles[position]);
        if(itemOnclickListenler!=null){

            holder.itemTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemOnclickListenler.onClickListener(holder.itemTxt,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return Const.titles.length;
    }

    class PageViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_txt)
        TextView itemTxt;

        public PageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
