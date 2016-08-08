package cn.wsy.demo.widgets;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

import cn.wsy.adapter.base.MyViewHolder;
import cn.wsy.adapter.base.SuperAdapter;
import cn.wsy.demo.R;
import cn.wsy.demo.ResponseNew;
import cn.wsy.generallib.photolib.utils.PhotoUtils;

/**
 * Created by wsy on 2016/7/20.
 */
public class TestAdapter extends SuperAdapter<ResponseNew.NewInfo> {

    public TestAdapter(Context mContext, List<ResponseNew.NewInfo> data) {
        super(mContext, data);
    }

    @Override
    public int injectLayoutID() {
        return R.layout.item_list_new;
    }

    @Override
    public void onBind(MyViewHolder holder, int position, ResponseNew.NewInfo item) {
        ResponseNew.NewInfo info = getItem(position);
        holder.setText(R.id.tv_new_content, info.getDescription());
        holder.setText(R.id.tv_new_title, info.getTitle());
        holder.setText(R.id.tv_new_time, info.getCtime());

        ImageView piv = holder.getView(R.id.iv_new_img);
        PhotoUtils.showImage(piv, info.getPicUrl());
    }


}
