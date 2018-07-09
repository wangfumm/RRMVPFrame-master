package com.hansintelligent.rrrmvpframework.network;


import com.hansintelligent.rrrmvpframework.base.mvp.IView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * RxTransformer
 * Created by wangfu on 2018/5/22.
 */
public class RxTransformer {

    /**
     * 无参数，仅做线程切换
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> transformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.throttleFirst(500, TimeUnit.MILLISECONDS)//防抖
                        .subscribeOn(Schedulers.io())//线程切换
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    /**
     * 界面请求，不需要加载和隐藏loading时调用 使用RxLifeCycle
     * 传入view接口，Activity，Fragment等实现了view接口，Activity，Fragment继承于{@link com.trello.rxlifecycle2.components.support.RxAppCompatActivity}
     * 也就实现了bindToLifecycle方法
     *
     * @param view View
     * @param <T>  泛型
     * @return
     */
    public static <T> ObservableTransformer<T, T> transformer(final IView view) {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                if (null == view) {
                    return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());//线程调度
                }
                return upstream.throttleFirst(500, TimeUnit.MILLISECONDS)//防抖
                        .subscribeOn(Schedulers.io())//线程切换
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(view.<T>bindLifecycle());//绑定生命周期
            }
        };
    }

//    /**
//     * 界面请求，需要加载和隐藏loading时调用,使用RxLifeCycle
//     * 传入view接口，Activity，Fragment等实现了view接口，Activity，Fragment继承于{@link com.trello.rxlifecycle2.components.support.RxAppCompatActivity}
//     * 也就实现了bindToLifecycle方法
//     * @param view View
//     * @param <T> 泛型
//     * @return
//     */
//    public static <T> ObservableTransformer<T, T> transformWithLoading(final IView view) {
//        return observable -> observable.subscribeOn(Schedulers.io())
//                .doOnSubscribe(disposable -> {
//                    view.showLoading();//显示进度条
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .doFinally(() -> {
//                    view.hideLoading();//隐藏进度条
//                }).compose(view.bindToLifecycle());
//    }


}
