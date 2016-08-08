package cn.wsy.adapter.base;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import cn.wsy.adapter.R;
import cn.wsy.adapter.interfaces.LoadMoreCallbak;


/**
 * Created by wsy on 2016/8/1.
 */
public class SuperRefreshLayout extends SwipeRefreshLayout {

    private OnRefreshListener refreshLayoutLister;
    private View multyListView;
    private SuperAdapter adapter;

    public SuperRefreshLayout(Context context) {
        this(context, null);
    }

    public SuperRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setColorSchemeResources(
                R.color.colorAccent, R.color.colorPrimary,
                R.color.colorPrimaryDark);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (multyListView == null) {
            getMultyListView();
        }
    }

    private void getMultyListView() {
        int childs = getChildCount();
        for (int i = 0; i < childs; i++) {
            View childView = getChildAt(i);
            if (childView instanceof ListView) {
                multyListView = (ListView) childView;
            }
            if (childView instanceof RecyclerView) {
                multyListView = (RecyclerView) childView;
            }
        }
        setOnRefreshListener(this.refreshLayoutLister);
    }

    public void setOnRefreshListener(final OnRefreshListener refreshLayoutLister) {
        this.refreshLayoutLister = refreshLayoutLister;

        if (this.refreshLayoutLister == null){
            setEnabled(false);
            return ;
        }

        if (multyListView != null) {
            if (multyListView instanceof ListView) {
                adapter = (SuperAdapter) ((ListView) multyListView).getAdapter();
               ((ListView) multyListView).setFooterDividersEnabled(false);
                adapter.setmListview((ListView) multyListView);//注入Listview
            }
            if (multyListView instanceof RecyclerView) {
                adapter = (SuperAdapter) ((RecyclerView) multyListView).getAdapter();
            }
            if (adapter != null) {
                adapter.setLoadMoreListener(new LoadMoreCallbak() {
                    @Override
                    public void loadMore() {
                        refreshLayoutLister.onLoadMore();
                    }
                });

                final SuperAdapter finalAdapter = adapter;
                this.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        finalAdapter.startRefresh();
                        refreshLayoutLister.onRefresh();
                    }
                });
            }

        }
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        super.setRefreshing(refreshing);
        if (!refreshing) {
            if (adapter != null)
                adapter.stopRefresh();
        }
    }

    /**
     * 上拉加载和下拉刷新监听接口
     */
    public interface OnRefreshListener {
        public void onRefresh();

        public void onLoadMore();
    }
}
