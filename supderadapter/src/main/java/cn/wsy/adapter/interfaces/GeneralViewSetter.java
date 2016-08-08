package cn.wsy.adapter.interfaces;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.FloatRange;
import android.support.v7.widget.RecyclerView;
import android.text.method.MovementMethod;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;

/**
 * 集成普遍的view set方法
 * Created by wsy on 2016/7/20.
 */
public interface GeneralViewSetter<SY> {

    SY setText(int viewId, CharSequence text);

    SY setTextColor(int viewId, int textColor);

    SY setTextColor(int viewId, ColorStateList colorStateList);

    SY setMovementMethod(int viewId, MovementMethod method);

    SY setImageResource(int viewId, int imgResId);

    SY setImageDrawable(int viewId, Drawable drawable);

    SY setImageBitmap(int viewId, Bitmap bitmap);

    SY setImageUri(int viewId, Uri imageUri);

    SY setScaleType(int viewId, ImageView.ScaleType type);

    SY setBackgroundColor(int viewId, int bgColor);

    SY setBackgroundResource(int viewId, int bgRes);

    SY setColorFilter(int viewId, ColorFilter colorFilter);

    SY setColorFilter(int viewId, int colorFilter);

    SY setAlpha(int viewId, @FloatRange(from = 0.0, to = 1.0) float value);

    SY setVisibility(int viewId, int visibility);

    SY setMax(int viewId, int max);

    SY setProgress(int viewId, int progress);

    SY setRating(int viewId, float rating);

    SY setTag(int viewId, Object tag);

    SY setTag(int viewId, int key, Object tag);

    SY setEnabled(int viewId, boolean enabled);

    SY setAdapter(int viewId, Adapter adapter);

    SY setAdapter(int viewId, RecyclerView.Adapter adapter);

    SY setChecked(int viewId, boolean checked);

    SY setOnClickListener(int viewId, View.OnClickListener listener);

    SY setOnLongClickListener(int viewId, View.OnLongClickListener listener);

    SY setOnTouchListener(int viewId, View.OnTouchListener listener);
}
