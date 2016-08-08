package cn.wsy.demo.widgets;

import android.content.Context;

import java.util.List;

import cn.wsy.adapter.base.MyViewHolder;
import cn.wsy.adapter.base.SuperAdapter;
import cn.wsy.demo.R;


/**
 * Created by wsy on 2016/8/3.
 */
public class LvAdapter extends SuperAdapter<String> {

    public LvAdapter(Context mContext, List<String> data) {
        super(mContext, data);
    }

    @Override
    public int injectLayoutID() {
        return R.layout.recycler_item_layout;
    }

    @Override
    public void onBind(MyViewHolder holder, int position, String item) {

    }
}
