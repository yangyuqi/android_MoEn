package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.GoodsPagerAdapter;
import com.youzheng.zhejiang.robertmoog.Store.adapter.GoodsTitleAdapter;
import com.youzheng.zhejiang.robertmoog.Store.fragment.GoodsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品管理界面
 */
public class GoodsManageActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 输入商品SKU
     */
    private EditText tv_search;
    private ImageView iv_search;
    private TabLayout tab;
    private ImageView iv_more;
    private ViewPager pager;
    private List<Fragment> list = new ArrayList<>();
    private GoodsPagerAdapter adapter;
    private GridView mGvTitle;
    private PopupWindow window;
    private List<String> stringList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_manage);
        initView();

    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        textHeadTitle.setText("商品列表");
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        tv_search = (EditText) findViewById(R.id.tv_search);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        iv_search.setOnClickListener(this);
        tab = (TabLayout) findViewById(R.id.tab);
        iv_more = (ImageView) findViewById(R.id.iv_more);
        iv_more.setOnClickListener(this);
        pager = (ViewPager) findViewById(R.id.pager);
        btnBack.setOnClickListener(this);

        initData();

    }

    private void initData() {
        list.add(new GoodsFragment());
        list.add(new GoodsFragment());
        list.add(new GoodsFragment());
        list.add(new GoodsFragment());
        list.add(new GoodsFragment());
        list.add(new GoodsFragment());
        list.add(new GoodsFragment());

        FragmentManager fm = getSupportFragmentManager();
        adapter = new GoodsPagerAdapter(fm, list);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(6);

        tab.setupWithViewPager(pager);
        //默认选中
        tab.getTabAt(0).select();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_search:
                break;
            case R.id.iv_more:
                showPopuWindow();
                break;
            case R.id.btnBack:
                finish();
                break;
        }
    }

    private void showPopuWindow() {
        View inflate = getLayoutInflater().inflate(R.layout.popuwindow_goods_title, null);
        mGvTitle = (GridView)inflate.findViewById(R.id.gv_title);
        stringList.clear();
        stringList.add("全部");
        stringList.add("全部");
        stringList.add("全部");
        stringList.add("全部");
        stringList.add("全部");
        stringList.add("全部");

        final GoodsTitleAdapter goodsTitleAdapter=new GoodsTitleAdapter(stringList,this);
        mGvTitle.setAdapter(goodsTitleAdapter);
        goodsTitleAdapter.notifyDataSetChanged();

        mGvTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goodsTitleAdapter.setSelectItem(position);
            }
        });
        window = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        window.setAnimationStyle(R.style.ActionDialogStyle);

        window.setBackgroundDrawable(getDrawable());
        window.setTouchable(true); // 设置popupwindow可点击
        window.setOutsideTouchable(true); // 设置popupwindow外部可点击
        window.showAsDropDown(tab);
        window.update();

    }
    //获取屏幕的高度
    public static int getScreenHeight(Activity context) {
        WindowManager manager = context.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        return height;

    }
    /**
     * 生成一个 透明的背景图片
     *
     * @return
     */
    private Drawable getDrawable() {
        ShapeDrawable bgdrawable = new ShapeDrawable(new OvalShape());
        bgdrawable.getPaint().setColor(this.getResources().getColor(android.R.color.transparent));
        return bgdrawable;
    }
}
