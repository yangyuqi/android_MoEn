package com.youzheng.zhejiang.robertmoog.Model.Home;

import java.util.ArrayList;
import java.util.List;

public class CustomerIntentDataBean {
    private boolean isIntent ;
    private String id ;
    private String remark ;
    private List<IntentProductList> intentProductList = new ArrayList<>();

    public boolean isIntent() {
        return isIntent;
    }

    public void setIntent(boolean intent) {
        isIntent = intent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<IntentProductList> getIntentProductList() {
        return intentProductList;
    }

    public void setIntentProductList(List<IntentProductList> intentProductList) {
        this.intentProductList = intentProductList;
    }
}
