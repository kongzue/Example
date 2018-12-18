package com.kongzue.demo.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.kongzue.baseframework.BaseActivity;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.OnJumpResponseListener;
import com.kongzue.demo.R;
import com.kongzue.demo.fragment.MainHomeFragment;
import com.kongzue.demo.fragment.MainMeFragment;
import com.kongzue.demo.fragment.MainMessageFragment;
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
    //页面标记常量
    public static final int TAB_HOME = 0;
    public static final int TAB_MESSAGE = 1;
    public static final int TAB_ME = 2;
    
    //准备好的Fragment
    private MainHomeFragment mainHomeFragment=new MainHomeFragment();
    private MainMessageFragment mainMessageFragment = new MainMessageFragment();
    private MainMeFragment mainMeFragment = new MainMeFragment();
    
    //切换页面需要
    private int nowPageIndex;
    private FragmentManager fragmentManager;
    
    //所有界面组件
    private FrameLayout content;
    private TabBarView tabbar;
    
    //此处绑定所有组件
    @Override
    public void initViews() {
        //绑定布局
        content = findViewById(R.id.content);
        tabbar = findViewById(R.id.tabbar);
    }
    
    //此处初始化需要准备的数据
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
        tabbar.setTab(tabs).setNormalFocusIndex(TAB_HOME);
        //默认切换到第一个界面
        changeFragment(TAB_HOME);
    }
    
    //此处绑定所有事件
    @Override
    public void setEvents() {
        //绑定事件
        tabbar.setOnTabChangeListener(new OnTabChangeListener() {
            @Override
            public void onTabChanged(View v, int index) {
                //点击了某个Tab时响应
                changeFragment(index);
            }
        });
    }
    
    public void changeFragment(int index) {
        nowPageIndex = index;
        switch (index) {
            case TAB_HOME:
                showFragment(mainHomeFragment);
                break;
            case TAB_MESSAGE:
                showFragment(mainMessageFragment);
                break;
            case TAB_ME:
                showFragment(mainMeFragment);
                break;
        }
    }
    
    private void showFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }
}
