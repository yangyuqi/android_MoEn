package com.youzheng.zhejiang.robertmoog.Store.bean;

import java.util.List;

public class CouponRecord {



        /**
         * totalAmount : 10000
         * orderMonthDataList : [{"month":"mock","numPrice":"mock"}]
         */

        private String totalAmount;
        private List<OrderMonthDataListBean> orderMonthDataList;

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public List<OrderMonthDataListBean> getOrderMonthDataList() {
            return orderMonthDataList;
        }

        public void setOrderMonthDataList(List<OrderMonthDataListBean> orderMonthDataList) {
            this.orderMonthDataList = orderMonthDataList;
        }

        public static class OrderMonthDataListBean {
            /**
             * month : mock
             * numPrice : mock
             */

            private String month;
            private String numPrice;

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public String getNumPrice() {
                return numPrice;
            }

            public void setNumPrice(String numPrice) {
                this.numPrice = numPrice;
            }
        }

}
