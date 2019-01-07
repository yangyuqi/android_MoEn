package com.youzheng.zhejiang.robertmoog.Model.Home;

import java.util.List;

public class HomeListDataBean {
    private int comId ;
    private String imgUrl ;
    private String comboDescribe ;
    private String comboName ;
    private String comboCode ;

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getComboDescribe() {
        return comboDescribe;
    }

    public void setComboDescribe(String comboDescribe) {
        this.comboDescribe = comboDescribe;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getComboCode() {
        return comboCode;
    }

    public void setComboCode(String comboCode) {
        this.comboCode = comboCode;
    }

    public List<HomeListDataBeanBean> getList() {
        return list;
    }

    public void setList(List<HomeListDataBeanBean> list) {
        this.list = list;
    }

    private List<HomeListDataBeanBean> list ;
}
