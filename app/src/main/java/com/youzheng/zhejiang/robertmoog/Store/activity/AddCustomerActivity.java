package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.view.SingleOptionsPicker;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加专业客户界面
 */
public class AddCustomerActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext,tv_degree;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 请输入手机号码
     */
    private EditText edt_phone;
    /**
     * 请输入姓名
     */
    private EditText edt_name;
    private LinearLayout lin_degree;
    /**
     * 添加
     */
    private TextView tv_add;
    private List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        textHeadTitle.setText("添加专业客户");
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_name = (EditText) findViewById(R.id.edt_name);
        lin_degree = (LinearLayout) findViewById(R.id.lin_degree);
        lin_degree.setOnClickListener(this);
        tv_add = (TextView) findViewById(R.id.tv_add);
        tv_degree= (TextView) findViewById(R.id.tv_degree);
        tv_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.lin_degree:
                list.clear();
                list.add("工长");
                list.add("设计师");
                SingleOptionsPicker.openOptionsPicker(this, list, tv_degree);
                break;
            case R.id.tv_add:
                break;
        }
    }
}
