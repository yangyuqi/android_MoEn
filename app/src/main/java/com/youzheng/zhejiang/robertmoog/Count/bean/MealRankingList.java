package com.youzheng.zhejiang.robertmoog.Count.bean;

import java.util.List;

public class MealRankingList {



        /**
         * pageNum : 1
         * pageSize : 10
         * totalSize : 25
         * totalPage : 3
         * setMealList : [{"comboCode":"mock","comboDescribe":"mock","numOrPrice":"mock"}]
         */

        private int pageNum;
        private int pageSize;
        private int totalSize;
        private int totalPage;
        private List<SetMealListBean> setMealList;

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

        public List<SetMealListBean> getSetMealList() {
            return setMealList;
        }

        public void setSetMealList(List<SetMealListBean> setMealList) {
            this.setMealList = setMealList;
        }

        public static class SetMealListBean {
            /**
             * comboCode : mock
             * comboDescribe : mock
             * numOrPrice : mock
             */

            private String comboCode;
            private String comboDescribe;
            private String numOrPrice;

            public String getComboCode() {
                return comboCode;
            }

            public void setComboCode(String comboCode) {
                this.comboCode = comboCode;
            }

            public String getComboDescribe() {
                return comboDescribe;
            }

            public void setComboDescribe(String comboDescribe) {
                this.comboDescribe = comboDescribe;
            }

            public String getNumOrPrice() {
                return numOrPrice;
            }

            public void setNumOrPrice(String numOrPrice) {
                this.numOrPrice = numOrPrice;
            }
        }
}
