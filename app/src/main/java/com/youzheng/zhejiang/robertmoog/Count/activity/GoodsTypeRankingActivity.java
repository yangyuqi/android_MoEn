package com.youzheng.zhejiang.robertmoog.Count.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Count.adapter.CheckRuleAdapter;
import com.youzheng.zhejiang.robertmoog.Count.adapter.GoodsTypeRankingAdapter;
import com.youzheng.zhejiang.robertmoog.Count.bean.GoodsTypeRankingList;
import com.youzheng.zhejiang.robertmoog.Count.bean.MealRankingList;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;
import com.youzheng.zhejiang.robertmoog.Store.view.RecycleViewDivider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;

/**
 * 商品品类排名界面
 */
public class GoodsTypeRankingActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 请选择时间
     */
    private TextView tv_startDate;
    /**
     * 请选择时间
     */
    private TextView tv_endDate;
    /**
     * 查询
     */
    private TextView tv_check;
    private LinearLayout lin_search;
    /**
     * 销售金额
     */
    private TextView tv_rule;
    private ImageView iv_more;
    private LinearLayout lin_title;
    private PullLoadMoreRecyclerView pr_list;
    private TimePickerView pvTime;
    private String time="";
    private int who;
    private PopupWindow window;
    private ListView listView;
    private CheckRuleAdapter ruleAdapter;
    private List<GoodsTypeRankingList.CategoryListBean> list=new ArrayList<>();
    private GoodsTypeRankingAdapter adapter;

    private int page=1;
    private int pageSize=10;
    private boolean isDay=true;
    private String startstr="";
    private String endstr="";
    private String rulestr="COUNT";//默认是数量
    private String type="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_type_ranking);
        initView();
        setListener();
        initTimer();
    }

    private void setListener() {
        pr_list.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page=1;
                list.clear();
                initData(page,pageSize,isDay,startstr,endstr,rulestr);
            }

            @Override
            public void onLoadMore() {
               page++;
                initData(page,pageSize,isDay,startstr,endstr,rulestr);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(page,pageSize,isDay,startstr,endstr,rulestr);
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("商品品类排名");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        tv_startDate = (TextView) findViewById(R.id.tv_startDate);
        tv_startDate.setOnClickListener(this);
        tv_endDate = (TextView) findViewById(R.id.tv_endDate);
        tv_endDate.setOnClickListener(this);
        tv_check = (TextView) findViewById(R.id.tv_check);
        tv_check.setOnClickListener(this);
        lin_search = (LinearLayout) findViewById(R.id.lin_search);
        tv_rule = (TextView) findViewById(R.id.tv_rule);
        tv_rule.setOnClickListener(this);
        iv_more = (ImageView) findViewById(R.id.iv_more);
        iv_more.setOnClickListener(this);
        lin_title = (LinearLayout) findViewById(R.id.lin_title);
        pr_list = (PullLoadMoreRecyclerView) findViewById(R.id.pr_list);
        pr_list.setLinearLayout();
        pr_list.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 5, getResources().getColor(R.color.divider_color_item)));
        pr_list.setColorSchemeResources(R.color.colorPrimary);
        adapter=new GoodsTypeRankingAdapter(list,this);
        pr_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new OnRecyclerViewAdapterItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (tv_rule.getText().toString().equals("销售数量")){
                    type="数量";
                }else {
                    type="金额";
                }
                Intent intent=new Intent(GoodsTypeRankingActivity.this,GoodsTypeRankingDetailActivity.class);
                intent.putExtra("type",type);
                intent.putExtra("goodsId",list.get(position).getId());
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    private void initData(int page, int pageSize, boolean isDay, String startDate, String endDate, String rule) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("pageNum",page);
        map.put("pageSize",pageSize);
        map.put("isDay",isDay);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("rule",rule);

        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.GOODS_TYPE_RANKING_LIST + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                pr_list.setPullLoadMoreCompleted();
            }

            @Override
            public void onResponse(String response) {
                Log.e("商品品类排名",response);
                pr_list.setPullLoadMoreCompleted();
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    GoodsTypeRankingList goodsTypeRankingList = gson.fromJson(gson.toJson(baseModel.getDatas()),GoodsTypeRankingList.class);
                    setData(goodsTypeRankingList);
                }
            }
        });




    }

    private void setData(GoodsTypeRankingList goodsTypeRankingList) {
        if (goodsTypeRankingList==null) return;
        if (goodsTypeRankingList.getCategoryList()==null) return;

        List<GoodsTypeRankingList.CategoryListBean> beanList=goodsTypeRankingList.getCategoryList();
        if (beanList.size()!=0){
            list.addAll(beanList);
            adapter.setUI(beanList);
        }else {
            showToast(getString(R.string.load_list_erron));
        }
        pr_list.setPullLoadMoreCompleted();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.tv_startDate:
                who=1;
                pvTime.show(v, false);
                break;
            case R.id.tv_endDate:
                who=2;
                pvTime.show(v, false);
                break;
            case R.id.tv_check:
                isDay=false;
                list.clear();
                initData(page,pageSize,isDay,startstr,endstr,rulestr);
                break;
            case R.id.iv_more:
                if (tv_rule.getText().toString().equals("销售数量")){
                    showPopuWindow(0);
                }else if (tv_rule.getText().toString().equals("销售金额")){
                    showPopuWindow(1);
                }
                break;
        }
    }

    private void showPopuWindow(int who) {
        View inflate = getLayoutInflater().inflate(R.layout.popuwindow_rule, null);
        listView=inflate.findViewById(R.id.lv_list);
        List<String> lists=new ArrayList<>();
        lists.add("销售数量");
        lists.add("销售金额");

        ruleAdapter=new CheckRuleAdapter(lists,this);
        listView.setAdapter(ruleAdapter);

        if (who==0){
            ruleAdapter.setSelectItem(0);
        }else {
            ruleAdapter.setSelectItem(1);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ruleAdapter.setSelectItem(position);
                if (position==0){
                    tv_rule.setText("销售数量");
                    // TODO: 2019/1/2 调用接口
                    rulestr="COUNT";
                    list.clear();
                    initData(page,pageSize,isDay,startstr,endstr,rulestr);
                }else if (position==1){
                    tv_rule.setText("销售金额");
                    // TODO: 2019/1/2 调用接口
                    rulestr="PRICE";
                    list.clear();
                    initData(page,pageSize,isDay,startstr,endstr,rulestr);
                }
                window.dismiss();
            }
        });
        window = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        window.setAnimationStyle(R.style.ActionDialogStyle);

        window.setBackgroundDrawable(getDrawable());
        window.setTouchable(true); // 设置popupwindow可点击
        window.setOutsideTouchable(true); // 设置popupwindow外部可点击
        window.showAsDropDown(lin_title);
        window.update();

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

    private void initTimer() {
        Calendar selectedDate = Calendar.getInstance();
        //获取系统的日期
        //年
        int year = selectedDate.get(Calendar.YEAR);
        //月
        int month = selectedDate.get(Calendar.MONTH) + 1;
        //日
        int day = selectedDate.get(Calendar.DAY_OF_MONTH);

        Calendar startDate = Calendar.getInstance();
        startDate.set(2010, 1, 1);

        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, day);

        //时间选择器
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                time=getTime(date);
                if (!time.equals("")){
                    if (who==1){
                        tv_startDate.setText(time);
                        startstr=time;
                    }else {
                        tv_endDate.setText(time);
                        endstr=time;
                    }
                }

            }
        })
                .setLayoutRes(R.layout.item_picker_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_confirm);
                        TextView ivCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.returnData();
                                pvTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(Color.DKGRAY)
                .setContentTextSize(20)
                .setDate(selectedDate)
                .setRangDate(startDate, selectedDate)
                .isDialog(true)
                //  .setDecorView(pr_list)//非dialog模式下,设置ViewGroup, pickerView将会添加到这个ViewGroup中
                .setBackgroundId(Color.parseColor("#F5F5F5"))
                .setOutSideCancelable(true)
                .build();

        pvTime.setKeyBackCancelable(false);//系统返回键监听屏蔽掉



    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(date);
    }
}
