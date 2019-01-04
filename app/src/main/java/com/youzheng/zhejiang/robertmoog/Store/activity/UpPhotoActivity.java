package com.youzheng.zhejiang.robertmoog.Store.activity;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Count.bean.ShopSale;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.AddphotoAdapter;
import com.youzheng.zhejiang.robertmoog.Store.bean.SampleOutPic;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class UpPhotoActivity extends BaseCameraActivity implements View.OnClickListener, OnRecyclerViewAdapterItemClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    private GridView gv_photo;
    private List<String> list=new ArrayList<>();
    private AddphotoAdapter adapter;
    private String path;
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
    private List<String> addlist=new ArrayList<>();

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    showToast("成功");
                    break;

                case 2:
                    showToast("失败");
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_photo);
        initView();
    }



    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        textHeadNext.setOnClickListener(this);
        textHeadNext.setText("发表");
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        gv_photo = (GridView) findViewById(R.id.gv_photo);
        textHeadNext.setOnClickListener(this);

        addlist=getIntent().getStringArrayListExtra("picturePath");
    }

    @Override
    protected void setHeadIvEvenSendMine(Bitmap bm, String picturePath) {
        super.setHeadIvEvenSendMine(bm, picturePath);
        list.add(picturePath);
        adapter=new AddphotoAdapter(list,this);
        gv_photo.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        list=addlist;
        adapter=new AddphotoAdapter(list,this);
        gv_photo.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Log.e("2131",list.size()+"");


        adapter.setOnItemClickListener(this);
    }

    private void initData() {

        list.add(path);
        adapter=new AddphotoAdapter(list,this);
        gv_photo.setAdapter(adapter);
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
            case R.id.textHeadNext:
                CommitPic();
                break;
            case R.id.tv_take_photo:
                skipCamera();
                dialog.dismiss();
                break;
            case R.id.tv_find_from:
                skipPhoto();
                dialog.dismiss();
                break;
            case R.id.tv_cancel:
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
                break;
        }
    }

    private void CommitPic() {
        for (int i = 0; i <list.size() ; i++) {
            path=list.get(i);
            Log.e("集合路径",path);
        }



    }

    @Override
    public void onItemClick(View view, int position) {
        showDialog();
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    private void showDialog() {
        dialog = new Dialog(UpPhotoActivity.this, R.style.ActionDialogStyle);
        //填充对话框的布局
        inflate = (LinearLayout) LayoutInflater.from(UpPhotoActivity.this).inflate(R.layout.layout_photo, null);
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
