package com.youzheng.zhejiang.robertmoog.Store.bean;

import java.util.List;

public class GoodsListDetail {

        /**
         * productDdetail : {"name":"mock","skuId":"mock","price":"mock","firstCategory":"mock","series":"mock","specification":"mock","packUnit":"mock","retailPrice":"mock","list":["mock"],"id":1}
         */

        private ProductDdetailBean productDdetail;

        public ProductDdetailBean getProductDdetail() {
            return productDdetail;
        }

        public void setProductDdetail(ProductDdetailBean productDdetail) {
            this.productDdetail = productDdetail;
        }

        public static class ProductDdetailBean {
            /**
             * name : mock
             * skuId : mock
             * price : mock
             * firstCategory : mock
             * series : mock
             * specification : mock
             * packUnit : mock
             * retailPrice : mock
             * list : ["mock"]
             * id : 1
             */

            private String name;
            private String skuId;
            private String price;
            private String firstCategory;
            private String series;
            private String specification;
            private String packUnit;
            private String retailPrice;
            private int id;
            private List<String> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSkuId() {
                return skuId;
            }

            public void setSkuId(String skuId) {
                this.skuId = skuId;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getFirstCategory() {
                return firstCategory;
            }

            public void setFirstCategory(String firstCategory) {
                this.firstCategory = firstCategory;
            }

            public String getSeries() {
                return series;
            }

            public void setSeries(String series) {
                this.series = series;
            }

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }

            public String getPackUnit() {
                return packUnit;
            }

            public void setPackUnit(String packUnit) {
                this.packUnit = packUnit;
            }

            public String getRetailPrice() {
                return retailPrice;
            }

            public void setRetailPrice(String retailPrice) {
                this.retailPrice = retailPrice;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<String> getList() {
                return list;
            }

            public void setList(List<String> list) {
                this.list = list;
            }

    }
}
