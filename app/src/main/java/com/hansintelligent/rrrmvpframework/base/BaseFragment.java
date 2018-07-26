package com.prookie.googleplaystore.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * BaseFragment
 * Created by wangfu on 2016/11/23.
 */
public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;
    private View rootView;
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
        if (rootView == null) {
            rootView = inflater.inflate(getContentLayoutId(), container, false);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        unBinder = ButterKnife.bind(this, view);
        if (useEventbus()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);//注册eventBus
            }
        }
        initView();
        initData();
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
    }


    /**
     * 是否使用eventBus
     *
     * @return
     */
    protected boolean useEventbus() {
        return false;
    }

    //获取layoutId,必须由子类实现
    public abstract int getContentLayoutId();

    //初始化布局，必须由子类实现
    protected abstract void initView();

    //初始化数据,必须由子类实现
    protected abstract void initData();
}
