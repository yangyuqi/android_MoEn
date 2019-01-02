package com.youzheng.zhejiang.robertmoog.Store.bean;

import java.util.List;

public class UnqualifiedContent {

        /**
         * patrolShopProblemDetail : {"questionId":1,"questionName":"LOGO，灯箱和品牌广告画面符合公司 品","questionDes":"商品摆放不整齐","questionImages":[{"imageImgUrl":"www.baidu.com"}]}
         */

        private PatrolShopProblemDetailBean patrolShopProblemDetail;

        public PatrolShopProblemDetailBean getPatrolShopProblemDetail() {
            return patrolShopProblemDetail;
        }

        public void setPatrolShopProblemDetail(PatrolShopProblemDetailBean patrolShopProblemDetail) {
            this.patrolShopProblemDetail = patrolShopProblemDetail;
        }

        public static class PatrolShopProblemDetailBean {
            /**
             * questionId : 1
             * questionName : LOGO，灯箱和品牌广告画面符合公司 品
             * questionDes : 商品摆放不整齐
             * questionImages : [{"imageImgUrl":"www.baidu.com"}]
             */

            private int questionId;
            private String questionName;
            private String questionDes;
            private List<QuestionImagesBean> questionImages;

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

            public String getQuestionDes() {
                return questionDes;
            }

            public void setQuestionDes(String questionDes) {
                this.questionDes = questionDes;
            }

            public List<QuestionImagesBean> getQuestionImages() {
                return questionImages;
            }

            public void setQuestionImages(List<QuestionImagesBean> questionImages) {
                this.questionImages = questionImages;
            }

            public static class QuestionImagesBean {
                /**
                 * imageImgUrl : www.baidu.com
                 */

                private String imageImgUrl;

                public String getImageImgUrl() {
                    return imageImgUrl;
                }

                public void setImageImgUrl(String imageImgUrl) {
                    this.imageImgUrl = imageImgUrl;
                }
            }

    }
}
