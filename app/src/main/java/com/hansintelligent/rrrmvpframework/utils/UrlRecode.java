package com.hansintelligent.rrrmvpframework.utils;

import java.io.UnsupportedEncodingException;

/**
 * 图片无法解析中文  url转码
 * Created by wangfu on 2017/5/27.
 */
public class UrlRecode {

    public static String utf8Togb2312(String str) {
        String data = "";
        try {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c + "".getBytes().length > 1 && c != ':' && c != '/') {
                    data = data + java.net.URLEncoder.encode(c + "", "utf-8");
                } else {
                    data = data + c;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            System.out.println(data);
        }
        return data;
    }

}
