package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.activity.UnqualifiedActivity;
import com.youzheng.zhejiang.robertmoog.Store.bean.CheckStoreDetail;

import java.util.List;

public class CheckStoreDetailAdapter extends BaseAdapter {
    private List<CheckStoreDetail.PatrolShopDetailBean> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public CheckStoreDetailAdapter(List<CheckStoreDetail.PatrolShopDetailBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public void setUI(List<CheckStoreDetail.PatrolShopDetailBean> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.item_result_detail,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_content=convertView.findViewById(R.id.tv_content);
            viewHolder.tv_yes_or_no=convertView.findViewById(R.id.tv_yes_or_no);
            viewHolder.iv_ok=convertView.findViewById(R.id.iv_ok);
            viewHolder.iv_no=convertView.findViewById(R.id.iv_no);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        final CheckStoreDetail.PatrolShopDetailBean bean=list.get(position);
        viewHolder.tv_content.setText(bean.getQuestionName());
        if (bean.getQuestionStatus().getId().equals("QUALIFIED")){
            viewHolder.iv_ok.setImageResource(R.mipmap.group_100_2);
            viewHolder.iv_no.setVisibility(View.GONE);
        }else {
            viewHolder.iv_ok.setImageResource(R.mipmap.group_90_3);
            viewHolder.iv_no.setVisibility(View.VISIBLE);
        }
        viewHolder.tv_yes_or_no.setText(bean.getQuestionStatus().getDes());

        viewHolder.iv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,UnqualifiedActivity.class);
                intent.putExtra("questionId",bean.getQuestionId());
                intent.putExtra("patrolShopId",bean.getPatrolShopId());
                context.startActivity(intent);
            }
        });


        return convertView;
    }
    class ViewHolder{
       private TextView tv_content,tv_yes_or_no;
       private ImageView iv_ok,iv_no;
    }
}
