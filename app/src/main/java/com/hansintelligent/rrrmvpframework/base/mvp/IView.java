package com.hansintelligent.rrrmvpframework.base.mvp;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * IView
 * Created by wangfu on 2018/5/15.
 */

public interface IView {


    <T> LifecycleTransformer <T>bindLifecycle();

    void showLoading();

    void hideLoading();


}
