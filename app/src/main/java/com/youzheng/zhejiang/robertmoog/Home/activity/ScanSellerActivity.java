package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.youzheng.zhejiang.robertmoog.R;


public class ScanSellerActivity extends AppCompatActivity  {

    private SurfaceView previewSv;

    private Rect previewFrameRect = null;
    private boolean isDecoding = false;
    private static final long VIBRATE_DURATION = 200L;
    private static final int REQUEST_CODE_ALBUM = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.scan_seller_layout);
        initView();
    }

    private void initView() {
        ((TextView)findViewById(R.id.textHeadTitle)).setText(this.getResources().getString(R.string.home_gv_two));
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.iv_next).setVisibility(View.VISIBLE);
        previewSv = (SurfaceView) findViewById(R.id.sv_preview);

    }


}
