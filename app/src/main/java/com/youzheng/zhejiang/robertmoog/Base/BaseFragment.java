package com.youzheng.zhejiang.robertmoog.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.R;

public class BaseFragment extends Fragment {

    protected Context mContext ;
    protected Gson gson ;
    protected View allView ;

    LinearLayout mRootBaseView;//根布局
    View mStateLayout;//include的state_layout
    LinearLayout ll_page_state_error;//stateLayout网络错误的布局
    LinearLayout ll_page_state_empty;//stateLayout无数据的布局
    Button btReload;//网络错误重新加载的布局

    protected String access_token ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        gson = new Gson();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    public void setUpView(View view){
        mRootBaseView = view.findViewById(R.id.activity_base_root);
        mStateLayout = view.findViewById(R.id.activity_base_state_layout);
        btReload = view.findViewById(R.id.state_layout_error_bt);
        ll_page_state_empty = view. findViewById(R.id.state_layout_empty);
        ll_page_state_error = view.findViewById(R.id.state_layout_error);
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

    protected void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

}
