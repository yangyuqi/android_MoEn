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
import com.youzheng.zhejiang.robertmoog.utils.SharedPreferencesUtils;
import com.youzheng.zhejiang.robertmoog.utils.View.DeleteDialog;
import com.youzheng.zhejiang.robertmoog.utils.View.DeleteDialogInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

public class MyFragment extends BaseFragment implements BaseFragment.ReloadInterface{

    View mView ;
    TextView tv_shop_name ,tv_role ,tv_loginOut ;
    public static String shopid;

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
        tv_loginOut = mView.findViewById(R.id.tv_loginOut);
    }

    @Override
    public void onResume() {
        super.onResume();
        final String token = (String) SharedPreferencesUtils.getParam(mContext, PublicUtils.access_token,"");
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
                        shopid=dataBean.getUserConfigData().getShopId();
                        if (dataBean.getUserConfigData().getUserRole().equals("SHOP_SELLER")){
                            tv_role.setText(R.string.goods_guide);
                        }else {
                            tv_role.setText(R.string.goods_manager);
                        }
                        tv_shop_name.setText(dataBean.getUserConfigData().getShopName());
                    }
                }

            });

            tv_loginOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteDialog deleteDialog = new DeleteDialog(mContext, "提示", "是否退出登录", "确定");
                    deleteDialog.show();
                    deleteDialog.OnDeleteBtn(new DeleteDialogInterface() {
                        @Override
                        public void isDelete(boolean isdelete) {
                            OkHttpClientManager.postAsynJson(gson.toJson(new HashMap<>()), UrlUtils.LOGIN_OUT + "?access_token=" + token, new OkHttpClientManager.StringCallback() {
                                @Override
                                public void onFailure(Request request, IOException e) {

                                }

                                @Override
                                public void onResponse(String response) {
                                    BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                                    if (baseModel.getCode()==PublicUtils.code){
                                        SharedPreferencesUtils.clear(mContext);
                                        getActivity().finish();
                                    }else {
                                        showToast(baseModel.getMsg());
                                    }
                                }
                            });
                        }
                    });
                }
            });
        }
    }

    @Override
    public void reloadClickListener() {

    }
}
