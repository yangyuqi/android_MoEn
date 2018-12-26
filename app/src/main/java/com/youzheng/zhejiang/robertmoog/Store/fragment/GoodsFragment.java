package com.youzheng.zhejiang.robertmoog.Store.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youzheng.zhejiang.robertmoog.Base.BaseFragment;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.activity.GoodsDetailActivity;
import com.youzheng.zhejiang.robertmoog.Store.adapter.GoodsListAdapter;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;
import com.youzheng.zhejiang.robertmoog.Store.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

public class GoodsFragment extends BaseFragment {
    private View view;
    private GoodsListAdapter adapter;
    private List<String> list = new ArrayList<>();
    private PullLoadMoreRecyclerView pr_goods;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_goods_list, null);
        initView();
        return view;
    }


    private void initView() {
        pr_goods = (PullLoadMoreRecyclerView) view.findViewById(R.id.pr_goods);
        pr_goods.setLinearLayout();
        pr_goods.setColorSchemeResources(R.color.colorPrimary);
        pr_goods.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL, 15, getResources().getColor(R.color.bg_all)));
        list.add("摩恩");
        list.add("摩恩");
        list.add("摩恩");
        list.add("摩恩");
        list.add("摩恩");
        list.add("摩恩");
        list.add("摩恩");
        list.add("摩恩");
        list.add("摩恩");

        adapter=new GoodsListAdapter(list,getActivity());
        pr_goods.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new OnRecyclerViewAdapterItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(),GoodsDetailActivity.class));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }
}
