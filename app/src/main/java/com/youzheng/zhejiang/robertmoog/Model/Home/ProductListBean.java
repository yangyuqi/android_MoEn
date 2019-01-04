package com.youzheng.zhejiang.robertmoog.Model.Home;

import java.io.Serializable;

public class ProductListBean implements Serializable{
    private String id ;
    private String name ;
    private String sku ;
    private String photo ;
    private String createDate ;
    private String price ;
    private Integer count ;
    private String codePu ;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCodePu() {
        return codePu;
    }

    public void setCodePu(String codePu) {
        this.codePu = codePu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
