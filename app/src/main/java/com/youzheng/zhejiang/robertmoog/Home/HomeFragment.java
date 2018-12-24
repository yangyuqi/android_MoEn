package com.youzheng.zhejiang.robertmoog.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.youzheng.zhejiang.robertmoog.Base.BaseFragment;
import com.youzheng.zhejiang.robertmoog.Home.activity.ClientViewActivity;
import com.youzheng.zhejiang.robertmoog.Home.activity.LoginActivity;
import com.youzheng.zhejiang.robertmoog.Home.activity.RegisterActivity;
import com.youzheng.zhejiang.robertmoog.Home.activity.SearchGoodsActivity;
import com.youzheng.zhejiang.robertmoog.R;

import com.youzheng.zhejiang.robertmoog.utils.CommonAdapter;
import com.youzheng.zhejiang.robertmoog.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment  implements BaseFragment.ReloadInterface {

    View mView ,getmView;
    GridView gv ;
    CommonAdapter<HomeBean> adapter ;
    List<HomeBean> data = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.home_fragment_layout,null);
        getmView = mView.findViewById(R.id.include_state);
        initView();
        setUpView(mView);
        setReloadInterface(this);
        return mView;
    }

    private void initView() {
        gv = mView.findViewById(R.id.gv);
        data.clear();
        data.add(new HomeBean(getString(R.string.home_gv_one),R.mipmap.group_5_3));data.add(new HomeBean(getString(R.string.home_gv_two),R.mipmap.group_5_4));data.add(new HomeBean(getString(R.string.home_gv_three),R.mipmap.group_5_5));data.add(new HomeBean(getString(R.string.home_gv_five),R.mipmap.group_5_6));data.add(new HomeBean(getString(R.string.home_gv_six),R.mipmap.group_5_7));
        adapter = new CommonAdapter<HomeBean>(mContext,data,R.layout.home_ls_item) {
            @Override
            public void convert(ViewHolder helper, HomeBean item) {
                helper.setText(R.id.tv_text,item.name);
                helper.setImageResource(R.id.iv_icon,item.icon);
            }
        };
        gv.setAdapter(adapter);

        mView.findViewById(R.id.iv_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, SearchGoodsActivity.class));
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initClick();
    }

    private void initClick() {
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
//                        startActivity(new Intent(mContext, LoginActivity.class));
                        startActivity(new Intent(mContext, RegisterActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mContext, ClientViewActivity.class));
                        break;

                    case 2 :
                        startActivity(new Intent(mContext,LoginActivity.class));
                        break;
                }
            }
        });
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
