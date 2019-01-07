package com.youzheng.zhejiang.robertmoog.Store.bean;

import java.util.List;

public class SampleOutPic {

        /**
         * productSampleResultData : {"operator":"mock","operationTime":"mock","imageUrls":[{"bigUrl":"mock","smallUrl":"mock"}]}
         */

        private ProductSampleResultDataBean productSampleResultData;

        public ProductSampleResultDataBean getProductSampleResultData() {
            return productSampleResultData;
        }

        public void setProductSampleResultData(ProductSampleResultDataBean productSampleResultData) {
            this.productSampleResultData = productSampleResultData;
        }

        public static class ProductSampleResultDataBean {
            /**
             * operator : mock
             * operationTime : mock
             * imageUrls : [{"bigUrl":"mock","smallUrl":"mock"}]
             */

            private String operator;
            private String operationTime;
            private List<ImageUrlsBean> imageUrls;

            public String getOperator() {
                return operator;
            }

            public void setOperator(String operator) {
                this.operator = operator;
            }

            public String getOperationTime() {
                return operationTime;
            }

            public void setOperationTime(String operationTime) {
                this.operationTime = operationTime;
            }

            public List<ImageUrlsBean> getImageUrls() {
                return imageUrls;
            }

            public void setImageUrls(List<ImageUrlsBean> imageUrls) {
                this.imageUrls = imageUrls;
            }

            public static class ImageUrlsBean {
                /**
                 * bigUrl : mock
                 * smallUrl : mock
                 */

                private String bigUrl;
                private String smallUrl;

                public String getBigUrl() {
                    return bigUrl;
                }

                public void setBigUrl(String bigUrl) {
                    this.bigUrl = bigUrl;
                }

                public String getSmallUrl() {
                    return smallUrl;
                }

                public void setSmallUrl(String smallUrl) {
                    this.smallUrl = smallUrl;
                }
            }

    }
}
