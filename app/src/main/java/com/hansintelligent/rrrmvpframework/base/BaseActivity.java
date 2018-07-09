package com.hansintelligent.rrrmvpframework.base;

import android.os.Bundle;

import com.hansintelligent.rrrmvpframework.application.MyApplication;
import com.hansintelligent.rrrmvpframework.base.mvp.IView;
import com.hansintelligent.rrrmvpframework.utils.RxPermissionHelper;
import com.hansintelligent.rrrmvpframework.utils.ScreenAdaptation;
import com.hansintelligent.rrrmvpframework.widget.CustomToast;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * BaseActivity
 */
public abstract class BaseActivity extends RxFragmentActivity implements RxPermissionHelper.PermissionCallbacks, IView {

    protected Unbinder unBinder;//butterknife 绑定控件


    /**
     * 获取布局Id
     *
     * @return
     */
    protected abstract int getContentViewId();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenAdaptation.setCustomDensity(this, getApplication());//修改屏幕密度
        setContentView(getContentViewId());
        unBinder = ButterKnife.bind(this);//butterKnife绑定view
        if (useEventBus()) {
            EventBus.getDefault().register(this);//注册eventBus
        }
    }

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 是否使用eventBus
     *
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }


    /**
     * 动态申请权限成功回调
     */
    @Override
    public void onPermissionsGranted() {

    }

    /**
     * 动态申请权限失败回调
     */
    @Override
    public void onPermissionsDenied() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    /**
     * bind rxlifecycle
     *
     * @return
     */
    @Override
    public <T> LifecycleTransformer<T> bindLifecycle() {
        return this.bindUntilEvent(ActivityEvent.PAUSE);
    }


    @Override
    protected void onDestroy() {
        if (unBinder != null) {
            unBinder.unbind();//butterKnife解绑view
        }
        if (useEventBus()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);//注销eventBus
            }
        }
        CustomToast.INSTANCE.cancelToast();//销毁 自定义toast
        super.onDestroy();
        MyApplication.getRefWatcher(this).watch(this);//内存泄漏检测
    }

    /**
     * 统一封装toast
     *
     * @param text
     */
    protected void showToast(String text) {
        CustomToast.INSTANCE.showToast(this, text);
    }


}
