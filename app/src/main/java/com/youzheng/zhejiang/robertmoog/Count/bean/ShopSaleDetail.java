package com.youzheng.zhejiang.robertmoog.Count.bean;

import java.util.List;

public class ShopSaleDetail {


         /**
         * pageNum : 1
         * orderCount : mock
         * pageSize : 10
         * totalSize : 25
         * totalPage : 3
         * orderAmountCount : mock
         * customerTransaction : mock
         * shopData : [{"date":"mock","order":"mock","orderAmount":"mock","customerTransaction":"mock"}]
         */

        private int pageNum;
        private String orderCount;
        private int pageSize;
        private int totalSize;
        private int totalPage;
        private String orderAmountCount;
        private String customerTransaction;
        private List<ShopDataBean> shopData;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public String getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(String orderCount) {
            this.orderCount = orderCount;
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

        public String getOrderAmountCount() {
            return orderAmountCount;
        }

        public void setOrderAmountCount(String orderAmountCount) {
            this.orderAmountCount = orderAmountCount;
        }

        public String getCustomerTransaction() {
            return customerTransaction;
        }

        public void setCustomerTransaction(String customerTransaction) {
            this.customerTransaction = customerTransaction;
        }

        public List<ShopDataBean> getShopData() {
            return shopData;
        }

        public void setShopData(List<ShopDataBean> shopData) {
            this.shopData = shopData;
        }

        public static class ShopDataBean {
            /**
             * date : mock
             * order : mock
             * orderAmount : mock
             * customerTransaction : mock
             */

            private String date;
            private String order;
            private String orderAmount;
            private String customerTransaction;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getOrder() {
                return order;
            }

            public void setOrder(String order) {
                this.order = order;
            }

            public String getOrderAmount() {
                return orderAmount;
            }

            public void setOrderAmount(String orderAmount) {
                this.orderAmount = orderAmount;
            }

            public String getCustomerTransaction() {
                return customerTransaction;
            }

            public void setCustomerTransaction(String customerTransaction) {
                this.customerTransaction = customerTransaction;
            }
        }

}
