package com.kongzue.demo.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kongzue.baseframework.BaseActivity;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.interfaces.SwipeBack;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.demo.R;
import com.kongzue.dialog.listener.DialogLifeCycleListener;
import com.kongzue.dialog.v2.TipDialog;
import com.kongzue.dialog.v2.WaitDialog;
import com.kongzue.titlebar.TitleBar;

@Layout(R.layout.activity_login)
@NavigationBarBackgroundColor(a = 0)
@DarkNavigationBarTheme(true)
@SwipeBack(true)
public class LoginActivity extends BaseActivity {
    
    private TitleBar titlebar;
    private EditText editUserName;
    private Button btnLogin;
    
    @Override
    public void initViews() {
        titlebar = findViewById(R.id.titlebar);
        editUserName = findViewById(R.id.edit_userName);
        btnLogin = findViewById(R.id.btn_login);
    }
    
    @Override
    public void initDatas(JumpParameter paramer) {
    
    }
    
    @Override
    public void setEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WaitDialog.show(me, "请稍候...");
                doDemoLogin();
            }
        });
    }
    
    private void doDemoLogin() {
        runOnMainDelayed(new Runnable() {
            @Override
            public void run() {
                WaitDialog.dismiss();
                String userName = editUserName.getText().toString().trim();
                if (!isNull(userName)) {
                    setResponse(new JumpParameter().put("userName", userName));
                    TipDialog.show(me, "登录成功！", TipDialog.TYPE_FINISH).setDialogLifeCycleListener(new DialogLifeCycleListener() {
                        @Override
                        public void onCreate(Dialog alertDialog) {
                        
                        }
                        
                        @Override
                        public void onShow(Dialog alertDialog) {
                        
                        }
                        
                        @Override
                        public void onDismiss() {
                            finish();
                        }
                    });
                } else {
                    TipDialog.show(me, "请输入用户名！", TipDialog.TYPE_WARNING);
                }
            }
        }, 3000);
    }
}
