package com.youzheng.zhejiang.robertmoog.Model.Home;

import java.util.List;

public class SetMealData {
    private List<HomeListDataBean> listData ;
    private int pageSize ;
    private int totalSize ;
    private int totalPage ;
    private int pageNum ;

    public List<HomeListDataBean> getListData() {
        return listData;
    }

    public void setListData(List<HomeListDataBean> listData) {
        this.listData = listData;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
