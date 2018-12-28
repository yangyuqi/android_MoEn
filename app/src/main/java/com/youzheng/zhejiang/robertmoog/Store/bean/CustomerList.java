package com.youzheng.zhejiang.robertmoog.Store.bean;

import java.util.List;

public class CustomerList {


    /**
     * code : 200
     * msg : Succes
     * datas : {"totalAmount":1,"coustomerList":[{"month":"mock","num":1}]}
     */
        /**
         * totalAmount : 1
         * coustomerList : [{"month":"mock","num":1}]
         */

        private int totalAmount;
        private List<CoustomerListBean> coustomerList;

        public int getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(int totalAmount) {
            this.totalAmount = totalAmount;
        }

        public List<CoustomerListBean> getCoustomerList() {
            return coustomerList;
        }

        public void setCoustomerList(List<CoustomerListBean> coustomerList) {
            this.coustomerList = coustomerList;
        }

        public static class CoustomerListBean {
            /**
             * month : mock
             * num : 1
             */

            private String month;
            private int num;

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

    }
}
