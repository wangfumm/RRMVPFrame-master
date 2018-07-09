package com.hansintelligent.rrrmvpframework.base.mvp;



/**
 * BasePresenter
 * Created by wangfu on 2018/5/15.
 */

public abstract class BasePresenter<T extends IView> implements IPresenter<T> {




    private T mMvpView;

    /**
     * getMvpView
     *
     * @return
     */
    protected T getMvpView() {
        return mMvpView;
    }

    /**
     * 关联 P 与 V
     *
     * @param view
     */
    @Override
    public void attachView(T view) {
        this.mMvpView = view;
    }

    /**
     * 取消关联P与V
     */
    @Override
    public void detachView() {
        mMvpView = null;
    }


}
