package com.youzheng.zhejiang.robertmoog.Store.bean;

import java.util.List;

public class ProfessionalCustomerList {

        /**
         * count : 1
         * specialtyCustomerList : [{"registerDate":"mock","custName":"mock","custCode":"mock","career":"mock","account":"mock","specialtyType":"mock"}]
         */

        private int count;
        private List<SpecialtyCustomerListBean> specialtyCustomerList;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<SpecialtyCustomerListBean> getSpecialtyCustomerList() {
            return specialtyCustomerList;
        }

        public void setSpecialtyCustomerList(List<SpecialtyCustomerListBean> specialtyCustomerList) {
            this.specialtyCustomerList = specialtyCustomerList;
        }

        public static class SpecialtyCustomerListBean {
            /**
             * registerDate : mock
             * custName : mock
             * custCode : mock
             * career : mock
             * account : mock
             * specialtyType : mock
             */

            private String registerDate;
            private String custName;
            private String custCode;
            private String career;
            private String account;
            private String specialtyType;

            public String getRegisterDate() {
                return registerDate;
            }

            public void setRegisterDate(String registerDate) {
                this.registerDate = registerDate;
            }

            public String getCustName() {
                return custName;
            }

            public void setCustName(String custName) {
                this.custName = custName;
            }

            public String getCustCode() {
                return custCode;
            }

            public void setCustCode(String custCode) {
                this.custCode = custCode;
            }

            public String getCareer() {
                return career;
            }

            public void setCareer(String career) {
                this.career = career;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getSpecialtyType() {
                return specialtyType;
            }

            public void setSpecialtyType(String specialtyType) {
                this.specialtyType = specialtyType;
            }

    }
}
