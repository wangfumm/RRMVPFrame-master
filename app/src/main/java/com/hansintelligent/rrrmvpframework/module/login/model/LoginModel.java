package com.hansintelligent.rrrmvpframework.module.login.model;


import com.hansintelligent.rrrmvpframework.base.BaseResponse;
import com.hansintelligent.rrrmvpframework.base.mvp.BaseModel;
import com.hansintelligent.rrrmvpframework.bean.UserInfo;
import com.hansintelligent.rrrmvpframework.module.login.contract.LoginContract;
import com.hansintelligent.rrrmvpframework.constant.APIConstant;
import com.hansintelligent.rrrmvpframework.network.APIService;


import java.util.Map;

import io.reactivex.Observable;

/**
 * LoginModel
 * Created by wangfu on 2018/5/8.
 */

public class LoginModel extends BaseModel implements LoginContract.IModelContract {

    private APIService service;

    public LoginModel() {
        service = createService(APIService.class);
    }

    @Override
    public Observable<BaseResponse> login(String code, String psd) {
        String url = APIConstant.LOGIN;

        Map<String, String> map = createBaseParamMap();
        map.put("Mobile", "18856422586");
        map.put("Password", "123456");

        return service.login(url, map);
    }

    @Override
    public Observable<BaseResponse<UserInfo>> getUserInfo() {
        String url = APIConstant.GET_USER_INFO;
        Map<String, String> map = createBaseParamMap();
        map.put("Mobile", "18856422586");

        return service.getUserInfo(url, map);
    }
}
