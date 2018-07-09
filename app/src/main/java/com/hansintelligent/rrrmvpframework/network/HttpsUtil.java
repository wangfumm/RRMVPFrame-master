package com.hansintelligent.rrrmvpframework.network;




import com.hansintelligent.rrrmvpframework.application.MyApplication;
import com.hansintelligent.rrrmvpframework.utils.Logger;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * HttpsUtil
 * Created by wangfu on 2018/5/10.
 */

public class HttpsUtil {

    private static final String CLIENT_TRUST_PASSWORD = "123456"; //信任证书密码，该证书默认密码是changeit
    private static final String CLIENT_AGREEMENT = "TLS";// 使用协议
    private static final String CLIENT_TRUST_MANAGER = "X509";
    private static final String CLIENT_TRUST_KEYSTORE = "BKS";
    SSLContext sslContext = null;

    int resourseId = 0;
    public SSLContext getSslSocket() {
        try { //取得SSL的SSLContext实例
            sslContext = SSLContext.getInstance(CLIENT_AGREEMENT);
            //取得TrustManagerFactory的X509密钥管理器实例

            TrustManagerFactory trustManager = TrustManagerFactory.getInstance(CLIENT_TRUST_MANAGER);
            //取得BKS密库实例

            KeyStore tks = KeyStore.getInstance(CLIENT_TRUST_KEYSTORE);
            InputStream is = MyApplication.getContext().getResources().openRawResource(resourseId);
            try {
                tks.load(is, CLIENT_TRUST_PASSWORD.toCharArray());
            } finally {
                is.close();
            }
            //初始化密钥管理器 trustManager.init(tks);
            // 初始化SSLContext
            sslContext.init(null, trustManager.getTrustManagers(), null);
        } catch (Exception e) {
            Logger.e("SslContextFactory", e.getMessage());
        }
        return sslContext;
    }





}
