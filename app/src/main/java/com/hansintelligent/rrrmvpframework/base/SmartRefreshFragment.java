package com.hansintelligent.rrrmvpframework.base;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * 带刷新功能的Fragment
 * <p>
 * 实现了下拉刷新和上拉加载更多
 * Created by wangfu on 2018/1/19.
 */

public abstract class SmartRefreshFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {



    /**
     * 下拉刷新
     *
     * @param refreshlayout
     */
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

    }

    /**
     * 上拉加载
     *
     * @param refreshlayout
     */
    @Override
    public void onLoadMore(RefreshLayout refreshlayout) {

    }
}
