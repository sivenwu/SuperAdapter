package cn.wsy.adapter.base;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.List;

/**
 * for listview
 * Created by wsy on 2016/7/20.
 */
public abstract class ListSupportAdapter<T> extends RefreshDisplayAdapter<T> implements ListAdapter {

    private boolean isSet = true;
    private DataSetObservable mDataSetObservable = new DataSetObservable();

    ListSupportAdapter(Context mContext, List<T> data) {
        super(mContext, data);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }

    public void notifyDataSetHasChanged() {
            mDataSetObservable.notifyChanged();
    }

    public void notifyDataSetInvalidated() {
            mDataSetObservable.notifyInvalidated();
    }


    @Override
    public int getCount() {
        return getmData() == null?0:getmData().size();
    }

    @Override
    public T getItem(int position) {
        if (getmData() == null || position >= getmData().size()){
            return null;
        }
        return getmData().get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if (isSet) {
//            if (parent instanceof  ListView)
//            setmListview((ListView) parent);//注入Listview
//            isSet = false;
//        }
        MyViewHolder holder = onCreate(convertView,parent);
        startLoadMore(holder,position+1);//注入加载更多
        onBind(holder,position,getItem(position));
        return holder.getConvertView();
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return getCount()==0?true:false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }


    //-->display  listview是通过观察者去监听datasource变化，这个与recyclerview有点区别，需要重写
    @Override
    public void add(int location, T item) {
        super.add(location, item);
        notifyDataSetHasChanged();
    }

    @Override
    public void addAll(int location, List<T> items) {
        super.addAll(location, items);
        notifyDataSetHasChanged();
    }

    @Override
    public void addAll(List<T> items) {
        super.addAll(items);
        notifyDataSetHasChanged();
    }

    @Override
    public void removeAll(List<T> items) {
        super.removeAll(items);
        notifyDataSetHasChanged();
    }

    @Override
    public void add(T item) {
        super.add(item);
        notifyDataSetHasChanged();
    }

    @Override
    public void remove(T item) {
        super.remove(item);
        notifyDataSetHasChanged();
    }

    @Override
    public void remove(int location) {
        super.remove(location);
        notifyDataSetHasChanged();
    }

    @Override
    public void clear() {
        super.clear();
        notifyDataSetHasChanged();
    }

    @Override
    public void set(int location, T item) {
        super.set(location, item);
        notifyDataSetHasChanged();
    }

    @Override
    public void replaceAll(List<T> items) {
        super.replaceAll(items);
        notifyDataSetHasChanged();
    }

    @Override
    public void retainAll(List<T> items) {
        super.retainAll(items);
        notifyDataSetHasChanged();
    }

    @Override
    public void set(T oldItem, T newItem) {
        super.set(oldItem, newItem);
        notifyDataSetHasChanged();
    }

}
