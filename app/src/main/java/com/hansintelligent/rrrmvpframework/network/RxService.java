package com.hansintelligent.rrrmvpframework.network;


import com.hansintelligent.rrrmvpframework.constant.APIConstant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RxService
 * Created by wangfu on 2017/10/20.
 */

public enum RxService {

    RETROFIT;
    private Retrofit mRetrofit;
    private static final int READ_TIMEOUT = 60;//读取超时时间,单位秒
    private static final int CONN_TIMEOUT = 50;//连接超时时间,单位秒


    /**
     * 初始化一个client,不然retrofit会自己默认添加一个
     */
    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("Content-Type", "application/json");
                    return chain.proceed(builder.build());
                }
            })
            .connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)//设置连接时间为50s
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//设置读取时间为一分钟
            .build();


    /**
     * @description 创建Retrofit对象
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public Retrofit createRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .client(httpClient)
                    .baseUrl(APIConstant.BASE_PATH) //api base path
                    .addConverterFactory(GsonConverterFactory.create())//返回值为Gson的支持(以实体类返回)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//返回值为Oservable<T>的支持
                    .build();
        }
        return mRetrofit;
    }

    /**
     * @description 返回服务接口对象实例
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public <T> T createService(final Class<T> service) {
        validateServiceInterface(service);//校验接口合法性
        return RxService.RETROFIT.createRetrofit().create(service);
    }


    /**
     * @description 校验接口合法性
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public <T> void validateServiceInterface(Class<T> service) {
        if (service == null) {
            //Toast.ShowToast("服务接口不能为空！");
        }
        if (!service.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
    }


}
