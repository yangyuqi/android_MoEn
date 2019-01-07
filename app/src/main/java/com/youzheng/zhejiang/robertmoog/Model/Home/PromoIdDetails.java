package com.youzheng.zhejiang.robertmoog.Model.Home;

import java.util.ArrayList;
import java.util.List;

public class PromoIdDetails {
    private String promoId ;
    private String promoName ;
    private String startTime ;
    private String endTime ;
    private String activityAbstract ;
    private String type ;
    private String typeId ;
    private List<String> orderPromo = new ArrayList<>();

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getActivityAbstract() {
        return activityAbstract;
    }

    public void setActivityAbstract(String activityAbstract) {
        this.activityAbstract = activityAbstract;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public List<String> getOrderPromo() {
        return orderPromo;
    }

    public void setOrderPromo(List<String> orderPromo) {
        this.orderPromo = orderPromo;
    }
}
