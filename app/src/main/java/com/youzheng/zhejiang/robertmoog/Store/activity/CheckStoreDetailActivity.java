package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.CheckResultAdapter;
import com.youzheng.zhejiang.robertmoog.Store.adapter.CheckStoreDetailAdapter;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> list=new ArrayList<>();
    private CheckStoreDetailAdapter adapter;

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
        initData();

    }

    private void initData() {
        list.add("演示道具及设备均可正常使用");
        list.add("演示道具及设备均可正常使用");
        list.add("演示道具及设备均可正常使用");
        list.add("演示道具及设备均可正常使用");
        list.add("演示道具及设备均可正常使用");
        list.add("演示道具及设备均可正常使用");

        adapter=new CheckStoreDetailAdapter(list,this);
        lv_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
