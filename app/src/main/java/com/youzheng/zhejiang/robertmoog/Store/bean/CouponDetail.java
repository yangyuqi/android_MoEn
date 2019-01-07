package com.youzheng.zhejiang.robertmoog.Store.bean;

import java.util.List;

public class CouponDetail {



        private List<CouponUsageRecordDetailBean> couponUsageRecordDetail;

        public List<CouponUsageRecordDetailBean> getCouponUsageRecordDetail() {
            return couponUsageRecordDetail;
        }

        public void setCouponUsageRecordDetail(List<CouponUsageRecordDetailBean> couponUsageRecordDetail) {
            this.couponUsageRecordDetail = couponUsageRecordDetail;
        }

        public static class CouponUsageRecordDetailBean {
            /**
             * orderCode : D1548784201901050001
             * date : 2019/01/05 09:39:43
             * money : 0
             */

            private String orderCode;
            private String date;
            private String money;

            public String getOrderCode() {
                return orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }

}
