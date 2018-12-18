package com.kongzue.demo.util.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.kongzue.demo.R;
import com.kongzue.demo.util.interfaces.OnRefreshCompleted;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * Author: @Kongzue
 * Github: https://github.com/kongzue/
 * Homepage: http://kongzue.com/
 * Mail: myzcxhh@live.cn
 * CreateTime: 2018/11/23 18:22
 */
public class RefreshView extends SmartRefreshLayout {
    
    private OnRefreshCompleted onRefreshCompleted;
    private OnRefreshListener onRefreshListener;
    private OnLoadMoreListener onLoadMoreListener;
    private ImageView imgRefresher;
    private ImageView imgLoadMore;
    
    public RefreshView(Context context) {
        super(context);
    }
    
    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    @Override
    public SmartRefreshLayout finishRefresh() {
        if (imgRefresher != null) {
            if (headerAnimator != null) {
                headerAnimator.cancel();
                imgRefresher.setRotation(0);
                imgRefresher.setImageResource(R.mipmap.img_refresher);
            }
        }
        if (onRefreshCompleted != null) onRefreshCompleted.onRefreshCompleted();
        return super.finishRefresh();
    }
    
    @Override
    public SmartRefreshLayout finishLoadMore() {
        if (imgLoadMore != null) {
            if (footerAnimator != null) {
                footerAnimator.cancel();
                imgLoadMore.setRotation(0);
                imgLoadMore.setImageResource(R.mipmap.img_loadmore);
            }
        }
        if (onRefreshCompleted != null) onRefreshCompleted.onLoadMoreCompleted();
        return super.finishLoadMore();
    }
    
    public void setOnRefreshCompleted(OnRefreshCompleted onRefreshCompleted) {
        this.onRefreshCompleted = onRefreshCompleted;
    }
    
    private ObjectAnimator headerAnimator;
    private ObjectAnimator footerAnimator;
    
    public void initRefreshSettings(ImageView r, ImageView l) {
        if (isInEditMode()) return;
        imgRefresher = r;
        imgLoadMore = l;
        
        setEnableOverScrollDrag(true);
        setEnableAutoLoadMore(false);
        
        if (imgRefresher != null) {
            super.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    if (headerAnimator!=null)headerAnimator.cancel();
                    headerAnimator = ObjectAnimator.ofFloat(imgRefresher, "rotation", 0f, 360f);//添加旋转动画，旋转中心默认为控件中点
                    headerAnimator.setDuration(500);//设置动画时间
                    headerAnimator.setInterpolator(new LinearInterpolator());//动画时间线性渐变
                    headerAnimator.setRepeatCount(ObjectAnimator.INFINITE);
                    headerAnimator.setRepeatMode(ObjectAnimator.RESTART);
                    headerAnimator.start();
                    imgRefresher.setImageResource(R.mipmap.img_refreshing);
                    if (onRefreshListener!=null)onRefreshListener.onRefresh(RefreshView.this);
                }
            });
        } else {
            Log.e(">>>", "imgRefresher==null");
        }
        if (imgLoadMore != null) {
            super.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshlayout) {
                    if (footerAnimator!=null)footerAnimator.cancel();
                    footerAnimator = ObjectAnimator.ofFloat(imgLoadMore, "rotation", 0f, 360f);//添加旋转动画，旋转中心默认为控件中点
                    footerAnimator.setDuration(500);//设置动画时间
                    footerAnimator.setInterpolator(new LinearInterpolator());//动画时间线性渐变
                    footerAnimator.setRepeatCount(ObjectAnimator.INFINITE);
                    footerAnimator.setRepeatMode(ObjectAnimator.RESTART);
                    footerAnimator.start();
                    imgLoadMore.setImageResource(R.mipmap.img_refreshing);
                    if (onLoadMoreListener!=null)onLoadMoreListener.onLoadMore(RefreshView.this);
                }
            });
        } else {
            Log.e(">>>", "imgLoadMore==null");
        }
    }
    
    public void initRefreshSettings(final ImageView r) {
        if (isInEditMode()) return;
    
        imgRefresher = r;
        imgLoadMore = null;
        
        setEnableOverScrollDrag(true);
        setEnableLoadMore(false);
        
        if (imgRefresher != null) {
            super.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    if (headerAnimator!=null)headerAnimator.cancel();
                    headerAnimator = ObjectAnimator.ofFloat(imgRefresher, "rotation", 0f, 360f);//添加旋转动画，旋转中心默认为控件中点
                    headerAnimator.setDuration(500);//设置动画时间
                    headerAnimator.setInterpolator(new LinearInterpolator());//动画时间线性渐变
                    headerAnimator.setRepeatCount(ObjectAnimator.INFINITE);
                    headerAnimator.setRepeatMode(ObjectAnimator.RESTART);
                    headerAnimator.start();
                    imgRefresher.setImageResource(R.mipmap.img_refreshing);
                    if (onRefreshListener!=null)onRefreshListener.onRefresh(RefreshView.this);
                }
            });
        } else {
            Log.e(">>>", "imgRefresher==null");
        }
    }
    
    public void initRefreshSettings() {
        if (isInEditMode()) return;
        setEnableOverScrollDrag(true);
        setEnableLoadMore(false);
        setEnableRefresh(false);
    }
    
    @Override
    public SmartRefreshLayout setOnRefreshListener(OnRefreshListener listener) {
        onRefreshListener = listener;
        return this;
    }
    
    @Override
    public SmartRefreshLayout setOnLoadMoreListener(OnLoadMoreListener listener) {
        onLoadMoreListener = listener;
        return this;
    }
    
    public void finishAll() {
        finishRefresh();
        finishLoadMore();
    }
}
