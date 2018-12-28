package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;

public class ProfessionalOrderDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBtnBack;
    /**  */
    private TextView mTextHeadTitle;
    /**  */
    private TextView mTextHeadNext;
    private ImageView mIvNext;
    private RelativeLayout mLayoutHeader;
    /**
     * 2016/01/19 09:19:04
     */
    private TextView mTvTime;
    /**
     * 987656201801020002
     */
    private TextView mTvNum;
    /**
     * 187 0000 0009
     */
    private TextView mTvReferee;
    /**
     * 李白(导购)
     */
    private TextView mTvMaker;
    /**
     * 187 0000 0009
     */
    private TextView mTvCustomer;
    /**
     * 刘思思
     */
    private TextView mTvName;
    /**
     * 187 0000 0009
     */
    private TextView mTvPhone;
    /**
     * 北京东城区内环三环银河sohoD座809
     */
    private TextView mTvAddress;
    /**
     * 安装进度
     */
    private TextView mTvProgress;
    /**
     * 已申请
     */
    private TextView mTvProgressState;
    private RecyclerView mRvList;
    /**
     * 订单促销
     */
    private TextView mTvOrderPromotion;
    /**
     * 1000
     */
    private TextView mTvOverMoney;
    /**
     * ￥100
     */
    private TextView mTvCutMoney;
    /**
     * 属于摩恩全国活动
     */
    private TextView mTvBelong;
    /**
     * 未赠送礼品
     */
    private TextView mTvOrSend;
    /**
     * 提货
     */
    private TextView mTvGet;
    /**
     * 已提货
     */
    private TextView mTvGetState;
    /**
     * 配送
     */
    private TextView mTvDispatching;
    /**
     * 大仓配送
     */
    private TextView mTvDispatchingType;
    /**
     * 收款方式
     */
    private TextView mTvGetMoney;
    /**
     * 商场付款
     */
    private TextView mTvGetMoneyType;
    /**
     * ￥16698
     */
    private TextView mTvShouldMoney;
    /**
     * -￥8
     */
    private TextView mTvCutMoneyOfJuan;
    /**
     * -￥100
     */
    private TextView mTvCutMoneyOfPromotion;
    /**
     * -￥80
     */
    private TextView mTvCutMoneyOfStore;
    /**
     * ￥17700
     */
    private TextView mTvGetMoneyOfNow;
    /**
     * 该订单是长工CC推荐来的，未结算
     */
    private TextView mTvContent;
    /**
     * 提交修改
     */
    private TextView mTvCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_order_detail);
        initView();
    }

    private void initView() {
        mBtnBack = (ImageView) findViewById(R.id.btnBack);
        mBtnBack.setOnClickListener(this);
        mTextHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        mTextHeadTitle.setText("订单详情");
        mTextHeadNext = (TextView) findViewById(R.id.textHeadNext);
        mIvNext = (ImageView) findViewById(R.id.iv_next);
        mLayoutHeader = (RelativeLayout) findViewById(R.id.layout_header);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        mTvNum = (TextView) findViewById(R.id.tv_num);
        mTvReferee = (TextView) findViewById(R.id.tv_referee);
        mTvMaker = (TextView) findViewById(R.id.tv_maker);
        mTvCustomer = (TextView) findViewById(R.id.tv_customer);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvPhone = (TextView) findViewById(R.id.tv_phone);
        mTvAddress = (TextView) findViewById(R.id.tv_address);
        mTvProgress = (TextView) findViewById(R.id.tv_progress);
        mTvProgressState = (TextView) findViewById(R.id.tv_progress_state);
        mRvList = (RecyclerView) findViewById(R.id.rv_list);
        mTvOrderPromotion = (TextView) findViewById(R.id.tv_order_promotion);
        mTvOverMoney = (TextView) findViewById(R.id.tv_over_money);
        mTvCutMoney = (TextView) findViewById(R.id.tv_cut_money);
        mTvBelong = (TextView) findViewById(R.id.tv_belong);
        mTvOrSend = (TextView) findViewById(R.id.tv_or_send);
        mTvGet = (TextView) findViewById(R.id.tv_get);
        mTvGetState = (TextView) findViewById(R.id.tv_get_state);
        mTvDispatching = (TextView) findViewById(R.id.tv_dispatching);
        mTvDispatchingType = (TextView) findViewById(R.id.tv_dispatching_type);
        mTvGetMoney = (TextView) findViewById(R.id.tv_get_money);
        mTvGetMoneyType = (TextView) findViewById(R.id.tv_get_money_type);
        mTvShouldMoney = (TextView) findViewById(R.id.tv_should_money);
        mTvCutMoneyOfJuan = (TextView) findViewById(R.id.tv_cut_money_of_juan);
        mTvCutMoneyOfPromotion = (TextView) findViewById(R.id.tv_cut_money_of_promotion);
        mTvCutMoneyOfStore = (TextView) findViewById(R.id.tv_cut_money_of_store);
        mTvGetMoneyOfNow = (TextView) findViewById(R.id.tv_get_money_of_now);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mTvCommit = (TextView) findViewById(R.id.tv_commit);
        mTvCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.tv_commit:
                break;
        }
    }
}
