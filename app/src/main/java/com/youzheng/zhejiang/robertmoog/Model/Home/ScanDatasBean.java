package com.youzheng.zhejiang.robertmoog.Model.Home;

import java.util.List;

public class ScanDatasBean {

    /**
     * name : 测试商品  淋浴房
     * code : cs1001
     * photo :
     * price : 1000
     * id : 44
     * isSetMeal : false
     * activityName : null
     * comboDescribe : null
     * isSpecial : true
     * productList : null
     */

    private String name;
    private String code;
    private String photo;
    private String price;
    private String id;
    private boolean isSetMeal;
    private String activityName;
    private String comboDescribe;
    private boolean isSpecial;
    private List<ProductListBean> productList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSetMeal() {
        return isSetMeal;
    }

    public void setSetMeal(boolean setMeal) {
        isSetMeal = setMeal;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getComboDescribe() {
        return comboDescribe;
    }

    public void setComboDescribe(String comboDescribe) {
        this.comboDescribe = comboDescribe;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void setSpecial(boolean special) {
        isSpecial = special;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }
}
