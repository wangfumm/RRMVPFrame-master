package com.hansintelligent.rrrmvpframework.utils;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * 动态权限申请
 * Created by wangfu on 2018/1/15.
 */

public class RxPermissionHelper {


    private PermissionCallbacks listener;


    private RxPermissionHelper(PermissionCallbacks listener) {

        this.listener = listener;

    }


    public static RxPermissionHelper newInstance(PermissionCallbacks listener) {
        return new RxPermissionHelper(listener);
    }


    /**
     * 申请权限
     *
     * @param activity
     * @param permissions
     */
    public void requestPermissions(Activity activity, String... permissions) {

        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(permissions)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            listener.onPermissionsGranted();
                        } else {
                            listener.onPermissionsDenied();
                        }
                    }
                });
    }


    /**
     * 申请权限接口回调
     */
    public interface PermissionCallbacks {

        //同意授权
        void onPermissionsGranted();

        //拒绝授权
        void onPermissionsDenied();

    }

}
