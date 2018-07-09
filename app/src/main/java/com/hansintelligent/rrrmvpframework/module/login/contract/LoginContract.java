package com.hansintelligent.rrrmvpframework.module.login.contract;


import com.hansintelligent.rrrmvpframework.base.BaseResponse;
import com.hansintelligent.rrrmvpframework.base.mvp.IView;
import com.hansintelligent.rrrmvpframework.bean.UserInfo;

import io.reactivex.Observable;

/**
 * LoginContract
 * Created by wangfu on 2017/10/20.
 */

public interface LoginContract {

    interface IPresenterContract {
        void login();

        void getUserInfo();
    }

    interface IViewContract extends IView {

        void loginSuccess();

        void loginFailure();

        void getUserInfoSuccess();

        void getUserInfoFailure();


    }

    interface IModelContract {

        Observable<BaseResponse> login(String code, String psd);

        Observable<BaseResponse<UserInfo>> getUserInfo();
    }

}
