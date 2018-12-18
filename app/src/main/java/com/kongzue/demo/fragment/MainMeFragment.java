package com.kongzue.demo.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.OnJumpResponseListener;
import com.kongzue.demo.R;
import com.kongzue.demo.activity.LoginActivity;

/**
 * Author: @Kongzue
 * Github: https://github.com/kongzue/
 * Homepage: http://kongzue.com/
 * Mail: myzcxhh@live.cn
 * CreateTime: 2018/12/16 22:20
 */
@Layout(R.layout.fragment_main_me)
public class MainMeFragment extends BaseFragment {
    
    private LinearLayout boxBody;
    private LinearLayout boxUser;
    private TextView txtUser;
    
    @Override
    public void initViews() {
        boxBody = findViewById(R.id.box_body);
        boxUser = findViewById(R.id.box_user);
        txtUser = findViewById(R.id.txt_user);
    }
    
    @Override
    public void initDatas() {
        boxBody.setPadding(0, me.getStatusBarHeight(), 0, 0);
    }
    
    @Override
    public void setEvents() {
        boxUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(LoginActivity.class, new OnJumpResponseListener() {
                    @Override
                    public void OnResponse(JumpParameter jumpParameter) {
                        if (jumpParameter!=null)txtUser.setText(jumpParameter.getString("userName"));
                    }
                });
            }
        });
    }
}
