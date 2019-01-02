package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.UnqualifiedAdapter;
import com.youzheng.zhejiang.robertmoog.Store.bean.CheckStoreDetail;
import com.youzheng.zhejiang.robertmoog.Store.bean.UnqualifiedContent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;

/**
 * 巡店不合格界面
 */
public class UnqualifiedActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 我我我我我我
     */
    private TextView tv_result;
    /**
     * 我我我我我我
     */
    private TextView tv_reason;
    private GridView gv_pic;
    private int patrolShopId,questionId;
    private UnqualifiedAdapter adapter;
    private List<UnqualifiedContent.PatrolShopProblemDetailBean.QuestionImagesBean> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unqualified);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("不合格原因");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        tv_result = (TextView) findViewById(R.id.tv_result);
        tv_reason = (TextView) findViewById(R.id.tv_reason);
        gv_pic = (GridView) findViewById(R.id.gv_pic);
        questionId=getIntent().getIntExtra("questionId",0);
        patrolShopId=getIntent().getIntExtra("patrolShopId",0);

        adapter=new UnqualifiedAdapter(list,this);
        gv_pic.setAdapter(adapter);

        initData();
    }

    private void initData() {
        HashMap<String,Object> map=new HashMap<>();
        map.put("questionId",questionId);
        map.put("patrolShopId",patrolShopId);

        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.UNQUALIFIED_DETAIL + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e("不合格详情",response);
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    UnqualifiedContent unqualifiedContent = gson.fromJson(gson.toJson(baseModel.getDatas()),UnqualifiedContent.class);
                    setData(unqualifiedContent);
                }
            }

        });
    }

    private void setData(UnqualifiedContent unqualifiedContent) {
        if (unqualifiedContent.getPatrolShopProblemDetail()==null) return;
        if (unqualifiedContent.getPatrolShopProblemDetail().getQuestionImages()==null) return;

        if (!unqualifiedContent.getPatrolShopProblemDetail().getQuestionName().equals("")||
                unqualifiedContent.getPatrolShopProblemDetail().getQuestionName()!=null){
            tv_result.setText(unqualifiedContent.getPatrolShopProblemDetail().getQuestionName());
        }

        if (!unqualifiedContent.getPatrolShopProblemDetail().getQuestionDes().equals("")||
                unqualifiedContent.getPatrolShopProblemDetail().getQuestionDes()!=null){
            tv_reason.setText(unqualifiedContent.getPatrolShopProblemDetail().getQuestionDes());
        }

        List<UnqualifiedContent.PatrolShopProblemDetailBean.QuestionImagesBean> beans=unqualifiedContent.getPatrolShopProblemDetail().getQuestionImages();

        if (beans.size()!=0){
            list.addAll(beans);
            adapter.setPic(beans);
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
