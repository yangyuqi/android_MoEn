package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.PeopleMangerAdapter;
import com.youzheng.zhejiang.robertmoog.Store.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**|
 * 员工管理界面
 */
public class PeopleMangerActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    private MyListView lv_list;
    /**
     * 添加导购
     */
    private TextView tv_add;
    private List<String> list=new ArrayList<>();
    private PeopleMangerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_manger);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("门店管理");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        lv_list = (MyListView) findViewById(R.id.lv_list);
        tv_add = (TextView) findViewById(R.id.tv_add);
        tv_add.setOnClickListener(this);
        initData();
    }

    private void initData() {
        list.add("店长");
        list.add("店长");
        list.add("店长");
        list.add("店长");
        list.add("店长");
        list.add("店长");

        adapter=new PeopleMangerAdapter(list,this);
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
            case R.id.tv_add:
                startActivity(new Intent(this,AddStaffActivity.class));
                break;
        }
    }
}
