package com.youzheng.zhejiang.robertmoog.Model.Home;

import java.util.List;

public class AddressDatas {
    private int totalSize ;
    private int totalPage ;
    private int pageNum ;
    private int pageSize ;
    private List<AddressDatasBean> addressList ;

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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<AddressDatasBean> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressDatasBean> addressList) {
        this.addressList = addressList;
    }
}
