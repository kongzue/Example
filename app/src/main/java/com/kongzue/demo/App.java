package com.kongzue.demo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.kongzue.baseframework.BaseFrameworkSettings;
import com.kongzue.baseokhttp.util.BaseOkHttp;

/**
 * Author: @Kongzue
 * Github: https://github.com/kongzue/
 * Homepage: http://kongzue.com/
 * Mail: myzcxhh@live.cn
 * CreateTime: 2018/12/16 23:05
 */
public class App extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        //Fresco初始化
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this).setDownsampleEnabled(true).build();
        Fresco.initialize(this, imagePipelineConfig);
        
        //Kongzue组件初始化
        BaseFrameworkSettings.DEBUGMODE = true;
        BaseOkHttp.DEBUGMODE = true;
    }
}
