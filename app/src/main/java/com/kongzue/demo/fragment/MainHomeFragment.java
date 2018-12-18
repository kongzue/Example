package com.kongzue.demo.fragment;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kongzue.baseframework.BaseAdapter;
import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.demo.R;
import com.kongzue.demo.activity.NewsDetailsActivity;
import com.kongzue.demo.util.views.RefreshView;
import com.kongzue.demo.works.HomeWorks;
import com.kongzue.dialog.v2.Notification;
import com.kongzue.titlebar.TitleBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kongzue.dialog.v2.DialogSettings.TYPE_KONGZUE;

/**
 * Author: @Kongzue
 * Github: https://github.com/kongzue/
 * Homepage: http://kongzue.com/
 * Mail: myzcxhh@live.cn
 * CreateTime: 2018/12/16 22:19
 */
@Layout(R.layout.fragment_main_home)
public class MainHomeFragment extends BaseFragment {
    
    private BaseAdapter baseAdapter;                //listView适配器
    private List<Map<String, Object>> datas;        //用于存储加载的数据
    
    private TitleBar titlebar;
    private RefreshView refreshLayout;
    private ImageView imgRefresher;
    private TextView txtRefresher;
    private ListView list;
    private ImageView imgLoadMore;
    private TextView txtLoadMore;
    
    @Override
    public void initViews() {
        titlebar = findViewById(R.id.titlebar);
        refreshLayout = findViewById(R.id.refreshLayout);
        imgRefresher = findViewById(R.id.img_refresher);
        txtRefresher = findViewById(R.id.txt_refresher);
        list = findViewById(R.id.list);
        imgLoadMore = findViewById(R.id.img_loadMore);
        txtLoadMore = findViewById(R.id.txt_loadMore);
    }
    
    @Override
    public void initDatas() {
        refreshLayout.initRefreshSettings(imgRefresher, imgLoadMore);
        
        if (baseAdapter != null) {
            baseAdapter = HomeWorks.init(me, datas);
            list.setAdapter(baseAdapter);
        }
        initDemoDatas();
    }
    
    @Override
    public void setEvents() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout r) {
                datas = null;
                baseAdapter = null;
                initDemoDatas();
            }
        });
        
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                try {
                    //获取上一天的日期
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    java.util.Date date = sdf.parse(now_date);
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
                    load_date = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
                    //继续加载数据
                    initDemoDatas();
                } catch (Exception e) {
                }
            }
        });
        
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (((int)datas.get(position).get("type")) == 1) {
                    jump(NewsDetailsActivity.class,new JumpParameter().put("url","http://daily.zhihu.com/story/"+datas.get(position).get("id")));
                }
            }
        });
    }
    
    private String load_date;
    private String now_date;
    
    //加载演示数据
    private void initDemoDatas() {
        //准备请求地址
        String url;
        if (isNull(load_date)) {         //常用网络文本判空
            url = "https://news-at.zhihu.com/api/4/news/latest";
        } else {
            url = "https://news-at.zhihu.com/api/4/news/before/" + load_date;
        }
        //加载Demo数据
        HttpRequest.build(me, url)
                .setResponseListener(new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        refreshLayout.finishAll();
                        if (error == null) {
                            try {
                                JSONObject main = new JSONObject(response);
                                now_date = main.getString("date");
                                JSONArray storiesList = main.getJSONArray("stories");
                                if (storiesList != null && storiesList.length() != 0) {
                                    
                                    List<String> images = new ArrayList<>();
                                    images.add("res://com.kongzue.demo/" + R.mipmap.test_banner1);
                                    images.add("res://com.kongzue.demo/" + R.mipmap.test_banner2);
                                    images.add("res://com.kongzue.demo/" + R.mipmap.test_banner3);
                                    images.add("res://com.kongzue.demo/" + R.mipmap.test_banner1);
                                    images.add("res://com.kongzue.demo/" + R.mipmap.test_banner2);
                                    images.add("res://com.kongzue.demo/" + R.mipmap.test_banner3);
                                    
                                    if (datas == null) {
                                        datas = new ArrayList<>();
                                        //第一个布局——轮播图
                                        Map<String, Object> map;
                                        map = new HashMap<>();
                                        map.put("type", 0);
                                        map.put("images", images);
                                        datas.add(map);
                                    }
                                    
                                    for (int i = 0; i < storiesList.length(); i++) {
                                        JSONObject itemOfStoriesList = storiesList.getJSONObject(i);
                                        
                                        int id = itemOfStoriesList.getInt("id");
                                        String image = (String) itemOfStoriesList.getJSONArray("images").get(0);
                                        String title = itemOfStoriesList.getString("title");
                                        
                                        Map<String, Object> map = new HashMap<>();
                                        map.put("type", 1);
                                        map.put("id", id);
                                        map.put("title", title);
                                        map.put("tip", "来自 @知乎日报 " + now_date);
                                        map.put("image", image);
                                        datas.add(map);
                                    }
                                    
                                    if (baseAdapter == null) {
                                        baseAdapter = HomeWorks.init(me, datas);
                                        list.setAdapter(baseAdapter);
                                    } else {
                                        baseAdapter.notifyDataSetChanged();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Notification.show(me, 0, "加载错误，请稍候重试", null, Notification.SHOW_TIME_SHORT, Notification.TYPE_ERROR).setType(TYPE_KONGZUE);
                            }
                        } else {
                            error.printStackTrace();
                            Notification.show(me, 0, "网络异常，请稍候重试", null, Notification.SHOW_TIME_SHORT, Notification.TYPE_ERROR).setType(TYPE_KONGZUE);
                        }
                    }
                })
                .doGet();
        ;
    }
}
