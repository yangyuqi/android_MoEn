package com.youzheng.zhejiang.robertmoog.Store.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youzheng.zhejiang.robertmoog.Base.BaseFragment;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.activity.ProfessionalOrderDetailActivity;
import com.youzheng.zhejiang.robertmoog.Store.adapter.GoodsTimeAdapter;
import com.youzheng.zhejiang.robertmoog.Store.adapter.ProfessionalCustomerOrderListAdapter;
import com.youzheng.zhejiang.robertmoog.Store.bean.OrderList;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;
import com.youzheng.zhejiang.robertmoog.Store.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private View view;
    /**
     * 搜索订单编号
     */
    private EditText tv_search;
    private ImageView iv_search;
    private LinearLayout lin_search;
    /**
     * 时间
     */
    private TextView tv_time;
    private PullLoadMoreRecyclerView rv_list;
    private GridView gv_time;
    /**
     * 重置
     */
    private TextView tv_again;
    /**
     * 确定
     */
    private TextView tv_confirm;
    private DrawerLayout drawer_layout;
    private List<OrderList> list = new ArrayList<>();
    private List<Integer> piclist = new ArrayList<>();
    private ProfessionalCustomerOrderListAdapter adapter;
    private GoodsTimeAdapter goodsTimeAdapter;
    private List<String> strlist = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_customer_order, null);

        initView();
        return view;
    }

    private void initView() {
        tv_search = (EditText) view.findViewById(R.id.tv_search);
        iv_search = (ImageView) view.findViewById(R.id.iv_search);
        iv_search.setOnClickListener(this);
        lin_search = (LinearLayout) view.findViewById(R.id.lin_search);
        tv_time = (TextView) view.findViewById(R.id.tv_time);
        tv_time.setOnClickListener(this);
        rv_list = (PullLoadMoreRecyclerView) view.findViewById(R.id.rv_list);
        gv_time = (GridView) view.findViewById(R.id.gv_time);
        tv_again = (TextView) view.findViewById(R.id.tv_again);
        tv_confirm = (TextView) view.findViewById(R.id.tv_confirm);
        drawer_layout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//禁止手势滑动
        tv_again.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);
        gv_time.setOnItemClickListener(this);
        initData();
    }

    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL, 15, getResources().getColor(R.color.bg_all)));
        rv_list.setLinearLayout();
        rv_list.setColorSchemeResources(R.color.colorPrimary);

        OrderList orderList = new OrderList();
        orderList.setType(0);
        orderList.setPic(R.mipmap.ic_launcher);
        orderList.setText("摩恩");
        list.add(orderList);

        OrderList orderList1 = new OrderList();
        orderList1.setType(1);
        orderList.setText("摩恩");
        orderList1.setPic(R.mipmap.ic_launcher);
        list.add(orderList1);

        piclist.add(R.mipmap.ic_launcher);
        piclist.add(R.mipmap.ic_launcher);
        piclist.add(R.mipmap.ic_launcher);

        adapter = new ProfessionalCustomerOrderListAdapter(list, piclist, getActivity());
        rv_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new OnRecyclerViewAdapterItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(),ProfessionalOrderDetailActivity.class));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });


        strlist.add("全部");
        strlist.add("一到三个月");
        strlist.add("一到三个月");
        strlist.add("一到三个月");
        strlist.add("一到三个月");
        strlist.add("一到三个月");

        goodsTimeAdapter=new GoodsTimeAdapter(strlist,getActivity());
        gv_time.setAdapter(goodsTimeAdapter);
        goodsTimeAdapter.notifyDataSetChanged();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_search:
                break;
            case R.id.tv_time:
                drawer_layout.openDrawer(GravityCompat.END);
                break;
            case R.id.tv_again:
                break;
            case R.id.tv_confirm:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        goodsTimeAdapter.setSelectItem(position);
    }
}
