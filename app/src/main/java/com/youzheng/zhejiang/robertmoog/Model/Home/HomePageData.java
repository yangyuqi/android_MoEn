package com.youzheng.zhejiang.robertmoog.Model.Home;

import java.util.List;

public class HomePageData {
    private boolean newPromotion ;
    private boolean newCombo ;
    private List<BannerImageDataBean> bannerImageData ;

    public boolean isNewPromotion() {
        return newPromotion;
    }

    public void setNewPromotion(boolean newPromotion) {
        this.newPromotion = newPromotion;
    }

    public boolean isNewCombo() {
        return newCombo;
    }

    public void setNewCombo(boolean newCombo) {
        this.newCombo = newCombo;
    }

    public List<BannerImageDataBean> getBannerImageData() {
        return bannerImageData;
    }

    public void setBannerImageData(List<BannerImageDataBean> bannerImageData) {
        this.bannerImageData = bannerImageData;
    }
}
