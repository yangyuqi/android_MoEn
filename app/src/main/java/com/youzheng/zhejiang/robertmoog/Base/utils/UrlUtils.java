package com.youzheng.zhejiang.robertmoog.Base.utils;

public class UrlUtils {
    public static String BASE_URL = "https://demo.waycomtech.com/api";
    public static String LOGIN = BASE_URL+"/oauth/token";
    public static String REGISTER_USER = BASE_URL+"/v1/app/customer/registerCustomer";
    public static String SEND_CODE = BASE_URL+"/v1/app/user/getCheckCode";
    public static String GET_USER_ONFO = BASE_URL+"/v1/app/user/getUserConfig";
    public static String CUSTOMER_SHOP = BASE_URL+"/v1/app/customer/shopCustomerIntent";
    public static String SHOP_PERSONAL = BASE_URL+"/v1/app/customer/shopPersonal";
    public static String NOT_LABEEL = BASE_URL+"/v1/app/customer/notLabel";
    public static String SHOP_SCVAN = BASE_URL+"/v1/app/customer/shopQRCode";
    public static String ATTENTION_GOODS_LIST = BASE_URL+"/v1/app/customer/intentProductList";
    public static String HOME_INFO = BASE_URL+"/v1/app/user/getHomePage";
    public static String GET_CUSTOMER = BASE_URL+"/v1/app/customer/getCustomer";
    public static String UPDATE_INTENT_REMARK = BASE_URL+"/v1/app/customer/updateRemark";

    public static String LOGIN_OUT = BASE_URL+"/v1/app/user/logout";
    public static String CUSTOMER_INTENT=BASE_URL+"/v1/app/customer/customerIntent";
    public static String DELETE_INTENT = BASE_URL+"/v1/app/customer/deleteIntent";
    public static String DELETE_GOODS = BASE_URL+"/v1/app/customer/deleteIntentProduct";

    public static String GET_CUSTOMER_LIST = BASE_URL+"/v1/app/shop/coustomer";
    public static String GET_CUSTOMER_LIST_DETAIL = BASE_URL+"/v1/app/shop/monthCoustomer";
    public static String GOODS_LIST_TYPE = BASE_URL+"/v1/app/shop/getProductCategory";
    public static String GOODS_LIST = BASE_URL+"/v1/app/shop/getProductList";
    public static String GOODS_LIST_DETAIL = BASE_URL+"/v1/app/shop/getProductDetail";

    public static String PROFESSIONAL_CUSTOMER_LIST = BASE_URL+"/v1/app/shop/specialtyCustomer";
    public static String ADD_PROFESSIONAL_CUSTOMER = BASE_URL+"/v1/app/shop/addCustomer";
    public static String SCAN_GOODS = BASE_URL+"/v1/app/customer/selectProduct";

    public static String PEOPLE_MAGER_LIST = BASE_URL+"/v1/app/shop/personal";
    public static String ADD_SELLER = BASE_URL+"/v1/app/shop/addPersonal";
    public static String ADDRESS_MANAGER = BASE_URL+"/v1/app/customer/customerAddress";
    public static String ADD_ADDRESS = BASE_URL+"/v1/app/customer/save/customerAddress";
    public static String SALES_GOODS = BASE_URL+"/v1/app/customer/sale";

}
