package com.youzheng.zhejiang.robertmoog.Count;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youzheng.zhejiang.robertmoog.Base.BaseFragment;
import com.youzheng.zhejiang.robertmoog.R;

public class CountFragment extends BaseFragment implements BaseFragment.ReloadInterface{

    View mView ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.count_fragment_layout,null);
        setUpView(mView);
        initView();
        setReloadInterface(this);
        return mView;
    }

    private void initView() {

    }

    @Override
    public void reloadClickListener() {

    }


}
