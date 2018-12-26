package com.youzheng.zhejiang.robertmoog.My;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseFragment;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.Model.login.UserConfigDataBean;
import com.youzheng.zhejiang.robertmoog.R;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Request;

public class MyFragment extends BaseFragment implements BaseFragment.ReloadInterface{

    View mView ;
    TextView tv_shop_name ,tv_role ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.my_fragment_layout,null);
        setUpView(mView);
        setReloadInterface(this);
        initView(mView);
        return mView;
    }

    private void initView(View mView) {
        tv_shop_name = mView.findViewById(R.id.tv_shop_name);
        tv_role = mView.findViewById(R.id.tv_role);
    }

    @Override
    public void onResume() {
        super.onResume();
        String token = (String) com.youzheng.tongxiang.huntingjob.UI.Utils.SharedPreferencesUtils.getParam(mContext, PublicUtils.access_token,"");
        if (!token.equals("")){
            OkHttpClientManager.postAsynJson(gson.toJson(new HashMap<>()), UrlUtils.GET_USER_ONFO + "?access_token=" + token, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                    if (baseModel.getCode()==PublicUtils.code){
                        UserConfigDataBean dataBean = gson.fromJson(gson.toJson(baseModel.getDatas()),UserConfigDataBean.class);
                        if (dataBean.getUserConfigData().getUserRole().equals("SHOP_SELLER")){
                            tv_role.setText(R.string.goods_guide);
                        }else {
                            tv_role.setText(R.string.goods_manager);
                        }
                        tv_shop_name.setText(dataBean.getUserConfigData().getShopName());
                    }
                }
            });
        }
    }

    @Override
    public void reloadClickListener() {

    }
}
