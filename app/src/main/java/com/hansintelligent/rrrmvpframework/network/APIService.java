package com.hansintelligent.rrrmvpframework.network;


import com.hansintelligent.rrrmvpframework.base.BaseResponse;
import com.hansintelligent.rrrmvpframework.bean.UserInfo;

import java.util.Map;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * APIService
 * Created by wangfu on 2017/10/20.
 */

public interface APIService {


    @POST("{login}")
    Observable<BaseResponse> login(@Path("login") String url, @Body Map<String, String> params);


    @POST("{getUserinfo}")
    Observable<BaseResponse<UserInfo>> getUserInfo(@Path("getUserinfo") String url, @Body Map<String, String> params);

}
