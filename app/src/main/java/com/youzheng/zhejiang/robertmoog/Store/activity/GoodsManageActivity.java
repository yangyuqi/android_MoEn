package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.MyConstant;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.GoodsPagerAdapter;
import com.youzheng.zhejiang.robertmoog.Store.adapter.GoodsSearchAdapter;
import com.youzheng.zhejiang.robertmoog.Store.adapter.GoodsTitleAdapter;
import com.youzheng.zhejiang.robertmoog.Store.bean.GoodsList;
import com.youzheng.zhejiang.robertmoog.Store.bean.GoodsType;
import com.youzheng.zhejiang.robertmoog.Store.fragment.GoodsFragment;
import com.youzheng.zhejiang.robertmoog.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;

/**
 * 商品管理界面
 */
public class GoodsManageActivity extends BaseActivity implements View.OnClickListener, TextWatcher, AdapterView.OnItemClickListener {

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
    private List<GoodsType.ListDataBean> stringList = new ArrayList<>();
    private GoodsTitleAdapter goodsTitleAdapter;
    private String edit;
    private LinearLayout lin_tab;
    private ListView search_list;
    private GoodsSearchAdapter searchAdapter;
    private List<GoodsList.ProductListDetailDataBean> searchlist = new ArrayList<>();
    private int pageSize = 10;
    private int page = 1;
    public static GoodsFragment goodsFragment;


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
        tv_search.addTextChangedListener(this);


        initData();

        lin_tab = (LinearLayout) findViewById(R.id.lin_tab);
        search_list = (ListView) findViewById(R.id.search_list);

        search_list.setOnItemClickListener(this);
    }

    private void initData() {

        OkHttpClientManager.postAsynJson(gson.toJson(new HashMap<>()), UrlUtils.GOODS_LIST_TYPE + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e("商品标签", response);
                BaseModel baseModel = gson.fromJson(response, BaseModel.class);
                if (baseModel.getCode() == PublicUtils.code) {
                    GoodsType goodsType = gson.fromJson(gson.toJson(baseModel.getDatas()), GoodsType.class);
                    setData(goodsType);
                }
            }
        });
    }

    private void setData(GoodsType goodsType) {
        FragmentManager fm = getSupportFragmentManager();
        adapter = new GoodsPagerAdapter(fm, list, stringList);

        if (goodsType.getListData() == null) return;
        List<GoodsType.ListDataBean> listDataBeans = goodsType.getListData();
        stringList = goodsType.getListData();
        if (listDataBeans.size() != 0) {
            stringList.addAll(listDataBeans);
            adapter.setUI(listDataBeans);

            for (int i = 0; i < listDataBeans.size(); i++) {
               goodsFragment=new GoodsFragment();
                Bundle bundle = new Bundle();
                // bundle.putString(MyConstant.GOODS_LIST_TYPE,stringList.get(i).getName());
                bundle.putInt(MyConstant.GOODS_ID, stringList.get(i).getId());
                goodsFragment.setArguments(bundle);

                list.add(goodsFragment);
            }

            pager.setAdapter(adapter);
            pager.setOffscreenPageLimit(listDataBeans.size() - 1);

            tab.setupWithViewPager(pager);
            //默认选中
            tab.getTabAt(0).select();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_search:
                edit = tv_search.getText().toString().trim();
                if (TextUtils.isEmpty(edit)) {
                    showToast(getString(R.string.please_write_sku));
                } else {
                    initSearch(edit);
                    lin_tab.setVisibility(View.GONE);
                    pager.setVisibility(View.GONE);
                    search_list.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_more:
                showPopuWindow();
                break;
            case R.id.btnBack:
                finish();
                break;
        }
    }
    //搜索之后的数据
    private void initSearch(String str) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("pageNum",page);
        map.put("pageSize",pageSize);
//        map.put("sku",goodsName);
        map.put("sku",str);//测试用
        map.put("firstCategoryId",0);
        String token = (String) SharedPreferencesUtils.getParam(mContext, PublicUtils.access_token,"");
        if (token!=null){
            OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.GOODS_LIST + "?access_token=" + token, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    Log.e("商品列表",response);
                    BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                    if (baseModel.getCode()==PublicUtils.code){
                        GoodsList goodsList = gson.fromJson(gson.toJson(baseModel.getDatas()),GoodsList.class);
                        setSearchData(goodsList);
                    }
                }
            });
        }
    }

    private void setSearchData(GoodsList goodsList) {
        searchAdapter=new GoodsSearchAdapter(searchlist,this);
        search_list.setAdapter(searchAdapter);
        if (goodsList.getProductListDetailData()==null) return;
        List<GoodsList.ProductListDetailDataBean> productListDetailDataBeans=goodsList.getProductListDetailData();
        searchlist=goodsList.getProductListDetailData();
        if (productListDetailDataBeans.size()!=0){
            searchlist.addAll(productListDetailDataBeans);
            searchAdapter.setRefreshUI(productListDetailDataBeans);
        }



    }

    private void showPopuWindow() {
        View inflate = getLayoutInflater().inflate(R.layout.popuwindow_goods_title, null);
        mGvTitle = (GridView) inflate.findViewById(R.id.gv_title);

        goodsTitleAdapter = new GoodsTitleAdapter(stringList, this);
        mGvTitle.setAdapter(goodsTitleAdapter);
        goodsTitleAdapter.notifyDataSetChanged();

        mGvTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goodsTitleAdapter.setSelectItem(position);
                tab.getTabAt(position).select();
                window.dismiss();
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        edit = tv_search.getText().toString();
        if (TextUtils.isEmpty(edit)) {
            lin_tab.setVisibility(View.VISIBLE);
            pager.setVisibility(View.VISIBLE);
            search_list.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this,GoodsDetailActivity.class);
        intent.putExtra("goodsID",searchlist.get(position).getId());
        startActivity(intent);
    }
}
