package com.hansintelligent.rrrmvpframework.constant;

import okhttp3.MediaType;

/**
 * APIConstant
 * Created by wangfu on 2017/10/20.
 */

public class APIConstant {

    public final static String BASE_PATH = "http://www.ubusclub.com:2090/Member/";

    public static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");


    //登录
    public final static String LOGIN = "Login";
    //获取用户信息
    public final static String GET_USER_INFO = "GetMemberInfo";


}
