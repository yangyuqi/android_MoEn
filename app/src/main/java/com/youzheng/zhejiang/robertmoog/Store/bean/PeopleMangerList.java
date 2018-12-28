package com.youzheng.zhejiang.robertmoog.Store.bean;

import java.util.List;

public class PeopleMangerList {

        /**
         * shopName : 23qwe
         * shopPersonalList : [{"name":"mock","phone":"mock","businessRole":"mock","id":1,"status":"mock"}]
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
             * businessRole : mock
             * id : 1
             * status : mock
             */

            private String name;
            private String phone;
            private String businessRole;
            private int id;
            private String status;

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

            public String getBusinessRole() {
                return businessRole;
            }

            public void setBusinessRole(String businessRole) {
                this.businessRole = businessRole;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }

}
