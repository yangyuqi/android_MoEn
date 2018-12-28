package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.bean.AddProfessionalCustomerRequest;
import com.youzheng.zhejiang.robertmoog.Store.view.SingleOptionsPicker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;

/**
 * 添加专业客户界面
 */
public class AddCustomerActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

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
    private String phone,name,id;
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

        edt_name.addTextChangedListener(this);
        edt_phone.addTextChangedListener(this);
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
                phone=edt_phone.getText().toString().trim();
                name=edt_name.getText().toString().trim();
                if (phone.equals("")){
                    showToast("手机号未填写");
                }else if (name.equals("")){
                    showToast("姓名未填写");
                }else if (tv_degree.getText().toString().equals("请选择身份")||tv_degree.getText().toString().equals("")){
                    showToast("未选择身份");
                }else {
                    if (tv_degree.getText().toString().equals("工长")){
                        id="FOREMAN";
                    }else if (tv_degree.getText().toString().equals("设计师")){
                        id="DESIGNER";
                    }
                    addProfessionalCustomer(phone,name,id);
                }
                break;
        }
    }

    private void  addProfessionalCustomer(String phone, String name,String id){
        HashMap<String,Object> map=new HashMap<>();
        map.put("custCode",phone);
        map.put("custName",name);
        AddProfessionalCustomerRequest request=new AddProfessionalCustomerRequest();
        request.setId(id);
        map.put("career",request);

        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.ADD_PROFESSIONAL_CUSTOMER + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e("添加专业客户",response);
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel!=null){
                    if (baseModel.getCode()==PublicUtils.code){
                        finish();
                    }else {
                        if (!baseModel.getMsg().equals("")){
                            showToast(baseModel.getMsg());
                        }
                    }

                }

            }
        });
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if (edt_phone.getText().toString().equals("")||edt_name.getText().toString().equals("")){


        }else {


        }

    }
}
