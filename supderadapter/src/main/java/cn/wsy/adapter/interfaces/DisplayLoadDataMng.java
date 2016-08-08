package cn.wsy.adapter.interfaces;


import cn.wsy.adapter.base.MyViewHolder;

/**
 * Created by wsy on 2016/8/1.
 */
public interface DisplayLoadDataMng {

    public void setLoadMoreListener(LoadMoreCallbak loadMoreCallbak);

    public void setRefreshListener();

    void stopLoadMore();

    void startLoadMore(MyViewHolder holder, int realPostion);

    void startRefresh();

    void stopRefresh();
}
