package com.youzheng.zhejiang.robertmoog.Store.bean;

import java.util.List;

public class SampleOutPic {



        /**
         * sampleImgIssueData : {"operator":"15622489789","operationTime":"2019-01-07 17:04:32","list":[{"bigUrl":"http://114.55.59.171:9080/image/product/originalImg/eca994b9-26fc-4c20-bfb4-a37290acb151.jpg","smallUrl":"http://114.55.59.171:9080/image/product/100/eca994b9-26fc-4c20-bfb4-a37290acb151.jpg"}]}
         */

        private SampleImgIssueDataBean sampleImgIssueData;

        public SampleImgIssueDataBean getSampleImgIssueData() {
            return sampleImgIssueData;
        }

        public void setSampleImgIssueData(SampleImgIssueDataBean sampleImgIssueData) {
            this.sampleImgIssueData = sampleImgIssueData;
        }

        public static class SampleImgIssueDataBean {
            /**
             * operator : 15622489789
             * operationTime : 2019-01-07 17:04:32
             * list : [{"bigUrl":"http://114.55.59.171:9080/image/product/originalImg/eca994b9-26fc-4c20-bfb4-a37290acb151.jpg","smallUrl":"http://114.55.59.171:9080/image/product/100/eca994b9-26fc-4c20-bfb4-a37290acb151.jpg"}]
             */

            private String operator;
            private String operationTime;
            private List<ListBean> list;

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

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * bigUrl : http://114.55.59.171:9080/image/product/originalImg/eca994b9-26fc-4c20-bfb4-a37290acb151.jpg
                 * smallUrl : http://114.55.59.171:9080/image/product/100/eca994b9-26fc-4c20-bfb4-a37290acb151.jpg
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
