package com.hansintelligent.rrrmvpframework.base.mvp;



/**
 * IPresenter
 * Created by wangfu on 2018/5/15.
 */

public interface IPresenter<T extends IView> {


    /**
     * 关联p与V
     *
     * @param view
     */
    void attachView(T view);

    /**
     * 取消关联p与v
     */
    void detachView();



}
