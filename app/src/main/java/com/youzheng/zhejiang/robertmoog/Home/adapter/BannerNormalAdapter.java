package com.youzheng.tongxiang.huntingjob.UI.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.youzheng.tongxiang.huntingjob.Model.entity.deliver.BannerListBean;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.H5Activity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.IntroduceJobActivity;
import com.youzheng.tongxiang.huntingjob.R;

import java.util.List;

/**
 * Created by qiuweiyu on 2018/2/7.
 */

public class BannerNormalAdapter extends StaticPagerAdapter {

    private List<BannerListBean> banner_date;
    private String accessToken ;

    public BannerNormalAdapter(List<BannerListBean> entity,String accessToken) {
        banner_date = entity;
        this.accessToken = accessToken ;
    }

    @Override
    public View getView(final ViewGroup container, final int position) {
        View new_view = LayoutInflater.from(container.getContext()).inflate(R.layout.image_new_layout,null);
        ImageView view = (ImageView) new_view.findViewById(R.id.iv_new);
        Glide.with(container.getContext()).load(banner_date.get(position).getImgUrl()).error(R.mipmap.gggggroup).into(view);
        new_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doGo(container.getContext(),banner_date.get(position));
            }
        });
        return new_view;
    }

    private void doGo(Context context ,BannerListBean bannerListBean) {
        switch (bannerListBean.getLinkType()){
            case "information" :
                Intent intent = new Intent(context,H5Activity.class);
                intent.putExtra("url","http://101.132.125.42:8787/txyp_news.html?id="+bannerListBean.getLink()+"&cid="+bannerListBean.getChildLinkType());
                context.startActivity(intent);
                break;
            case "conference" :


                break;
            case "job" :
//                Intent intent1 = new Intent(context, IntroduceJobActivity.class);
//                if (accessToken.equals("")){
//                    intent1.putExtra("from","co");
//                }
//                intent1.putExtra("id",String.valueOf(bannerListBean.getLink()));
//                context.startActivity(intent1);
                break;
            case "resume":
                break;
            case "network":
                break;
            case "company":
                break;
            case "default":
                return;
        }
    }


    @Override
    public int getCount() {
        return banner_date.size();
    }
}