package com.kongzue.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kongzue.baseframework.BaseActivity;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.tabbar.Tab;
import com.kongzue.tabbar.TabBarView;
import com.kongzue.tabbar.interfaces.OnTabChangeListener;
import com.kongzue.titlebar.TitleBar;

import java.util.ArrayList;
import java.util.List;

@Layout(R.layout.activity_main)
@NavigationBarBackgroundColor(a = 0)
@DarkNavigationBarTheme(true)
public class MainActivity extends BaseActivity {
    
    private TitleBar titlebar;
    private TabBarView tabbar;
    
    @Override
    public void initViews() {
        //绑定布局
        titlebar = findViewById(R.id.titlebar);
        tabbar = findViewById(R.id.tabbar);
    }
    
    @Override
    public void initDatas(JumpParameter paramer) {
        //初始化方法
        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab(this, "首页", R.mipmap.tab2_home_off)
                         .setFocusIcon(this, R.mipmap.tab2_home_on)
        );
        tabs.add(new Tab(this, "消息", R.mipmap.tab2_message_off)
                         .setFocusIcon(this, R.mipmap.tab2_message_on)
        );
        tabs.add(new Tab(this, "我的", R.mipmap.tab2_me_off)
                         .setFocusIcon(this, R.mipmap.tab2_me_on)
        );
        tabbar.setTab(tabs).setNormalFocusIndex(1);
    }
    
    @Override
    public void setEvents() {
        //绑定事件
        tabbar.setOnTabChangeListener(new OnTabChangeListener() {
            @Override
            public void onTabChanged(View v, int index) {
                //点击了某个Tab时响应
                
            }
        });
    }
}
