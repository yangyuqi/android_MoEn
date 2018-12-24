package com.youzheng.zhejiang.robertmoog.Model;

import java.util.ArrayList;
import java.util.List;

public class TestBean {

    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String type ;
    private List<String> data = new ArrayList<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public TestBean(String name, String type, List<String> data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
