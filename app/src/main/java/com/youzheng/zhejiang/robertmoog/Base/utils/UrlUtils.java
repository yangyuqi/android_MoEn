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

    public static String LOGIN_OUT = BASE_URL+"/v1/app/user/logout";
    public static String CUSTOMER_INTENT=BASE_URL+"/v1/app/customer/customerIntent";
    public static String DELETE_INTENT = BASE_URL+"/v1/app/customer/deleteIntent";
    public static String DELETE_GOODS = BASE_URL+"/v1/app/customer/deleteIntentProduct";

    public static String GET_CUSTOMER_LIST = BASE_URL+"/v1/app/shop/coustomer";
    public static String GET_CUSTOMER_LIST_DETAIL = BASE_URL+"/v1/app/shop/monthCoustomer";

}
