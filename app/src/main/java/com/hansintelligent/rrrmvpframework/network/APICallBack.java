package com.hansintelligent.rrrmvpframework.network;


import com.hansintelligent.rrrmvpframework.base.BaseResponse;
import com.hansintelligent.rrrmvpframework.utils.Logger;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 封装请求返回
 * Created by wangfu on 2017/10/12.
 */

public abstract class APICallBack<M> implements Observer<M> {


    private static final String TAG = APICallBack.class.getSimpleName();

    public abstract void onStart(@NonNull Disposable d);

    public abstract void onSuccess(M modelBean);

    public abstract void onFailure(String errorMsg);

    public abstract void onFinished();


    @Override
    public void onSubscribe(@NonNull Disposable d) {
        onStart(d);
    }

    @Override
    public void onNext(@NonNull M modelBean) {
        Logger.d(TAG, modelBean.toString());
        BaseResponse<M> response = (BaseResponse<M>) modelBean;
        if ("200".equals(response.getCode())) {
            onSuccess(modelBean);
        } else {
            onFailure(response.getMessage());
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int exceptionCode = httpException.code();
            String msg = httpException.getMessage();
            if (exceptionCode == 401) {
                msg = "用户名密码错误，请重新登录！";
            }
            if (exceptionCode == 403 || exceptionCode == 404 || exceptionCode == 407 || exceptionCode == 408) {
                msg = "网络链接超时，请稍后再试！";
            }
            if (exceptionCode == 501 || exceptionCode == 502 || exceptionCode == 504) {
                msg = "服务器无响应，请稍后再试！";
            }
            onFailure(msg);
        } else {
            onFailure(e.getMessage());
        }
        onFinished();

    }

    @Override
    public void onComplete() {
        onFinished();
    }

}
