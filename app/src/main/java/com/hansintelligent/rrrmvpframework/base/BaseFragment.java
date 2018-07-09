package com.hansintelligent.rrrmvpframework.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hansintelligent.rrrmvpframework.application.MyApplication;
import com.hansintelligent.rrrmvpframework.base.mvp.IView;
import com.hansintelligent.rrrmvpframework.utils.RxPermissionHelper;
import com.hansintelligent.rrrmvpframework.widget.CustomToast;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.RxFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * BaseFragment
 * Created by wangfu on 2016/11/23.
 */
public abstract class BaseFragment extends RxFragment implements RxPermissionHelper.PermissionCallbacks, IView {

    protected Activity mActivity;


    protected Unbinder unBinder;//butterknife 绑定控件

    //Fragment创建
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();//获取当前fragment所依赖的Activity
    }

    //初始化fragment布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = initView();
        unBinder = ButterKnife.bind(this, view);
        if (useEventbus()) {
            EventBus.getDefault().register(this);//注册eventBus
        }
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unBinder != null) {
            unBinder.unbind();
        }
        if (useEventbus()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);//注销eventBus
            }
        }
        CustomToast.INSTANCE.cancelToast();//销毁 自定义toast
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.getRefWatcher(mActivity).watch(mActivity);//内存泄露检测
    }


    //fragment所依赖的Activity的onCreate方法执行结束
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        initData();
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

    /**
     * bind rxlifecycle
     *
     * @return
     */
    @Override
    public <T> LifecycleTransformer<T> bindLifecycle() {
        return super.bindUntilEvent(FragmentEvent.PAUSE);
    }

    /**
     * 统一封装toast
     *
     * @param text
     */
    protected void showToast(String text) {
        CustomToast.INSTANCE.showToast(mActivity, text);
    }

    /**
     * 是否使用eventBus
     *
     * @return
     */
    protected boolean useEventbus() {
        return false;
    }


    //初始化布局，必须由子类实现
    protected abstract View initView();

    //初始化数据,必须由子类实现
    protected abstract void initData();
}
