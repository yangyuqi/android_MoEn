package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.utils.MyConstant;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.ImageAdapter;
import com.youzheng.zhejiang.robertmoog.Store.utils.DepthPageTransformer;

import java.util.List;

/**
 *图片浏览器界面
 */
public class CheckPicActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 1/4
     */
    private TextView tv_num;
    private ImageView iv_more;
    private ViewPager pager;
    private List<String> list;
    private int pos;
    private ImageAdapter adapter;
    private LinearLayout lin_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_pic);
        initView();
    }

    protected void initView() {


        tv_num = (TextView) findViewById(R.id.tv_num);
        iv_more = (ImageView) findViewById(R.id.iv_more);
        iv_more.setOnClickListener(this);
        pager = (ViewPager) findViewById(R.id.pager);

        list = getIntent().getStringArrayListExtra(MyConstant.PRINT_GLANCE_OVER_LIST);
        pos = getIntent().getIntExtra(MyConstant.PRINT_GLANCE_OVER_POS, 0);

        adapter = new ImageAdapter(list, this);
        pager.setAdapter(adapter);

        pager.setCurrentItem(pos);//设置起始位置
        pager.setPageTransformer(true, new DepthPageTransformer());//修改动画效果

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {
                tv_num.setText((position + 1) + "/" + list.size());//显示当前图片的位置
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        lin_back = (LinearLayout) findViewById(R.id.lin_back);
        lin_back.setOnClickListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_more:
                break;
            case R.id.lin_back:
                finish();
                break;
        }
    }

}
