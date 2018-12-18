package com.kongzue.demo.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kongzue.baseframework.BaseActivity;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.interfaces.SwipeBack;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.demo.R;
import com.kongzue.titlebar.TitleBar;

@Layout(R.layout.activity_news_details)
@NavigationBarBackgroundColor(a = 0)
@DarkNavigationBarTheme(true)
@SwipeBack(true)
public class NewsDetailsActivity extends BaseActivity {
    
    private TitleBar titlebar;
    private WebView webView;
    
    @Override
    public void initViews() {
        titlebar = findViewById(R.id.titlebar);
        webView = findViewById(R.id.webView);
    }
    
    @Override
    public void initDatas(JumpParameter paramer) {
        initWebView();
        String url = paramer.getString("url");
        if (!isNull(url)) {
            webView.loadUrl(url);
        }
    }
    
    @Override
    public void setEvents() {
    
    }
    
    private void initWebView() {
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();
        
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        
        //设置自适应屏幕，两者合用
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setUseWideViewPort(true);
        
        //缩放操作
        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.startsWith("https") || url.startsWith("http")){
                    view.loadUrl(url);
                }else{
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });
    }
}
