package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Home.HomeFragment;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.utils.CommonAdapter;
import com.youzheng.zhejiang.robertmoog.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RegisterSuccessActivity extends BaseActivity {

    GridView gv ;
    CommonAdapter<HomeBean> adapter ;
    List<HomeBean> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_success_layout);
        initView();

        initEvent();
    }

    private void initEvent() {
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 3 :
                        startActivity(new Intent(mContext,LocationManageActivity.class));
                        break;
                }
            }
        });
    }

    private void initView() {
        ((TextView)findViewById(R.id.textHeadTitle)).setText("注册成功");
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gv = (GridView) findViewById(R.id.gv);
        data.clear();
//        data.add(new HomeBean("卖货",R.mipmap.group_34_2));data.add(new HomeBean("客户活动",R.mipmap.group_34_3));data.add(new HomeBean("客户账户",R.mipmap.group_34_4));data.add(new HomeBean("地址管理",R.mipmap.group_34_5));data.add(new HomeBean(getString(R.string.home_gv_six),R.mipmap.group_34_6));
        data.add(new HomeBean("卖货",R.mipmap.group_34_2));data.add(new HomeBean("客户活动",R.mipmap.group_34_3));data.add(new HomeBean("客户账户",R.mipmap.group_34_4));data.add(new HomeBean("地址管理",R.mipmap.group_34_5));
        data.add(new HomeBean("意向管理",R.mipmap.group_5_6));data.add(new HomeBean("客户订单",R.mipmap.group_35_1));data.add(new HomeBean("退货",R.mipmap.group_7_10));data.add(new HomeBean("退货单",R.mipmap.group_7_11));
        adapter = new CommonAdapter<HomeBean>(mContext,data,R.layout.home_ls_item) {
            @Override
            public void convert(ViewHolder helper, HomeBean item) {
                helper.setText(R.id.tv_text,item.name);
                helper.setImageResource(R.id.iv_icon,item.icon);
            }
        };
        gv.setAdapter(adapter);
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
