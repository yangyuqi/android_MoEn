package com.youzheng.zhejiang.robertmoog;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Count.fragment.CountFragment;
import com.youzheng.zhejiang.robertmoog.Home.HomeFragment;
import com.youzheng.zhejiang.robertmoog.My.MyFragment;
import com.youzheng.zhejiang.robertmoog.Store.fragment.StoreFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends BaseActivity  {

    FrameLayout fragmentContainer;
    RadioButton footBarHome;
    RadioButton footBarIm;
    RadioButton footBarIm2;
    RadioButton mainFootbarUser;
    RadioGroup group;
    private ArrayList<String> fragmentTags;
    private   String CURR_INDEX = "currIndex";
    private  int currIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);
        initView();

        initData(savedInstanceState);
        showFragment();
        initEvent();
    }

    private void initView() {
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        footBarHome = (RadioButton) findViewById(R.id.foot_bar_home);
        footBarIm = (RadioButton) findViewById(R.id.foot_bar_im);
        footBarIm2 = (RadioButton) findViewById(R.id.main_footbar_account);
        mainFootbarUser = (RadioButton) findViewById(R.id.main_footbar_user);
        group = (RadioGroup) findViewById(R.id.group);
    }

    private void initEvent() {
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.foot_bar_home : currIndex = 0 ;break;
                    case R.id.foot_bar_im : currIndex = 1 ;break;
                    case R.id.main_footbar_account : currIndex = 2 ;break;
                    case R.id.main_footbar_user : currIndex = 3 ;break;
                }
                showFragment();
            }
        });
    }

    private void initData(Bundle savedInstanceState) {
        fragmentTags = new ArrayList<>(Arrays.asList("HomePageFragment", "StoreFragment", "CountFragment","UserCenterFragment"));
        if(savedInstanceState != null) {
            currIndex = savedInstanceState.getInt(CURR_INDEX);
            hideSavedFragment();
        }
    }

    private void showFragment() {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment fragment = fm.findFragmentByTag(fragmentTags.get(currIndex));
        if(fragment == null) {
            fragment = instantFragment(currIndex);
        }
        for (int i = 0; i < fragmentTags.size(); i++) {
            Fragment f = fm.findFragmentByTag(fragmentTags.get(i));
            if(f != null && f.isAdded()) {
                fragmentTransaction.hide(f);
            }
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.fragment_container, fragment, fragmentTags.get(currIndex));
        }
        fragmentTransaction.commitAllowingStateLoss();
        fm.executePendingTransactions();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURR_INDEX, currIndex);
    }

    private Fragment instantFragment(int currIndex) {
        switch (currIndex) {
            case 0 :
                return new HomeFragment();
            case 1:
                return new StoreFragment();
            case 2:
                return new CountFragment();
            case 3:
                return new MyFragment();
            default:
                return null;
        }
    }

    private void hideSavedFragment() {
        Fragment fragment = fm.findFragmentByTag(fragmentTags.get(currIndex));
        if(fragment != null) {
            fm.beginTransaction().hide(fragment).commit();
        }
    }

}
