package cn.wsy.adapter.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wsy on 2016/7/21.
 */
public abstract class SuperAdapter<T> extends ListSupportAdapter<T> {

    public SuperAdapter(Context mContext, List data) {
        super(mContext, data);
    }

    @Override
    public MyViewHolder onCreate(View convertView, ViewGroup parent) {
        return MyViewHolder.get(convertView, convertView == null ? convertView = getLayout() : null);
    }

}
