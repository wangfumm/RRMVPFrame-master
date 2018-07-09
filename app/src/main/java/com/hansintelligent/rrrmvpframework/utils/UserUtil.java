package com.hansintelligent.rrrmvpframework.utils;

import android.text.TextUtils;

import com.hansintelligent.rrrmvpframework.application.MyApplication;
import com.hansintelligent.rrrmvpframework.bean.UserInfo;

/**
 * UserUtil
 * Created by wangfu on 2018/5/31.
 */

public class UserUtil {

    private static UserUtil instance;

    private UserInfo mUserInfo;

    public static UserUtil getInstance() {
        if (null == instance) {
            synchronized (UserUtil.class) {
                if (null == instance) {
                    instance = new UserUtil();
                }
            }
        }
        return instance;
    }

    public UserInfo get() {
        if (mUserInfo != null) {
            return mUserInfo;
        }
        mUserInfo = ((UserInfo) ACache.get(MyApplication.getContext()).getAsObject("userinfo"));
        if (mUserInfo != null) {
            return mUserInfo;
        }
        return null;
    }

    public void update(UserInfo userInfo) {
        ACache.get(MyApplication.getContext()).put("userinfo", userInfo);
        mUserInfo = ((UserInfo) ACache.get(MyApplication.getContext()).getAsObject("userinfo"));
    }

    public void remove() {
        ACache.get(MyApplication.getContext()).remove("userinfo");
        mUserInfo = null;
    }

    public String getUserId() {
        if (!TextUtils.isEmpty(get().getId())) {
            return get().getId();
        }
        return null;
    }

    public String getMobile() {
        if (!TextUtils.isEmpty(get().getMobile())) {
            return get().getMobile();
        }
        return null;
    }

}
