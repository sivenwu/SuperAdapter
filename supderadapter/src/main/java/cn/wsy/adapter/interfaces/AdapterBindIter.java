package cn.wsy.adapter.interfaces;

import android.view.View;
import android.view.ViewGroup;

/**
 * 适配器事件绑定
 * Created by wsy on 2016/7/20.
 */
public interface AdapterBindIter<SY,T> {

    public abstract int injectLayoutID();

    public abstract void onBind(SY holder, int position, T item);

    public SY onCreate(View convertView, ViewGroup parent);

}
