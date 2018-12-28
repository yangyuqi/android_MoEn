package com.youzheng.zhejiang.robertmoog.Store.bean;

import java.util.List;

public class GoodsList {



        /**
         * pageNum : 1
         * pageSize : 10
         * totalSize : 25
         * totalPage : 3
         * productListDetailData : [{"id":1,"smallImageUrl":"mock","skuId":"mock","name":"mock","price":"mock"}]
         */

        private int pageNum;
        private int pageSize;
        private int totalSize;
        private int totalPage;
        private List<ProductListDetailDataBean> productListDetailData;

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

        public List<ProductListDetailDataBean> getProductListDetailData() {
            return productListDetailData;
        }

        public void setProductListDetailData(List<ProductListDetailDataBean> productListDetailData) {
            this.productListDetailData = productListDetailData;
        }

        public static class ProductListDetailDataBean {
            /**
             * id : 1
             * smallImageUrl : mock
             * skuId : mock
             * name : mock
             * price : mock
             */

            private int id;
            private String smallImageUrl;
            private String skuId;
            private String name;
            private String price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSmallImageUrl() {
                return smallImageUrl;
            }

            public void setSmallImageUrl(String smallImageUrl) {
                this.smallImageUrl = smallImageUrl;
            }

            public String getSkuId() {
                return skuId;
            }

            public void setSkuId(String skuId) {
                this.skuId = skuId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }

}
