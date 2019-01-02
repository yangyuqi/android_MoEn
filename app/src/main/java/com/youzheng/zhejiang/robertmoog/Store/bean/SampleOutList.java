package com.youzheng.zhejiang.robertmoog.Store.bean;

import java.util.List;

public class SampleOutList {

        /**
         * sampleResData : {"judge":false,"sampleSingleDataList":[{"sampleId":1,"sampleName":"测试1","sampleQuantity":0,"sampleType":"出样信息"},{"sampleId":2,"sampleName":"测试2","sampleQuantity":0,"sampleType":"出样信息"},{"sampleId":3,"sampleName":"测试3","sampleQuantity":0,"sampleType":"试水台信息"},{"sampleId":4,"sampleName":"测试4","sampleQuantity":0,"sampleType":"试水台信息"},{"sampleId":5,"sampleName":"测试5","sampleQuantity":0,"sampleType":"出样信息"},{"sampleId":6,"sampleName":"测试6","sampleQuantity":0,"sampleType":"出样信息"},{"sampleId":7,"sampleName":"测试7","sampleQuantity":0,"sampleType":"出样信息"},{"sampleId":8,"sampleName":"测试8","sampleQuantity":0,"sampleType":"出样信息"}]}
         */

        private SampleResDataBean sampleResData;

        public SampleResDataBean getSampleResData() {
            return sampleResData;
        }

        public void setSampleResData(SampleResDataBean sampleResData) {
            this.sampleResData = sampleResData;
        }

        public static class SampleResDataBean {
            /**
             * judge : false
             * sampleSingleDataList : [{"sampleId":1,"sampleName":"测试1","sampleQuantity":0,"sampleType":"出样信息"},{"sampleId":2,"sampleName":"测试2","sampleQuantity":0,"sampleType":"出样信息"},{"sampleId":3,"sampleName":"测试3","sampleQuantity":0,"sampleType":"试水台信息"},{"sampleId":4,"sampleName":"测试4","sampleQuantity":0,"sampleType":"试水台信息"},{"sampleId":5,"sampleName":"测试5","sampleQuantity":0,"sampleType":"出样信息"},{"sampleId":6,"sampleName":"测试6","sampleQuantity":0,"sampleType":"出样信息"},{"sampleId":7,"sampleName":"测试7","sampleQuantity":0,"sampleType":"出样信息"},{"sampleId":8,"sampleName":"测试8","sampleQuantity":0,"sampleType":"出样信息"}]
             */

            private boolean judge;
            private List<SampleSingleDataListBean> sampleSingleDataList;

            public boolean isJudge() {
                return judge;
            }

            public void setJudge(boolean judge) {
                this.judge = judge;
            }

            public List<SampleSingleDataListBean> getSampleSingleDataList() {
                return sampleSingleDataList;
            }

            public void setSampleSingleDataList(List<SampleSingleDataListBean> sampleSingleDataList) {
                this.sampleSingleDataList = sampleSingleDataList;
            }

            public static class SampleSingleDataListBean {
                /**
                 * sampleId : 1
                 * sampleName : 测试1
                 * sampleQuantity : 0
                 * sampleType : 出样信息
                 */

                private int sampleId;
                private String sampleName;
                private int sampleQuantity;
                private String sampleType;

                public int getSampleId() {
                    return sampleId;
                }

                public void setSampleId(int sampleId) {
                    this.sampleId = sampleId;
                }

                public String getSampleName() {
                    return sampleName;
                }

                public void setSampleName(String sampleName) {
                    this.sampleName = sampleName;
                }

                public int getSampleQuantity() {
                    return sampleQuantity;
                }

                public void setSampleQuantity(int sampleQuantity) {
                    this.sampleQuantity = sampleQuantity;
                }

                public String getSampleType() {
                    return sampleType;
                }

                public void setSampleType(String sampleType) {
                    this.sampleType = sampleType;
                }
            }

    }
}
