package com.youzheng.zhejiang.robertmoog.Store.bean;

import java.util.List;

public class CheckStoreDetail {



        /**
         * patrolShopDetail : [{"patrolShopId":1,"questionId":1,"questionName":"产品陈列整齐规范，无空缺!","questionStatus":{"id":"QUALIFIED","des":"合格"},"isProblem":"false"},{"patrolShopId":1,"questionId":2,"questionName":"员工仪表1","questionStatus":{"id":"NO_QUALIFIED","des":"不合格"},"isProblem":"false"}]
         * remarks : 啦啦啦啦
         */

        private String remarks;
        private List<PatrolShopDetailBean> patrolShopDetail;

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public List<PatrolShopDetailBean> getPatrolShopDetail() {
            return patrolShopDetail;
        }

        public void setPatrolShopDetail(List<PatrolShopDetailBean> patrolShopDetail) {
            this.patrolShopDetail = patrolShopDetail;
        }

        public static class PatrolShopDetailBean {
            /**
             * patrolShopId : 1
             * questionId : 1
             * questionName : 产品陈列整齐规范，无空缺!
             * questionStatus : {"id":"QUALIFIED","des":"合格"}
             * isProblem : false
             */

            private int patrolShopId;
            private int questionId;
            private String questionName;
            private QuestionStatusBean questionStatus;
            private String isProblem;

            public int getPatrolShopId() {
                return patrolShopId;
            }

            public void setPatrolShopId(int patrolShopId) {
                this.patrolShopId = patrolShopId;
            }

            public int getQuestionId() {
                return questionId;
            }

            public void setQuestionId(int questionId) {
                this.questionId = questionId;
            }

            public String getQuestionName() {
                return questionName;
            }

            public void setQuestionName(String questionName) {
                this.questionName = questionName;
            }

            public QuestionStatusBean getQuestionStatus() {
                return questionStatus;
            }

            public void setQuestionStatus(QuestionStatusBean questionStatus) {
                this.questionStatus = questionStatus;
            }

            public String getIsProblem() {
                return isProblem;
            }

            public void setIsProblem(String isProblem) {
                this.isProblem = isProblem;
            }

            public static class QuestionStatusBean {
                /**
                 * id : QUALIFIED
                 * des : 合格
                 */

                private String id;
                private String des;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

    }
}
