package com.youzheng.zhejiang.robertmoog.Store.bean;

import java.util.List;

public class PeopleMangerList {


        /**
         * shopName : 23qwe
         * shopPersonalList : [{"name":"mock","phone":"mock","businessRole":{"id":"mock","des":"mock"},"id":1,"status":{"id":"mock","des":"mock"}}]
         */

        private String shopName;
        private List<ShopPersonalListBean> shopPersonalList;

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public List<ShopPersonalListBean> getShopPersonalList() {
            return shopPersonalList;
        }

        public void setShopPersonalList(List<ShopPersonalListBean> shopPersonalList) {
            this.shopPersonalList = shopPersonalList;
        }

        public static class ShopPersonalListBean {
            /**
             * name : mock
             * phone : mock
             * businessRole : {"id":"mock","des":"mock"}
             * id : 1
             * status : {"id":"mock","des":"mock"}
             */

            private String name;
            private String phone;
            private BusinessRoleBean businessRole;
            private int id;
            private StatusBean status;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public BusinessRoleBean getBusinessRole() {
                return businessRole;
            }

            public void setBusinessRole(BusinessRoleBean businessRole) {
                this.businessRole = businessRole;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public StatusBean getStatus() {
                return status;
            }

            public void setStatus(StatusBean status) {
                this.status = status;
            }

            public static class BusinessRoleBean {
                /**
                 * id : mock
                 * des : mock
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

            public static class StatusBean {
                /**
                 * id : mock
                 * des : mock
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
