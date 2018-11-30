package com.kongzue.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.kongzue.baseframework.BaseActivity;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.titlebar.TitleBar;

import java.util.HashMap;
import java.util.Map;

@Layout(R.layout.activity_aim)
@NavigationBarBackgroundColor(a = 0)
public class AimActivity extends BaseActivity {
    
    private TitleBar titlebar;
    private TextView txtText;
    
    @Override
    public void initViews() {
        titlebar = findViewById(R.id.titlebar);
        txtText = findViewById(R.id.txt_text);
    }
    
    @Override
    public void initDatas(JumpParameter paramer) {
        txtText.setText(paramer.getString("text"));
        
        setResponse(new JumpParameter()
            .put("result","这是返回的数据")
        );
        
        
    }
    
    @Override
    public void setEvents() {
    
    }
}
