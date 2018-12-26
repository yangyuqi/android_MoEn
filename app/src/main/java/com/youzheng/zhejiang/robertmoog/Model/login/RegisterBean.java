package com.youzheng.zhejiang.robertmoog.Model.login;

import java.io.Serializable;

public class RegisterBean implements Serializable{
    private String phone ;
    private Integer customerId ;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
