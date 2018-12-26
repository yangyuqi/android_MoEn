package com.youzheng.zhejiang.robertmoog.Store.activity;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.IconHintView;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;

/**
 * 商品详情界面
 */
public class GoodsDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    private RollPagerView pagerView;
    /**
     * 987656201801020002
     */
    private TextView tv_goods_number;
    /**
     * 摩恩淋浴花洒龙头套装pro 淋雨喷头卫生间 浴室冷热水龙头沐浴器
     */
    private TextView tv_goods_content;
    /**
     * ￥123
     */
    private TextView tv_goods_money;
    /**
     * 品类 XXX
     */
    private TextView tv_type;
    /**
     * 品类 XXX
     */
    private TextView tv_series;
    /**
     * 品类 XXX
     */
    private TextView tv_spec;
    /**
     * 品类 XXX
     */
    private TextView tv_marketing_unit;
    /**
     * 品类 XXX
     */
    private TextView tv_value_of_moen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        textHeadTitle.setText("商品详情");
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        pagerView = (RollPagerView) findViewById(R.id.pagerView);
        tv_goods_number = (TextView) findViewById(R.id.tv_goods_number);
        tv_goods_content = (TextView) findViewById(R.id.tv_goods_content);
        tv_goods_money = (TextView) findViewById(R.id.tv_goods_money);
        tv_type = (TextView) findViewById(R.id.tv_type);
        tv_series = (TextView) findViewById(R.id.tv_series);
        tv_spec = (TextView) findViewById(R.id.tv_spec);
        tv_marketing_unit = (TextView) findViewById(R.id.tv_marketing_unit);
        tv_value_of_moen = (TextView) findViewById(R.id.tv_value_of_moen);

        initPagaer();
    }

    //设置轮播图
    private void initPagaer() {
        pagerView.setPlayDelay(2000);
        pagerView.setAnimationDurtion(500);
        //设置适配器
        pagerView.setAdapter(new TestNormalAdapter(pagerView));
        //自定义指示器图片
        pagerView.setHintView(new IconHintView(this, R.mipmap.group_72_2, R.mipmap.group_72_1));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnBack:
                finish();
                break;
        }
    }

    private class TestNormalAdapter extends LoopPagerAdapter {
        private int[] imgs = { //轮播的图片
                R.mipmap.pg,
                R.mipmap.pg,
                R.mipmap.pg,
                R.mipmap.pg,
                R.mipmap.pg,
        };
        public TestNormalAdapter(RollPagerView viewPager) {
            super(viewPager);
        }


        @Override
        public View getView(ViewGroup container, final int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //点击事件   具体点击了哪一张图片的下标
//                    Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }

        @Override
        public int getRealCount() {
            return imgs.length;
        }
    }

}
