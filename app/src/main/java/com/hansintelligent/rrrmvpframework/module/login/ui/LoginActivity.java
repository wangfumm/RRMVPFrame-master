package com.hansintelligent.rrrmvpframework.module.login.ui;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hansintelligent.rrrmvpframework.R;
import com.hansintelligent.rrrmvpframework.base.BaseActivity;
import com.hansintelligent.rrrmvpframework.bean.UserInfo;
import com.hansintelligent.rrrmvpframework.module.login.UserTestActivity;
import com.hansintelligent.rrrmvpframework.module.login.contract.LoginContract;
import com.hansintelligent.rrrmvpframework.module.login.presenter.LoginPresenter;
import com.hansintelligent.rrrmvpframework.utils.Logger;
import com.hansintelligent.rrrmvpframework.utils.RxPermissionHelper;
import com.hansintelligent.rrrmvpframework.utils.UserUtil;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;


import java.io.File;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;


/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements LoginContract.IViewContract {

    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.layout_login)
    LinearLayout ll_login;
    private LoginPresenter mPresenter;

    private Dialog mLoadingDialog;//loadingDialog


    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new LoginPresenter();
        mPresenter.attachView(this);//关联P与V

        ll_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        initView();

        UserInfo userInfo = new UserInfo();
        userInfo.setId("111111");
        userInfo.setNickName("我爱罗");
        userInfo.setChecked(true);


        Log.d("userInfo:", userInfo.toString());


//        UserInfo userInfo = new UserInfo();
//        userInfo.setId("123456");
//        userInfo.setNickName("萝莉");
//        userInfo.setMobile("19912345678");
//        UserUtil.getInstance().update(userInfo);
//        userInfo = null;


//        compressImage();

    }

    private void compressImage(String path) {

        Observable.just(path)//批量压缩，建议使用Flowable。下游处理图片有可能会导致背压的问题
                .observeOn(Schedulers.io())
                .map(new Function<String, File>() {
                    @Override
                    public File apply(@NonNull String s) throws Exception {
                        return Luban.with(LoginActivity.this).get(s);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<File>() {
                    @Override
                    public void accept(@NonNull File file) throws Exception {
                        // TODO: 2018/5/25 显示图片
                    }
                });

    }


    /**
     * initView
     */
    @Override
    protected void initView() {


        ll_login = ((LinearLayout) findViewById(R.id.layout_login));

        //没有数据时点击屏幕刷新数据
        ll_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.login();
//                startActivity(new Intent(LoginActivity.this, UserTestActivity.class));
//                finish();
//                startActivity(new Intent(LoginActivity.this, ZXingScanActivity.class));
            }
        });

        String[] permissions = new String[]{Manifest.permission.INTERNET, Manifest.permission.VIBRATE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        RxPermissionHelper.newInstance(this).requestPermissions(this, permissions);

    }

    /**
     * initData
     */
    @Override
    protected void initData() {

        Observable.intervalRange(1, 30, 10, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        Logger.d(TAG, "onNext：" + (20 - aLong));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.d(TAG, "onError:" + e);
                    }

                    @Override
                    public void onComplete() {
                        Logger.d(TAG, "onComplete");
                    }
                });


    }

    @Override
    protected void onResume() {
        super.onResume();
//        mPresenter.login();//登录接口
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();//取消关联P 与 V
        super.onDestroy();
    }

    /**
     * 显示全局加载页面
     */
    @Override
    public void showLoading() {
//        mLoadingDialog = McBlogDialog.createLoadingDialog(this, "登录中...");
    }


    /**
     * 隐藏全局加载页面
     */
    @Override
    public void hideLoading() {
//        McBlogDialog.closeDialog(mLoadingDialog);
    }


    /**
     * 登录成功
     */
    @Override
    public void loginSuccess() {

        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        mPresenter.getUserInfo();//获取用户信息


    }

    /**
     * 登录失败
     */
    @Override
    public void loginFailure() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        ll_login.setVisibility(View.GONE);
    }


    /**
     * 获取用户信息成功
     */
    @Override
    public void getUserInfoSuccess() {
        Toast.makeText(this, "获取用户信息成功", Toast.LENGTH_SHORT).show();
        ll_login.setVisibility(View.VISIBLE);


//        startActivity(new Intent(this, HomeActivity.class));

    }


    /**
     * 获取用户信息失败
     */
    @Override
    public void getUserInfoFailure() {

        ll_login.setVisibility(View.GONE);

    }


    /**
     * 动态权限申请成功
     */
    @Override
    public void onPermissionsGranted() {

    }

    /**
     * 动态权限申请失败
     */
    @Override
    public void onPermissionsDenied() {

    }
    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;
    private static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application){

        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

//        if ()

    }
}
