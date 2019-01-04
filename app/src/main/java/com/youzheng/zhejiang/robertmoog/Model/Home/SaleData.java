package com.youzheng.zhejiang.robertmoog.Model.Home;

import java.util.List;

public class SaleData<T> {
    private String amountPayable ;
    private String payAmount ;
    private String orderDerate ;
    private String couponDerate ;
    private String shopDerate ;
    private String shipPerson ;
    private String shipMobile ;
    private String shipAddress ;
    private String addressId ;
    private String rules ;
    private List<T> useCouponList ;
    private List<T> notUseCouponList ;

    public String getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(String amountPayable) {
        this.amountPayable = amountPayable;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getOrderDerate() {
        return orderDerate;
    }

    public void setOrderDerate(String orderDerate) {
        this.orderDerate = orderDerate;
    }

    public String getCouponDerate() {
        return couponDerate;
    }

    public void setCouponDerate(String couponDerate) {
        this.couponDerate = couponDerate;
    }

    public String getShopDerate() {
        return shopDerate;
    }

    public void setShopDerate(String shopDerate) {
        this.shopDerate = shopDerate;
    }

    public String getShipPerson() {
        return shipPerson;
    }

    public void setShipPerson(String shipPerson) {
        this.shipPerson = shipPerson;
    }

    public String getShipMobile() {
        return shipMobile;
    }

    public void setShipMobile(String shipMobile) {
        this.shipMobile = shipMobile;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public List<T> getUseCouponList() {
        return useCouponList;
    }

    public void setUseCouponList(List<T> useCouponList) {
        this.useCouponList = useCouponList;
    }

    public List<T> getNotUseCouponList() {
        return notUseCouponList;
    }

    public void setNotUseCouponList(List<T> notUseCouponList) {
        this.notUseCouponList = notUseCouponList;
    }
}
