package com.youzheng.zhejiang.robertmoog.Store.activity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;

/**
 * 出样管理界面
 */
public class SampleOutMangerActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    private LinearLayout lin_commit;
    private LinearLayout lin_history;
    private LinearLayout inflate;
    private Dialog dialog;
    /**
     * 拍摄
     */
    private TextView tv_take_photo;
    /**
     * 从手机相册选择
     */
    private TextView tv_find_from;
    /**
     * 取消
     */
    private TextView tv_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_out_manger);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("出样发布");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        lin_commit = (LinearLayout) findViewById(R.id.lin_commit);
        lin_commit.setOnClickListener(this);
        lin_history = (LinearLayout) findViewById(R.id.lin_history);
        lin_history.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.lin_commit:
                showDialog();
                break;
            case R.id.lin_history:
                startActivity(new Intent(this,SampleOutDetailActivity.class));
                break;
            case R.id.tv_take_photo:
                break;
            case R.id.tv_find_from:
                startActivity(new Intent(this,UpPhotoActivity.class));
                dialog.dismiss();
                break;
            case R.id.tv_cancel:
                break;
        }
    }


    private void showDialog() {
        dialog = new Dialog(SampleOutMangerActivity.this, R.style.ActionDialogStyle);
        //填充对话框的布局
        inflate = (LinearLayout) LayoutInflater.from(SampleOutMangerActivity.this).inflate(R.layout.layout_photo, null);
        //初始化控件
        tv_take_photo = (TextView) inflate.findViewById(R.id.tv_take_photo);
        tv_take_photo.setOnClickListener(this);
        tv_find_from = (TextView) inflate.findViewById(R.id.tv_find_from);
        tv_find_from.setOnClickListener(this);
        tv_cancel = (TextView) inflate.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = LinearLayout.LayoutParams.MATCH_PARENT;
//        lp.y = 20;//设置Dialog距离底部的距离
////       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }


}
