package com.youzheng.zhejiang.robertmoog.Store.bean;

import java.util.List;

public class CommitRequest {


    private List<ProductSampleDataBean> productSampleData;

    public List<ProductSampleDataBean> getProductSampleData() {
        return productSampleData;
    }

    public void setProductSampleData(List<ProductSampleDataBean> productSampleData) {
        this.productSampleData = productSampleData;
    }

    public static class ProductSampleDataBean {
        /**
         * sampleId : 1
         * sampleQuantity : 1
         */

        private int sampleId;
        private int sampleQuantity;

        public int getSampleId() {
            return sampleId;
        }

        public void setSampleId(int sampleId) {
            this.sampleId = sampleId;
        }

        public int getSampleQuantity() {
            return sampleQuantity;
        }

        public void setSampleQuantity(int sampleQuantity) {
            this.sampleQuantity = sampleQuantity;
        }
    }
}
