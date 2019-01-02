package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.PeopleMangerAdapter;
import com.youzheng.zhejiang.robertmoog.Store.bean.PeopleMangerList;
import com.youzheng.zhejiang.robertmoog.Store.bean.ProfessionalCustomerList;
import com.youzheng.zhejiang.robertmoog.Store.bean.StoreCustomerDetail;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;
import com.youzheng.zhejiang.robertmoog.Store.view.MyListView;
import com.youzheng.zhejiang.robertmoog.Store.view.RecycleViewDivider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;

/**|
 * 员工管理界面
 */
public class PeopleMangerActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    private PullLoadMoreRecyclerView lv_list;
    /**
     * 添加导购
     */
    private TextView tv_add,tv_store_name;
    private List<PeopleMangerList.ShopPersonalListBean> list=new ArrayList<>();
    private PeopleMangerAdapter adapter;
    private int page=1;
    private int pageSize=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_manger);
        initView();
        setListener();
    }

    private void setListener() {
        lv_list.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page=1;
                list.clear();
                initData(page,pageSize);
            }

            @Override
            public void onLoadMore() {
                 page++;
                 initData(page,pageSize);
            }
        });

    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("门店管理");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        lv_list = (PullLoadMoreRecyclerView) findViewById(R.id.lv_list);
        tv_add = (TextView) findViewById(R.id.tv_add);
        tv_store_name=findViewById(R.id.tv_store_name);
        tv_add.setOnClickListener(this);

        lv_list.setLinearLayout();
        lv_list.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 5, getResources().getColor(R.color.divider_color_item)));
        lv_list.setColorSchemeResources(R.color.colorPrimary);

        adapter=new PeopleMangerAdapter(list,this);
        lv_list.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnRecyclerViewAdapterItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showStopDialog(list.get(position).getId());
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    public void showStopDialog(final int id) {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(PeopleMangerActivity.this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_appear, null);
        dialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogBuilder.show();
        dialogBuilder.setContentView(dialogView);

        TextView tv_no=dialogView.findViewById(R.id.tv_no);
        TextView tv_ok=dialogView.findViewById(R.id.tv_ok);

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dialogBuilder.dismiss();
            }
        });

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  stopPeople(id);
                  dialogBuilder.dismiss();
            }
        });

        Window window = dialogBuilder.getWindow();
        //这句设置我们dialog在底部
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        //这句就是设置dialog横向满屏了。
        DisplayMetrics d = this.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
//        lp.width = (int) (d.widthPixels * 0.74); // 高度设置为屏幕的0.6
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);


    }

    private void stopPeople(int id) {

        HashMap<String,Object> map=new HashMap<>();
        map.put("employeeId",id);

        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.STOP_SELLER + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e("停用员工",response);
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                       if (!baseModel.getMsg().equals("")){
                           showToast(baseModel.getMsg());
                       }
                       initData(page,pageSize);
                       lv_list.setRefreshing(true);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(page,pageSize);
    }

    private void initData(int page, int pageSize) {


        HashMap<String,Object> map=new HashMap<>();
        map.put("pageNum",page);
        map.put("pageSize",pageSize);

        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.PEOPLE_MAGER_LIST + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                lv_list.setPullLoadMoreCompleted();
            }

            @Override
            public void onResponse(String response) {
                Log.e("门店管理列表",response);
                lv_list.setPullLoadMoreCompleted();
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    PeopleMangerList peopleMangerList = gson.fromJson(gson.toJson(baseModel.getDatas()),PeopleMangerList.class);
                    setData(peopleMangerList);
                }
            }

        });

    }

    private void setData(PeopleMangerList peopleMangerList) {
        if (peopleMangerList.getShopPersonalList()==null) return;
        if (!peopleMangerList.getShopName().equals("")){
            tv_store_name.setText(peopleMangerList.getShopName());
        }
        List<PeopleMangerList.ShopPersonalListBean> listBeans=peopleMangerList.getShopPersonalList();
        if (listBeans.size()!=0){
            list.addAll(listBeans);
            adapter.setUI(listBeans);
        }else {
            showToast(getString(R.string.load_list_erron));
        }
        lv_list.setPullLoadMoreCompleted();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.tv_add:
                startActivity(new Intent(this,AddStaffActivity.class));
                break;
        }
    }
}
