package com.youzheng.zhejiang.robertmoog.Count.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Count.adapter.StoreSaleAdapter;
import com.youzheng.zhejiang.robertmoog.Count.bean.ShopSale;
import com.youzheng.zhejiang.robertmoog.MainActivity;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.bean.CustomerList;
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
 * 门店销量界面
 */
public class StoreSalesNumberActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 2018/11/12
     */
    private TextView tv_startDate;
    /**
     * 2018/11/15
     */
    private TextView tv_endDate;
    /**
     * 查询
     */
    private TextView tv_check;
    private PullLoadMoreRecyclerView pr_list;
    /**
     * 800
     */
    private TextView tv_order_total;
    /**
     * 10000
     */
    private TextView tv_order_money;
    /**
     * 2000
     */
    private TextView tv_order_value;
    private TimePickerView pvTime;
    private String time="";
    private int who;
    private StoreSaleAdapter adapter;
    private List<ShopSale.ShopDataBean> list=new ArrayList<>();
    private int page=1;
    private int pageSize=10;
    private boolean isDay=true;
    private String starstDate="";
    private String endsDate="";
    private String orderCount,orderAmountCount,customerTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_sales_number);
        initView();
        initTimer();
        setListener();
    }

    private void setListener() {
        pr_list.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
               page=1;
               list.clear();
               initData(page,pageSize,isDay,starstDate,endsDate);
            }

            @Override
            public void onLoadMore() {
                page++;
                initData(page,pageSize,isDay,starstDate,endsDate);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(page,pageSize,isDay,starstDate,endsDate);
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("门店销量");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        tv_startDate = (TextView) findViewById(R.id.tv_startDate);
        tv_startDate.setOnClickListener(this);
        tv_endDate = (TextView) findViewById(R.id.tv_endDate);
        tv_endDate.setOnClickListener(this);
        tv_check = (TextView) findViewById(R.id.tv_check);
        tv_check.setOnClickListener(this);
        pr_list = (PullLoadMoreRecyclerView) findViewById(R.id.pr_list);
        tv_order_total = (TextView) findViewById(R.id.tv_order_total);
        tv_order_money = (TextView) findViewById(R.id.tv_order_money);
        tv_order_value = (TextView) findViewById(R.id.tv_order_value);

        pr_list.setLinearLayout();
        pr_list.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 5, getResources().getColor(R.color.divider_color_item)));
        pr_list.setColorSchemeResources(R.color.colorPrimary);

        adapter=new StoreSaleAdapter(list,this);
        pr_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    private void initData(int page,int pageSize,boolean isDay,String startDate,String endDate) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("pageNum",page);
        map.put("pageSize",pageSize);
        map.put("isDay",isDay);
        map.put("startDate",startDate);
        map.put("endDate",endDate);

        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.SHOP_SALE + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                pr_list.setPullLoadMoreCompleted();
            }

            @Override
            public void onResponse(String response) {
                Log.e("门店销量",response);
                pr_list.setPullLoadMoreCompleted();
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    ShopSale shopSale = gson.fromJson(gson.toJson(baseModel.getDatas()),ShopSale.class);
                    setData(shopSale);
                }

            }
        });
    }

    private void setData(ShopSale shopSale) {
        if (shopSale.getShopData()==null) return;
        orderCount=shopSale.getOrderCount();
        orderAmountCount=shopSale.getOrderAmountCount();
        customerTransaction=shopSale.getCustomerTransaction();

        if (!orderCount.equals("")||orderCount!=null){
            tv_order_total.setText(orderCount);
        }

        if (!orderAmountCount.equals("")||orderAmountCount!=null){
            tv_order_money.setText(orderAmountCount);
        }

        if (!customerTransaction.equals("")||customerTransaction!=null){
            tv_order_value.setText(customerTransaction);
        }

        List<ShopSale.ShopDataBean> beans=shopSale.getShopData();
        if (beans.size()!=0){
            list.addAll(beans);
            adapter.setUI(beans);
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
                list.clear();
                isDay=false;
                initData(page,pageSize,isDay,starstDate,endsDate);
                break;
        }
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

        final Calendar startDate = Calendar.getInstance();
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
                         starstDate=time;
                     }else {
                         tv_endDate.setText(time);
                         endsDate=time;
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
