package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Home.adapter.CommonAdapter;
import com.youzheng.zhejiang.robertmoog.Home.adapter.CustomerGoodsAdapter;
import com.youzheng.zhejiang.robertmoog.Home.adapter.ViewHolder;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.Model.Home.CustomerIntentDataBean;
import com.youzheng.zhejiang.robertmoog.Model.Home.IntentProductList;
import com.youzheng.zhejiang.robertmoog.Model.Home.ShopPersonalListBean;
import com.youzheng.zhejiang.robertmoog.Model.login.RegisterBean;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.utils.QRcode.android.CaptureActivity;
import com.youzheng.zhejiang.robertmoog.utils.View.DeleteDialog;
import com.youzheng.zhejiang.robertmoog.utils.View.DeleteDialogInterface;
import com.youzheng.zhejiang.robertmoog.utils.View.RemarkDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import rx.functions.Action1;

public class AttentionGoodsActivity extends BaseActivity {


    ListView ls ;
    RegisterBean registerBean ;
    ShopPersonalListBean bean ;
    CommonAdapter<IntentProductList> adapter ;
    TextView tv_attention ,tv_update_intent;
    int widWidth ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attention_goods_layout);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        widWidth = outMetrics.widthPixels;
        initView();
        bean = (ShopPersonalListBean) getIntent().getSerializableExtra("label");
        registerBean = (RegisterBean) getIntent().getSerializableExtra("registerBean");
        initData();

        initClick();
    }

    private void initClick() {
        ((ImageView)findViewById(R.id.iv_next)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPermissions permissions = new RxPermissions(AttentionGoodsActivity.this);
                permissions.request(Manifest.permission.CAMERA,Manifest.permission.VIBRATE).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean){
                            Intent intent = new Intent(mContext, CaptureActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }

    private void initData() {
        Map<String,Object> map = new HashMap<>();
        if (bean!=null) {
            map.put("id", bean.getCustomerId());
        }
        if (registerBean!=null){
            map.put("id",registerBean.getCustomerId());
        }
        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.ATTENTION_GOODS_LIST + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()== PublicUtils.code){
                    final CustomerIntentDataBean intentDataBean = gson.fromJson(gson.toJson(baseModel.getDatas()),CustomerIntentDataBean.class);
                    if (intentDataBean.getRemark()!=null){
                        tv_attention.setText(intentDataBean.getRemark());
                    }
                    if (intentDataBean.getIntentProductList().size()>0){
                        adapter.setData(intentDataBean.getIntentProductList());
                        adapter.notifyDataSetChanged();
                    }
                    tv_update_intent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RemarkDialog remarkDialog = new RemarkDialog(mContext,intentDataBean.getId(),intentDataBean.getRemark());
                            remarkDialog.show();
                        }
                    });

                }
            }
        });
    }

    private void initView() {
        ((TextView)findViewById(R.id.textHeadTitle)).setText("意向商品");
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.iv_next).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.iv_next)).setImageResource(R.mipmap.group_75_1);
        ls = (ListView) findViewById(R.id.ls);
        tv_attention = findViewById(R.id.tv_attention);
        tv_update_intent = findViewById(R.id.tv_update_intent);
        adapter = new CommonAdapter<IntentProductList>(mContext,new ArrayList<IntentProductList>(),R.layout.common_goods_vh_item) {
            @Override
            public void convert(ViewHolder helper, final IntentProductList item) {
                helper.setText(R.id.tv_name,item.getName());
                helper.setText(R.id.tv_desc,item.getSku());
                helper.setText(R.id.tv_price,R.string.label_money+""+item.getPrice());
                Glide.with(mContext).load(item.getPhoto()).into((ImageView) helper.getView(R.id.iv_icon));
                RelativeLayout view = helper.getView(R.id.rl_width);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
                params.width = (int) PublicUtils.dip2px(PublicUtils.px2dip(widWidth));
                view.setLayoutParams(params);

                helper.getView(R.id.main_right_drawer_layout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DeleteDialog dialog = new DeleteDialog(mContext,"提示","是否删除此意向商品","确定");
                        dialog.show();
                        dialog.OnDeleteBtn(new DeleteDialogInterface() {
                            @Override
                            public void isDelete(boolean isdelete) {
                                Map<String,Object> map = new HashMap<>();
                                map.put("id",item.getId());
                                OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.DELETE_GOODS + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
                                    @Override
                                    public void onFailure(Request request, IOException e) {

                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                                        if (baseModel.getCode()==PublicUtils.code){
                                            initData();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }
        };

        ls.setAdapter(adapter);
    }
}
