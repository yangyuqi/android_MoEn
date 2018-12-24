package com.youzheng.zhejiang.robertmoog.Store;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.youzheng.zhejiang.robertmoog.Base.BaseFragment;
import com.youzheng.zhejiang.robertmoog.Home.HomeFragment;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.utils.CommonAdapter;
import com.youzheng.zhejiang.robertmoog.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class StoreFragment extends BaseFragment implements BaseFragment.ReloadInterface{

    View mView ;
    GridView gridView ;
    CommonAdapter<HomeBean> adapter ;
    List<HomeBean> data = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.store_fragment_layout,null);
        initView();
        setUpView(mView);
        setReloadInterface(this);
        return mView;
    }

    private void initView() {
        gridView = mView.findViewById(R.id.gv);
        data.clear();
        data.add(new HomeBean("门店客户",R.mipmap.group_7_1));data.add(new HomeBean("订单管理",R.mipmap.group_7_2));data.add(new HomeBean("商品管理",R.mipmap.group_7_3));data.add(new HomeBean("专业客户",R.mipmap.group_7_4));data.add(new HomeBean("专业客户订单",R.mipmap.group_7_5));
        data.add(new HomeBean("员工管理",R.mipmap.group_7_6));data.add(new HomeBean("出样填报",R.mipmap.group_7_7));data.add(new HomeBean("出样管理",R.mipmap.group_7_8));data.add(new HomeBean("巡店查询",R.mipmap.group_7_9));data.add(new HomeBean("退货",R.mipmap.group_7_10));
        data.add(new HomeBean("退货单管理",R.mipmap.group_7_11));data.add(new HomeBean("优惠券记录",R.mipmap.group_7_12));
        adapter = new CommonAdapter<HomeBean>(mContext,data,R.layout.home_ls_item) {
            @Override
            public void convert(ViewHolder helper,HomeBean item) {
                helper.setText(R.id.tv_text,item.name);
                helper.setImageResource(R.id.iv_icon,item.icon);
            }
        };
        gridView.setAdapter(adapter);
    }

    @Override
    public void reloadClickListener() {

    }

    public class HomeBean{
        String name ;
        int icon ;
        public HomeBean(String name, int icon){
            this.name = name;
            this.icon = icon ;
        }
    }
}
