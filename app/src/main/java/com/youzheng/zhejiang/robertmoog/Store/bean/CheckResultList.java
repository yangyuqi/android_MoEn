package com.youzheng.zhejiang.robertmoog.Store.bean;

import java.util.List;

public class CheckResultList {

        private List<PatrolShopListBean> patrolShopList;

        public List<PatrolShopListBean> getPatrolShopList() {
            return patrolShopList;
        }

        public void setPatrolShopList(List<PatrolShopListBean> patrolShopList) {
            this.patrolShopList = patrolShopList;
        }

        public static class PatrolShopListBean {
            /**
             * id : 1
             * time : mock
             * result : mock
             * reason : mock
             */

            private int id;
            private String time;
            private String result;
            private String reason;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getResult() {
                return result;
            }

            public void setResult(String result) {
                this.result = result;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }
        }

}
