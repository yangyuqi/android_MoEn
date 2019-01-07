package com.youzheng.zhejiang.robertmoog.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.jude.rollviewpager.RollPagerView;
import com.youzheng.zhejiang.robertmoog.Base.BaseFragment;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Home.activity.AttentionIntentActivity;
import com.youzheng.zhejiang.robertmoog.Home.activity.ClientViewActivity;
import com.youzheng.zhejiang.robertmoog.Home.activity.LoginActivity;
import com.youzheng.zhejiang.robertmoog.Home.activity.RegisterActivity;
import com.youzheng.zhejiang.robertmoog.Home.activity.RegisterSuccessActivity;
import com.youzheng.zhejiang.robertmoog.Home.activity.SearchGoodsActivity;
import com.youzheng.zhejiang.robertmoog.Home.activity.SetMealActivity;
import com.youzheng.zhejiang.robertmoog.Home.activity.ShopActionActivity;
import com.youzheng.zhejiang.robertmoog.Home.adapter.BannerNormalAdapter;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.Model.Home.CustomerBean;
import com.youzheng.zhejiang.robertmoog.Model.Home.HomePageBean;
import com.youzheng.zhejiang.robertmoog.Model.Home.HomePageData;
import com.youzheng.zhejiang.robertmoog.R;

import com.youzheng.zhejiang.robertmoog.Store.activity.GoodsManageActivity;
import com.youzheng.zhejiang.robertmoog.utils.CommonAdapter;
import com.youzheng.zhejiang.robertmoog.utils.SharedPreferencesUtils;
import com.youzheng.zhejiang.robertmoog.utils.ViewHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;

public class HomeFragment extends BaseFragment  implements BaseFragment.ReloadInterface {

    View mView ,getmView;
    GridView gv ;
    EditText tv_search ;
    CommonAdapter<HomeBean> adapter ;
    List<HomeBean> data = new ArrayList<>();
    RollPagerView rollPagerView ;


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
        mView.findViewById(R.id.btnBack).setVisibility(View.GONE);
        gv = mView.findViewById(R.id.gv);
        tv_search = mView.findViewById(R.id.tv_search);
        rollPagerView = mView.findViewById(R.id.rv);
        data.clear();
        data.add(new HomeBean(getString(R.string.home_gv_one),R.mipmap.group_5_3));data.add(new HomeBean(getString(R.string.home_gv_two),R.mipmap.group_5_4));data.add(new HomeBean(getString(R.string.home_gv_three),R.mipmap.group_5_5));data.add(new HomeBean(getString(R.string.home_gv_five),R.mipmap.group_5_6));data.add(new HomeBean(getString(R.string.home_gv_six),R.mipmap.group_5_7));
        adapter = new CommonAdapter<HomeBean>(mContext,data,R.layout.home_ls_item) {
            @Override
            public void convert(ViewHolder helper, HomeBean item) {
                helper.setText(R.id.tv_text,item.name);
                helper.setImageResource(R.id.iv_icon,item.icon);
                if (helper.getPosition()==2){
                    if (item.newPromotion){
                        helper.getView(R.id.iv_small).setVisibility(View.VISIBLE);
                    }else {
                        helper.getView(R.id.iv_small).setVisibility(View.GONE);
                    }
                }
                if (helper.getPosition()==4){
                    if (item.newCombo){
                        helper.getView(R.id.iv_emp).setVisibility(View.VISIBLE);
                    }else {
                        helper.getView(R.id.iv_emp).setVisibility(View.GONE);
                    }
                }
            }
        };
        gv.setAdapter(adapter);

        mView.findViewById(R.id.iv_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_search.getText().toString().equals("")){
                    showToast(getString(R.string.phone_not_null));
                    return;
                }
                Map<String,Object> map = new HashMap<>();
                map.put("phone",tv_search.getText().toString());
                OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.GET_CUSTOMER+"?access_token="+access_token, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void  onResponse(String response) {
                        BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                        if (baseModel.getCode()==PublicUtils.code){
                            CustomerBean customerBean = gson.fromJson(gson.toJson(baseModel.getDatas()),CustomerBean.class);
                            Intent intent = new Intent(mContext, RegisterSuccessActivity.class);
                            intent.putExtra("customer",customerBean.getCustomer());
                            startActivity(intent);
                        }else {
                            showToast(baseModel.getMsg());
                        }
                    }
                });

//                startActivity(new Intent(mContext, SearchGoodsActivity.class));
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initClick();

        initData();
    }

    private void initData() {
        access_token = (String) SharedPreferencesUtils.getParam(mContext, PublicUtils.access_token,"");
        if (!access_token.equals("")) {
            OkHttpClientManager.postAsynJson(gson.toJson(new HashMap<>()), UrlUtils.HOME_INFO + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                    if (baseModel.getCode()==PublicUtils.code){
                        HomePageBean homePageData = gson.fromJson(gson.toJson(baseModel.getDatas()),HomePageBean.class);
                        if (homePageData.getHomePageData().isNewPromotion()){
                            data.get(2).newPromotion=true;
                        }
                        if (homePageData.getHomePageData().isNewCombo()){
                            data.get(4).newCombo=true;
                        }
                        if (homePageData.getHomePageData().getBannerImageData().size()>0){
                            rollPagerView.setAdapter(new BannerNormalAdapter(homePageData.getHomePageData().getBannerImageData(),access_token));
                        }
                    }
                }
            });
        }
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
//                        startActivity(new Intent(mContext,LoginActivity.class));
                        startActivity(new Intent(mContext, ShopActionActivity.class));
                        break;

                    case 3 :
                        startActivity(new Intent(mContext, AttentionIntentActivity.class));
                        break;

                    case 4 :
                        startActivity(new Intent(mContext, SetMealActivity.class));
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
        boolean newPromotion ;
        boolean newCombo ;
        public HomeBean(String name, int icon){
            this.name = name;
            this.icon = icon ;
        }
    }


}
