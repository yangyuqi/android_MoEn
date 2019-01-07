package com.youzheng.zhejiang.robertmoog.Store.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.youzheng.zhejiang.robertmoog.Base.BaseFragment;
import com.youzheng.zhejiang.robertmoog.Home.activity.ClientViewActivity;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.activity.CheckResultActivity;
import com.youzheng.zhejiang.robertmoog.Store.activity.CouponRecordActivity;
import com.youzheng.zhejiang.robertmoog.Store.activity.GoodsManageActivity;
import com.youzheng.zhejiang.robertmoog.Store.activity.OrderListActivity;
import com.youzheng.zhejiang.robertmoog.Store.activity.PeopleMangerActivity;
import com.youzheng.zhejiang.robertmoog.Store.activity.ProfessionalCustomerActivity;
import com.youzheng.zhejiang.robertmoog.Store.activity.ProfessionalCustomerOrderActivity;
import com.youzheng.zhejiang.robertmoog.Store.activity.ReturnGoodsManger.ReturnGoodsListActivity;
import com.youzheng.zhejiang.robertmoog.Store.activity.SampleOutInformationActivity;
import com.youzheng.zhejiang.robertmoog.Store.activity.SampleOutMangerActivity;
import com.youzheng.zhejiang.robertmoog.Store.activity.StoreCustomerActivity;
import com.youzheng.zhejiang.robertmoog.utils.CommonAdapter;
import com.youzheng.zhejiang.robertmoog.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class StoreFragment extends BaseFragment implements BaseFragment.ReloadInterface {

    View mView;
    GridView gridView;
    CommonAdapter<HomeBean> adapter;
    List<HomeBean> data = new ArrayList<>();
    private View view;
    private ImageView btnBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.store_fragment_layout, null);
        initView();
        setUpView(mView);
        setReloadInterface(this);
        return mView;
    }

    private void initView() {
        gridView = mView.findViewById(R.id.gv);
        data.clear();
        data.add(new HomeBean("门店客户", R.mipmap.group_7_1));
        data.add(new HomeBean("订单管理", R.mipmap.group_7_2));
        data.add(new HomeBean("商品管理", R.mipmap.group_7_3));
        data.add(new HomeBean("专业客户", R.mipmap.group_7_4));
        data.add(new HomeBean("专业客户订单", R.mipmap.group_7_5));
        data.add(new HomeBean("员工管理", R.mipmap.group_7_6));
        data.add(new HomeBean("出样填报", R.mipmap.group_7_7));
        data.add(new HomeBean("出样管理", R.mipmap.group_7_8));
        data.add(new HomeBean("巡店查询", R.mipmap.group_7_9));
        data.add(new HomeBean("退货", R.mipmap.group_7_10));
        data.add(new HomeBean("退货单管理", R.mipmap.group_7_11));
        data.add(new HomeBean("优惠券记录", R.mipmap.group_7_12));
        adapter = new CommonAdapter<HomeBean>(mContext, data, R.layout.home_ls_item) {
            @Override
            public void convert(ViewHolder helper, HomeBean item) {
                helper.setText(R.id.tv_text, item.name);
                helper.setImageResource(R.id.iv_icon, item.icon);
            }
        };
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(), StoreCustomerActivity.class));
                        break;

                    case 1:
                        startActivity(new Intent(getActivity(), OrderListActivity.class));
                        break;

                    case 2:
                        startActivity(new Intent(getActivity(), GoodsManageActivity.class));
                        break;

                    case 3:
                        startActivity(new Intent(getActivity(), ProfessionalCustomerActivity.class));
                        break;

                    case 4:
                        startActivity(new Intent(getActivity(), ProfessionalCustomerOrderActivity.class));
                        break;

                    case 5:
                        startActivity(new Intent(getActivity(), PeopleMangerActivity.class));
                        break;

                    case 6:
                        startActivity(new Intent(getActivity(), SampleOutInformationActivity.class));
                        break;

                    case 7:
                        startActivity(new Intent(getActivity(), SampleOutMangerActivity.class));
                        break;

                    case 8:
                        startActivity(new Intent(getActivity(), CheckResultActivity.class));
                        break;

                    case 9:
                        startActivity(new Intent(getActivity(), ClientViewActivity.class));
                        break;

                    case 10:
                        startActivity(new Intent(getActivity(), ReturnGoodsListActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(getActivity(), CouponRecordActivity.class));
                        break;
                }
            }
        });
        btnBack = (ImageView) mView.findViewById(R.id.btnBack);
        btnBack.setVisibility(View.GONE);
    }

    @Override
    public void reloadClickListener() {

    }

    public class HomeBean {
        String name;
        int icon;

        public HomeBean(String name, int icon) {
            this.name = name;
            this.icon = icon;
        }
    }
}
