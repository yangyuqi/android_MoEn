package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;

public class SampleOutEditActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 浴室柜组数
     */
    private TextView tv_yu_shi_gui_num;
    /**
     * 请输入数量
     */
    private EditText et_number1;
    /**
     * 垃圾处理器出样数量
     */
    private TextView tv_la_ji_num;
    /**
     * 请输入数量
     */
    private EditText et_number2;
    /**
     * 陶瓷坐便器出样坑位数量
     */
    private TextView tv_tao_ci_num;
    /**
     * 请输入数量
     */
    private EditText et_number3;
    /**
     * 智能一体机出样坑位数量
     */
    private TextView tv_zhi_neng_num;
    /**
     * 请输入数量
     */
    private EditText et_number4;
    /**
     * 智能盖板出样数量
     */
    private TextView tv_gai_ban_num;
    /**
     * 请输入数量
     */
    private EditText et_number5;
    /**
     * 陶瓷脸盆出样数量
     */
    private TextView tv_lian_pen_num;
    /**
     * 请输入数量
     */
    private EditText et_number6;
    /**
     * 净水器出样数量
     */
    private TextView tv_jing_shui_num;
    /**
     * 请输入数量
     */
    private EditText et_number7;
    /**
     * 雨林试水台 - 旧款
     */
    private TextView tv_rain_old_num;
    /**
     * 请输入数量
     */
    private EditText et_number8;
    /**
     * 雨林试水台 - 新款
     */
    private TextView tv_rain_new_num;
    /**
     * 请输入数量
     */
    private EditText et_number9;
    /**
     * 小试水台
     */
    private TextView tv_small_rain_num;
    /**
     * 请输入数量
     */
    private EditText et_number10;
    /**
     * 厨盆试水台
     */
    private TextView tv_cu_pen_rain_num;
    /**
     * 请输入数量
     */
    private EditText et_number11;
    /**
     * VI4.0试水台
     */
    private TextView tv_VI4_rain_num;
    /**
     * 请输入数量
     */
    private EditText et_number12;
    /**
     * 提交
     */
    private TextView tv_commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_out_edit);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("出样信息");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        tv_yu_shi_gui_num = (TextView) findViewById(R.id.tv_yu_shi_gui_num);
        et_number1 = (EditText) findViewById(R.id.et_number1);
        tv_la_ji_num = (TextView) findViewById(R.id.tv_la_ji_num);
        et_number2 = (EditText) findViewById(R.id.et_number2);
        tv_tao_ci_num = (TextView) findViewById(R.id.tv_tao_ci_num);
        et_number3 = (EditText) findViewById(R.id.et_number3);
        tv_zhi_neng_num = (TextView) findViewById(R.id.tv_zhi_neng_num);
        et_number4 = (EditText) findViewById(R.id.et_number4);
        tv_gai_ban_num = (TextView) findViewById(R.id.tv_gai_ban_num);
        et_number5 = (EditText) findViewById(R.id.et_number5);
        tv_lian_pen_num = (TextView) findViewById(R.id.tv_lian_pen_num);
        et_number6 = (EditText) findViewById(R.id.et_number6);
        tv_jing_shui_num = (TextView) findViewById(R.id.tv_jing_shui_num);
        et_number7 = (EditText) findViewById(R.id.et_number7);
        tv_rain_old_num = (TextView) findViewById(R.id.tv_rain_old_num);
        et_number8 = (EditText) findViewById(R.id.et_number8);
        tv_rain_new_num = (TextView) findViewById(R.id.tv_rain_new_num);
        et_number9 = (EditText) findViewById(R.id.et_number9);
        tv_small_rain_num = (TextView) findViewById(R.id.tv_small_rain_num);
        et_number10 = (EditText) findViewById(R.id.et_number10);
        tv_cu_pen_rain_num = (TextView) findViewById(R.id.tv_cu_pen_rain_num);
        et_number11 = (EditText) findViewById(R.id.et_number11);
        tv_VI4_rain_num = (TextView) findViewById(R.id.tv_VI4_rain_num);
        et_number12 = (EditText) findViewById(R.id.et_number12);
        tv_commit = (TextView) findViewById(R.id.tv_commit);
        tv_commit.setOnClickListener(this);
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
