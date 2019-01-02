package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.CheckResultAdapter;
import com.youzheng.zhejiang.robertmoog.Store.adapter.CheckStoreDetailAdapter;
import com.youzheng.zhejiang.robertmoog.Store.bean.CheckStoreDetail;
import com.youzheng.zhejiang.robertmoog.Store.bean.CheckStoreList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;

/**
 * 巡店列表详情界面
 */
public class CheckStoreDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    private ListView lv_list;
    /**
     * 整体合规，但需要注意展厅整洁问题
     */
    private TextView tv_result_content;
    private List<CheckStoreDetail.PatrolShopDetailBean> list=new ArrayList<>();
    private CheckStoreDetailAdapter adapter;
    private int checkid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_store_detail);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("巡店结果");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        lv_list = (ListView) findViewById(R.id.lv_list);
        tv_result_content = (TextView) findViewById(R.id.tv_result_content);
        checkid=getIntent().getIntExtra("checkID",0);

        adapter=new CheckStoreDetailAdapter(list,this);
        lv_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        initData();

    }

    private void initData() {
        HashMap<String,Object> map=new HashMap<>();
        map.put("patrolShopId",checkid);

        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.CHECK_STORE_DETAIL + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e("巡店详情",response);

                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    CheckStoreDetail checkStoreDetail = gson.fromJson(gson.toJson(baseModel.getDatas()),CheckStoreDetail.class);
                    setData(checkStoreDetail);
                }
            }

        });


    }

    private void setData(CheckStoreDetail checkStoreDetail) {
        if (checkStoreDetail.getPatrolShopDetail()==null) return;
        if (!checkStoreDetail.getRemarks().equals("")||checkStoreDetail.getRemarks()==null){
            tv_result_content.setText(checkStoreDetail.getRemarks());
        }else {
            tv_result_content.setText("无");
        }

        List<CheckStoreDetail.PatrolShopDetailBean> beanList=checkStoreDetail.getPatrolShopDetail();
        if (beanList.size()!=0){
            list.addAll(beanList);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnBack:
                finish();
                break;
        }
    }
}
