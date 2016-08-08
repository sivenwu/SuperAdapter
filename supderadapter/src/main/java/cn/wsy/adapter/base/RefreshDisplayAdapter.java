package cn.wsy.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;

import cn.wsy.adapter.R;
import cn.wsy.adapter.interfaces.DisplayLoadDataMng;
import cn.wsy.adapter.interfaces.LoadMoreCallbak;


/**
 * Created by wsy on 2016/8/1.
 */
public abstract class RefreshDisplayAdapter<T> extends BaseReuseAdapter<T> implements DisplayLoadDataMng {

    private LoadMoreCallbak loadMoreCallbak;

    //value 刷新和加载不能同时存在
    private boolean STATE_LOAD_MORD_FINSH = true;//加载结束
    private boolean STATE_LOAD_MORDING = false;//正在加载
    private boolean STATE_REFRESH_FINSH = false;//刷新结束
    private boolean STATE_REFRESHING = false;//正在刷新

    public RefreshDisplayAdapter(Context mContext, List<T> data) {
        super(mContext, data);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(MyViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isRecyclerView())
            startLoadMore(holder, holder.getAdapterPosition());
    }

    @Override
    public void setLoadMoreListener(LoadMoreCallbak loadMoreCallbak) {
        this.loadMoreCallbak = loadMoreCallbak;
        addFooterView(LayoutInflater.from(getmActivity()).inflate(R.layout.footerview_layout, null));
    }

    @Override
    public void setRefreshListener() {

    }

    @Override
    public void startLoadMore(MyViewHolder holder, int realPostion) {

        if (loadMoreCallbak != null) {
//            Log.i("wusy", "layoutpostion : " + realPostion + " size cout is :" + getItemCount());
            if (realPostion == getItemCount() - 1 && STATE_LOAD_MORD_FINSH && !STATE_REFRESHING) {
//                addFooterView(LayoutInflater.from(mContext).inflate(R.layout.footerview_layout,null));
//                notifyDataSetChanged();
                setFooterViewResult(true, "正在加载中...");
                loadMoreCallbak.loadMore();
            }
        }
    }

    @Override
    public void stopLoadMore() {
        if (haveFooterView()) {
            setFooterViewResult(false, "已经全部加载完成");
            STATE_LOAD_MORD_FINSH = true;
        }
    }

    @Override
    public void startRefresh() {
        STATE_REFRESHING = true;
//        removeAll(getmData());
        setFooterViewResult(false, "正在加载中...");
    }

    @Override
    public void stopRefresh() {
        STATE_REFRESHING = false;
    }
}
