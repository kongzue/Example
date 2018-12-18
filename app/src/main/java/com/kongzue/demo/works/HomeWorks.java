package com.kongzue.demo.works;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kongzue.baseframework.BaseActivity;
import com.kongzue.baseframework.BaseAdapter;
import com.kongzue.baseframework.interfaces.MultipleMapAdapterSettings;
import com.kongzue.demo.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: @Kongzue
 * Github: https://github.com/kongzue/
 * Homepage: http://kongzue.com/
 * Mail: myzcxhh@live.cn
 * CreateTime: 2018/12/16 22:57
 */
public class HomeWorks {
    
    public static BaseAdapter init(final BaseActivity me, List<Map<String, Object>> datas) {
        Map<Integer, Integer> layouts = new HashMap<>();
        layouts.put(0, R.layout.item_banner);
        layouts.put(1, R.layout.item_news);
        
        BaseAdapter baseAdapter = new BaseAdapter(me, datas, layouts, new MultipleMapAdapterSettings() {
            @Override
            public Object setViewHolder(int type, View convertView) {
                switch (type) {
                    case 0:
                        BannerViewHolder bannerViewHolder = new BannerViewHolder();
                        bannerViewHolder.iBanner = convertView.findViewById(R.id.iBanner);
                        
                        bannerViewHolder.iBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);      //显示圆形指示器
                        bannerViewHolder.iBanner.setIndicatorGravity(BannerConfig.CENTER);           //指示器居中
                        bannerViewHolder.iBanner.isAutoPlay(true);                                   //设置是否自动轮播
                        bannerViewHolder.iBanner.setViewPagerIsScroll(true);                         //设置是否允许手动滑动轮播图
                        bannerViewHolder.iBanner.setDelayTime(3000);                                 //轮播时间
                        bannerViewHolder.iBanner.setBannerAnimation(Transformer.Default);            //动画
                        bannerViewHolder.iBanner.setImageLoader(new ImageLoader() {                  //图片加载器
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                Uri uri = Uri.parse((String) path);
                                imageView.setImageURI(uri);
                            }
        
                            @Override
                            public ImageView createImageView(Context context) {
                                SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
                                return simpleDraweeView;
                            }
                        });
                        bannerViewHolder.iBanner.setOnBannerListener(new OnBannerListener() {        //点击轮播图
                            @Override
                            public void OnBannerClick(int position) {
            
                            }
                        });
                        
                        return bannerViewHolder;
                    case 1:
                        NewsViewHolder newsViewHolder = new NewsViewHolder();
                        newsViewHolder.image = convertView.findViewById(R.id.image);
                        newsViewHolder.txtTitle = convertView.findViewById(R.id.txt_title);
                        newsViewHolder.txtInfo = convertView.findViewById(R.id.txt_info);
                        return newsViewHolder;
                }
                return null;
            }
            
            @Override
            public void setData(int type, Object viewHolder, Map<String, Object> data) {
                switch (type) {
                    case 0:
                        BannerViewHolder bannerViewHolder = (BannerViewHolder) viewHolder;
                        List<String> images = (List<String>) data.get("images");
                        bannerViewHolder.iBanner.setImages(images);                                  //图片数据源
                        bannerViewHolder.iBanner.start();                                            //开始执行
                        break;
                    case 1:
                        NewsViewHolder newsViewHolder = (NewsViewHolder) viewHolder;
                        newsViewHolder.txtTitle.setText(data.get("title") + "");
                        newsViewHolder.txtInfo.setText(data.get("tip") + "");
                        newsViewHolder.image.setImageURI(data.get("image") + "");
                        break;
                }
            }
        });
        
        return baseAdapter;
    }
    
    static class BannerViewHolder {
        Banner iBanner;
    }
    
    static class NewsViewHolder {
        SimpleDraweeView image;
        TextView txtTitle;
        TextView txtInfo;
    }
}
