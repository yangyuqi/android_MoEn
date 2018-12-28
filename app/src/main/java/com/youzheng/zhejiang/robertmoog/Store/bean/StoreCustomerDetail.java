package com.youzheng.zhejiang.robertmoog.Store.bean;

import java.util.List;

public class StoreCustomerDetail {



        /**
         * pageNum : 1
         * pageSize : 10
         * totalSize : 25
         * totalPage : 3
         * monthCoustomerDetail : [{"sourceChannelEnum":"mock","registerDate":"mock","account":"mock","updateUserName":"mock","customerType":"mock"}]
         */

        private int pageNum;
        private int pageSize;
        private int totalSize;
        private int totalPage;
        private List<MonthCoustomerDetailBean> monthCoustomerDetail;

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

        public List<MonthCoustomerDetailBean> getMonthCoustomerDetail() {
            return monthCoustomerDetail;
        }

        public void setMonthCoustomerDetail(List<MonthCoustomerDetailBean> monthCoustomerDetail) {
            this.monthCoustomerDetail = monthCoustomerDetail;
        }

        public static class MonthCoustomerDetailBean {
            /**
             * sourceChannelEnum : mock
             * registerDate : mock
             * account : mock
             * updateUserName : mock
             * customerType : mock
             */

            private String sourceChannelEnum;
            private String registerDate;
            private String account;
            private String updateUserName;
            private String customerType;


            public String getSourceChannelEnum() {
                return sourceChannelEnum;
            }

            public void setSourceChannelEnum(String sourceChannelEnum) {
                this.sourceChannelEnum = sourceChannelEnum;
            }

            public String getRegisterDate() {
                return registerDate;
            }

            public void setRegisterDate(String registerDate) {
                this.registerDate = registerDate;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getUpdateUserName() {
                return updateUserName;
            }

            public void setUpdateUserName(String updateUserName) {
                this.updateUserName = updateUserName;
            }

            public String getCustomerType() {
                return customerType;
            }

            public void setCustomerType(String customerType) {
                this.customerType = customerType;
            }
        }

}
