package com.youzheng.zhejiang.robertmoog.Count.bean;

public class CountAll {



        /**
         * dayCountData : {"shopCount":"0","setMealName":"","categoryName":"","productSku":"","customerCount":"0","productName":"","setMealInfo":""}
         */

        private DayCountDataBean dayCountData;

        public DayCountDataBean getDayCountData() {
            return dayCountData;
        }

        public void setDayCountData(DayCountDataBean dayCountData) {
            this.dayCountData = dayCountData;
        }

        public static class DayCountDataBean {
            /**
             * shopCount : 0
             * setMealName :
             * categoryName :
             * productSku :
             * customerCount : 0
             * productName :
             * setMealInfo :
             */

            private String shopCount;
            private String setMealName;
            private String categoryName;
            private String productSku;
            private String customerCount;
            private String productName;
            private String setMealInfo;

            public String getShopCount() {
                return shopCount;
            }

            public void setShopCount(String shopCount) {
                this.shopCount = shopCount;
            }

            public String getSetMealName() {
                return setMealName;
            }

            public void setSetMealName(String setMealName) {
                this.setMealName = setMealName;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public String getProductSku() {
                return productSku;
            }

            public void setProductSku(String productSku) {
                this.productSku = productSku;
            }

            public String getCustomerCount() {
                return customerCount;
            }

            public void setCustomerCount(String customerCount) {
                this.customerCount = customerCount;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getSetMealInfo() {
                return setMealInfo;
            }

            public void setSetMealInfo(String setMealInfo) {
                this.setMealInfo = setMealInfo;
            }
    }
}
