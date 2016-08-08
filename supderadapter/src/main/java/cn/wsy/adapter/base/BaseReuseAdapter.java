package cn.wsy.adapter.base;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import cn.wsy.adapter.R;
import cn.wsy.adapter.interfaces.AdapterBindIter;
import cn.wsy.adapter.interfaces.CRUD;
import cn.wsy.adapter.interfaces.ILayoutManager;
import cn.wsy.adapter.interfaces.OnItemClickListener;
import cn.wsy.adapter.interfaces.OnItemLongClickListener;


/**
 * 支持头部、尾部。基础adapter
 * Created by wsy on 2016/7/20.
 */
public abstract class BaseReuseAdapter<T> extends RecyclerView.Adapter<MyViewHolder> implements AdapterBindIter<MyViewHolder, T>
        , CRUD<T>, ILayoutManager {//,IFooterViewMng 暫時屏蔽

    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;

    //View
    private View VIEW_HEADER;
    private View VIEW_FOOTER;
    private RecyclerView mRecyclerView;
    private ListView mListview;

    private ProgressBar footer_bar;
    private TextView footer_tv;

    //click listener
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    //data
    private Context mContext;
    private List<T> mData;

    public BaseReuseAdapter(Context mContext, List<T> data) {
        this.mContext = mContext;
        this.mData = data;
    }

    public Context getmActivity() {
        return mContext;
    }

    @Override
    public int getItemCount() {
        int count = mData == null ? 0 : mData.size();
        if (haveHeaderView()) count++;
        if (haveFooterView()) count++;
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        }
        if (isFooterView(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final MyViewHolder holder;
        View convertView = getLayout();
        if (viewType == TYPE_HEADER) {
            holder = new MyViewHolder(getHeaderView());
            holder.setConvertView(getHeaderView());
        } else if (viewType == TYPE_FOOTER) {
            holder = new MyViewHolder(getFooterView());
            holder.setConvertView(getFooterView());
        } else {
            holder = onCreate(null, parent);
            if (!(holder.getConvertView() instanceof AdapterView)) {
                if (clickListener != null) {
                    holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int index = holder.getAdapterPosition();
                            if (haveHeaderView()) {
                                index--;
                            }
                            clickListener.onclick(v, index);
                        }
                    });
                }
                if (longClickListener != null) {

                    holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            int index = holder.getAdapterPosition();
                            if (haveHeaderView()) {
                                index--;
                            }
                            longClickListener.onLongClick(v, index);
                            return false;
                        }
                    });
                }
            }
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) {
                position--;
            }
            onBind(holder, position, mData.get(position));
        }
    }

    @Override
    public boolean hasLayoutManager() {
        return mRecyclerView != null && mRecyclerView.getLayoutManager() != null;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return hasLayoutManager() ? mRecyclerView.getLayoutManager() : null;
    }


    //----------------------------------------------------> for RecyclerView onclick
    public void setLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }


    //-------------------------------------------------> display listview

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    public ListView getmListview() {
        return mListview;
    }

    public void setmListview(ListView mListview) {
        this.mListview = mListview;
    }

    public boolean isRecyclerView(){
        return getmListview() ==null ?true:false;
    }

    //-------------------------------------------------> display header footer view
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = null;
    }

    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("hearview has already exists!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            VIEW_HEADER = headerView;
            ifGridLayoutManager();
            notifyItemInserted(0);
        }

    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;

            footer_bar = (ProgressBar) VIEW_FOOTER.findViewById(R.id.footer_pb);
            footer_tv = (TextView) VIEW_FOOTER.findViewById(R.id.footer_tv);
            if (isRecyclerView()) {//如果判断不是listview依赖，则为recycleview
                ifGridLayoutManager();
                notifyItemInserted(getItemCount() - 1);
            }else{
                getmListview().addFooterView(VIEW_FOOTER);
            }
        }

    }

    /**
     * 处理 recycleview 是gridlayoutmanager 头文件的时候
     */
    private void ifGridLayoutManager() {
        final RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                    ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() :
                            1;
                }
            });
        }
    }

    public View getHeaderView() {
        return VIEW_HEADER;
    }

    public View getFooterView() {
        return VIEW_FOOTER;
    }

    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }

    public void setFooterViewResult(boolean isShowLoad, String result){
        if (!isShowLoad) {
            footer_bar.setVisibility(View.GONE);
        }else{
            footer_bar.setVisibility(View.VISIBLE);
        }
        footer_tv.setText(result);
    }

    public View getLayout() {
        return LayoutInflater.from(mContext).inflate(injectLayoutID(), null);
    }

    //-------------------------------------------------->display data
    public List<T> getmData() {
        return mData;
    }

    @Override
    public void add(T item) {
        mData.add(item);
        int location = mData.size() - 1;
        if (haveHeaderView())
            location++;
        notifyItemInserted(location);
    }

    @Override
    public void add(int location, T item) {
        mData.add(location, item);
        if (haveHeaderView())
            location++;
        notifyItemInserted(location);
    }

    @Override
    @Deprecated
    public final void insert(int location, T item) {
        add(location, item);
    }

    @Override
    public void addAll(List<T> items) {
        if (items == null || items.isEmpty()) {
            return;
        }
        int location = getItemCount();
        mData.addAll(items);
        if (haveHeaderView())
            location++;
        notifyItemRangeInserted(location, items.size());
    }

    @Override
    public void addAll(int location, List<T> items) {
        if (items == null || items.isEmpty()) {
            return;
        }
        if (location < 0 || location > getItemCount()) {
            return;
        }
        mData.addAll(location, items);
        if (haveHeaderView())
            location++;
        notifyItemRangeInserted(location, items.size());
    }

    @Override
    public void remove(T item) {
        if (contains(item)) {
            remove(mData.indexOf(item));
        }
    }

    @Override
    public void remove(int location) {
        if (location >= mData.size()) return;
        mData.remove(location);
        if (haveHeaderView())
            location++;
        notifyItemRemoved(location);
    }

    @Override
    public void removeAll(List<T> items) {
        mData.removeAll(items);
        notifyDataSetChanged(); // RecyclerView
    }

    @Override
    public void retainAll(List<T> items) {
        mData.retainAll(items);
        notifyDataSetChanged(); // RecyclerView
    }

    @Override
    public void set(T oldItem, T newItem) {
        set(mData.indexOf(oldItem), newItem);
    }

    @Override
    public void set(int location, T item) {
        mData.set(location, item);
        if (haveHeaderView())
            location++;
        notifyItemChanged(location);
    }

    @Override
    public void replaceAll(List<T> items) {
        if (items == null || items.isEmpty()) {
            return;
        }
        if (mData.isEmpty()) {
            addAll(items);
        } else {
            int start = haveHeaderView() ? 1 : 0;
            int originalSize = getItemCount();
            int newSize = items.size();
            mData.clear();
            mData.addAll(items);
            if (originalSize > newSize) {
                notifyItemRangeChanged(start, newSize);
                notifyItemRangeRemoved(start + newSize, originalSize - newSize);
            } else if (originalSize == newSize) {
                notifyItemRangeChanged(start, newSize);
            } else {
                notifyItemRangeChanged(start, originalSize);
                notifyItemRangeInserted(start + originalSize, newSize - originalSize);
            }
        }
    }

    @Override
    public boolean contains(T item) {
        return mData.contains(item);
    }

    @Override
    public boolean containsAll(List<T> items) {
        return mData.containsAll(items);
    }

    @Override
    public void clear() {
        if (!mData.isEmpty()) {
            mData.clear();
            notifyDataSetChanged();  // RecyclerView
        }
    }

}
