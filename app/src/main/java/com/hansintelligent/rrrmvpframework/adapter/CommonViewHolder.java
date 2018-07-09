package com.hansintelligent.rrrmvpframework.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.SpannableString;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hansintelligent.rrrmvpframework.glide.ImageLoadUtil;


/**
 * CommonViewHolder
 * Created by wangfu on 2016/12/1.
 */
public class CommonViewHolder {

    private SparseArray<View> mViews;
    private int mPosition;
    private View mConverView;
    private Context mContext;


    public CommonViewHolder(Context context, ViewGroup parent, int layoutId, int position) {

        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mContext = context;

        mConverView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConverView.setTag(this);
    }

    /**
     * viewholder的入口方法
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static CommonViewHolder getViewHolder(Context context, View convertView,
                                                 ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new CommonViewHolder(context, parent, layoutId, position);
        } else {
            CommonViewHolder holder = ((CommonViewHolder) convertView.getTag());
            holder.mPosition = position;
            return holder;
        }
    }

    public View getConverView() {
        return mConverView;
    }


    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConverView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return ((T) view);
    }


    /**
     * 给textview设置值
     *
     * @param viewId
     * @param text
     * @return
     */
    public CommonViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;//链式编程
    }

    /**
     * 给textview设置spannableString值
     *
     * @param viewId
     * @param sp
     * @return
     */
    public CommonViewHolder setText(int viewId, SpannableString sp) {
        TextView tv = getView(viewId);
        tv.setText(sp);
        return this;
    }


    /**
     * 设置字体颜色
     *
     * @param viewId
     * @param resId
     * @return
     */
    public CommonViewHolder setTextColor(int viewId, int resId) {

        TextView tv = getView(viewId);
        tv.setTextColor(resId);
        return this;//链式编程

    }


    /**
     * 设置空间
     *
     * @param viewId
     * @param visible
     * @return
     */
    public CommonViewHolder setVisibility(int viewId, int visible) {
        View v = getView(viewId);
        switch (visible) {
            case View.VISIBLE:
            case View.INVISIBLE:
            case View.GONE:
                v.setVisibility(visible);
                break;
            default:
                break;
        }
        return this;
    }


    /**
     * 给imageview设置bitmap
     *
     * @param viewId
     * @param bitmap
     * @return
     */
    public CommonViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }


    /**
     * 设置imageview图片
     *
     * @param viewId
     * @param res
     * @return
     */
    public CommonViewHolder setImageResource(int viewId, int res) {
        ImageView iv = getView(viewId);
        int r = 0;// TODO: 2018/5/21 等待添加默认图片
        ImageLoadUtil.loadImage(mContext, iv, res, r);
        return this;
    }

    /**
     * 设置imageview图片
     *
     * @return
     */
    public CommonViewHolder setImageResource(int viewId, String res) {
        ImageView iv = getView(viewId);
        int r = 0;// TODO: 2018/5/21 等待添加默认图片
        ImageLoadUtil.loadImage(mContext, iv, res, r);
        return this;
    }


    /**
     * 设置圆角imageview图片
     *
     * @param viewId
     * @param res
     * @return
     */
    public CommonViewHolder setImageRoundResource(int viewId, int res) {
        ImageView iv = getView(viewId);
        int r = 0;// TODO: 2018/5/21 等待添加默认图片
        ImageLoadUtil.loadRoundImage(mContext, iv, res, r);
        return this;
    }

    /**
     * 设置圆角imageview图片
     *
     * @return
     */
    public CommonViewHolder setImageRoundResource(int viewId, String res) {
        ImageView iv = getView(viewId);
        int r = 0;// TODO: 2018/5/21 等待添加默认图片
        ImageLoadUtil.loadRoundImage(mContext, iv, res, r);
        return this;
    }


    /**
     * 设置背景颜色
     *
     * @param viewId
     * @param color
     * @return
     */
    public CommonViewHolder setBackground(int viewId, int color) {
        View tv = getView(viewId);
        tv.setBackgroundColor(color);
        return this;
    }

}
