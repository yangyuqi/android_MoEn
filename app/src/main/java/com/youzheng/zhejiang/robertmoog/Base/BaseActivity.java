package com.youzheng.zhejiang.robertmoog.Base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.utils.ActivityStack;
import com.youzheng.zhejiang.robertmoog.utils.SharedPreferencesUtils;

public class BaseActivity extends AppCompatActivity {


    LinearLayout mRootBaseView;//根布局

    View mStateLayout;//include的state_layout
    LinearLayout ll_page_state_error;//stateLayout网络错误的布局
    LinearLayout ll_page_state_empty;//stateLayout无数据的布局
    Button btReload;//网络错误重新加载的布局

    protected Context mContext ;
    protected FragmentManager fm;
    protected Gson gson ;
    protected String access_token ,role;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        fm = getSupportFragmentManager();
        mContext = this ;
        gson = new Gson();
        access_token = (String) SharedPreferencesUtils.getParam(mContext, PublicUtils.access_token,"");
        role = (String) SharedPreferencesUtils.getParam(mContext,PublicUtils.role,"");
        ActivityStack.getScreenManager().pushActivity(this);
        initBaseView();
    }

    public void initBaseView() {
        mRootBaseView = (LinearLayout) findViewById(R.id.activity_base_root);
        mStateLayout = findViewById(R.id.activity_base_state_layout);
        btReload = (Button) findViewById(R.id.state_layout_error_bt);
        ll_page_state_empty = (LinearLayout) findViewById(R.id.state_layout_empty);
        ll_page_state_error = (LinearLayout) findViewById(R.id.state_layout_error);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        if (null != mRootBaseView) {
            mRootBaseView.addView(view, lp);
        }
    }


    public void changePageState(PageState pageState) {
        switch (pageState) {
            case NORMAL:
                if (mStateLayout.getVisibility() == View.VISIBLE) {
                    mStateLayout.setVisibility(View.GONE);
                }
                break;
            case ERROR:
                if (mStateLayout.getVisibility() == View.GONE) {
                    mStateLayout.setVisibility(View.VISIBLE);
                    ll_page_state_error.setVisibility(View.VISIBLE);
                    btReload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            reloadInterface.reloadClickListener();
                        }
                    });
                    ll_page_state_empty.setVisibility(View.GONE);
                }
                break;
            case EMPTY:
                if (mStateLayout.getVisibility() == View.GONE) {
                    mStateLayout.setVisibility(View.VISIBLE);
                    ll_page_state_error.setVisibility(View.GONE);
                    ll_page_state_empty.setVisibility(View.VISIBLE);
                }
                break;
        }


    }

    //网络错误重新加载的接口
    public ReloadInterface reloadInterface;

    public void setReloadInterface(ReloadInterface reloadInterface) {
        this.reloadInterface = reloadInterface;
    }

    public interface ReloadInterface {
        void reloadClickListener();
    }

    public enum PageState {
        /**
         * 数据内容显示正常
         */
        NORMAL,
        /**
         * 数据为空
         */
        EMPTY,
        /**
         * 数据加载失败
         */
        ERROR
    }

    @Override
    protected void onDestroy() {
        ActivityStack.getScreenManager().popActivity(this);
        super.onDestroy();
    }

    protected void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
