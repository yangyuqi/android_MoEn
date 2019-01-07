package com.youzheng.zhejiang.robertmoog.Model.Home;

import java.util.List;

public class GetPromoListDatas {
    private int totalSize ;
    private int totalPage ;
    private int pageSize ;
    private int pageNum ;
    private List<GetPromoListBean> getPromoList ;

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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<GetPromoListBean> getGetPromoList() {
        return getPromoList;
    }

    public void setGetPromoList(List<GetPromoListBean> getPromoList) {
        this.getPromoList = getPromoList;
    }
}
