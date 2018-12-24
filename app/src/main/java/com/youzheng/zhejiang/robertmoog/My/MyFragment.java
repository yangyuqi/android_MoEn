package com.youzheng.zhejiang.robertmoog.My;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youzheng.zhejiang.robertmoog.Base.BaseFragment;
import com.youzheng.zhejiang.robertmoog.R;

public class MyFragment extends BaseFragment implements BaseFragment.ReloadInterface{

    View mView ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.my_fragment_layout,null);
        setUpView(mView);
        setReloadInterface(this);
        return mView;
    }

    @Override
    public void reloadClickListener() {

    }
}
