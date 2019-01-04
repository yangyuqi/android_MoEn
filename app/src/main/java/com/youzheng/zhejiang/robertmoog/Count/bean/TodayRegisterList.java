package com.youzheng.zhejiang.robertmoog.Count.bean;

import java.util.List;

public class TodayRegisterList {


        private List<CustomerListBean> customerList;

        public List<CustomerListBean> getCustomerList() {
            return customerList;
        }

        public void setCustomerList(List<CustomerListBean> customerList) {
            this.customerList = customerList;
        }

        public static class CustomerListBean {
            /**
             * createDate : mock
             * phone : mock
             * operatePersonal : mock
             * channel : mock
             */

            private String createDate;
            private String phone;
            private String operatePersonal;
            private String channel;

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getOperatePersonal() {
                return operatePersonal;
            }

            public void setOperatePersonal(String operatePersonal) {
                this.operatePersonal = operatePersonal;
            }

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

    }
}
