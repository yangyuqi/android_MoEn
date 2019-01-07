package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.bean.CustomerList;

import java.util.List;

public class StoreCustomerAdapter extends BaseAdapter {
    private List<CustomerList.CoustomerListBean> list;
    private LayoutInflater layoutInflater;
    private Context context;

    public StoreCustomerAdapter(List<CustomerList.CoustomerListBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public void setListRefreshUi(List<CustomerList.CoustomerListBean> list){
        this.list = list;
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
            convertView=layoutInflater.inflate(R.layout.item_store_customer_list,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_date=convertView.findViewById(R.id.tv_date);
            viewHolder.tv_number=convertView.findViewById(R.id.tv_number);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        CustomerList.CoustomerListBean coustomerListBean=list.get(position);
        viewHolder.tv_date.setText(coustomerListBean.getMonth());
        viewHolder.tv_number.setText(coustomerListBean.getNum()+"");
        return convertView;
    }

    class ViewHolder{
        private TextView tv_date,tv_number;
    }
}
