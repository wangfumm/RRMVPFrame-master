package com.hansintelligent.rrrmvpframework.module.login.presenter;


import com.hansintelligent.rrrmvpframework.base.BaseResponse;
import com.hansintelligent.rrrmvpframework.base.mvp.BasePresenter;
import com.hansintelligent.rrrmvpframework.bean.UserInfo;
import com.hansintelligent.rrrmvpframework.module.login.contract.LoginContract;
import com.hansintelligent.rrrmvpframework.module.login.model.LoginModel;
import com.hansintelligent.rrrmvpframework.network.APICallBack;
import com.hansintelligent.rrrmvpframework.network.RxTransformer;
import com.hansintelligent.rrrmvpframework.utils.Logger;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * LoginPresenter
 * Created by wangfu on 2017/10/20.
 */

public class LoginPresenter extends BasePresenter<LoginContract.IViewContract> implements LoginContract.IPresenterContract {

    private static final String TAG = LoginPresenter.class.getSimpleName();
    private LoginModel mLoginModel;


    public LoginPresenter() {
        mLoginModel = new LoginModel();
    }


    /**
     * login
     */
    @Override
    public void login() {

        mLoginModel.login("13248365219", "123456")
                .compose(RxTransformer.<BaseResponse>transformer(getMvpView()))
                .subscribe(new APICallBack<BaseResponse>() {

                    @Override
                    public void onStart(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(BaseResponse modelBean) {
                        getMvpView().loginSuccess();
                        Logger.d(TAG, modelBean.getContent() + "");
                    }

                    @Override
                    public void onFailure(String errorMsg) {

                        getMvpView().loginFailure();
                    }

                    @Override
                    public void onFinished() {
                        //getmMvpView().hideLoading();
                    }
                });

    }

    /**
     * 获取用户信息
     */
    @Override
    public void getUserInfo() {

        mLoginModel.getUserInfo()
                .compose(RxTransformer.<BaseResponse<UserInfo>>transformer(getMvpView()))
                .subscribe(new APICallBack<BaseResponse<UserInfo>>() {

                    @Override
                    public void onStart(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(BaseResponse<UserInfo> modelBean) {
                        getMvpView().getUserInfoSuccess();
                        Logger.d(TAG, modelBean.getContent() + "");
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        getMvpView().getUserInfoFailure();
                    }

                    @Override
                    public void onFinished() {
                        getMvpView().hideLoading();
                    }
                });
    }
}
