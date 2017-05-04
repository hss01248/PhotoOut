package com.hss01248.picoutdemo;

import android.app.Application;

import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.hss01248.frescopicker.FrescoIniter;
import com.hss01248.photoouter.PhotoUtil;

/**
 * Created by Administrator on 2017/5/1.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PhotoUtil.init(getApplicationContext(),new FrescoIniter());
        //Logger.initialize(new Settings());
        XLog.init(LogLevel.ALL);
    }
}
