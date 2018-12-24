package com.youzheng.zhejiang.robertmoog;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

public class RMApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        CrashReport.initCrashReport(getApplicationContext(), "175a6bc0e2", true);
    }
}
