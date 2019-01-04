package com.youzheng.zhejiang.robertmoog;

import android.app.Application;
import android.graphics.Color;

import com.tencent.bugly.crashreport.CrashReport;
import com.youzheng.zhejiang.robertmoog.Store.utils.UIImageLoader;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;

public class RMApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initGallerFinal();
//        CrashReport.initCrashReport(getApplicationContext(), "175a6bc0e2", true);
    }

    private void initGallerFinal() {

        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(getResources().getColor(R.color.colorPrimary))
                .setTitleBarTextColor(Color.WHITE)
                .setTitleBarIconColor(Color.WHITE)
                .setFabNornalColor(getResources().getColor(R.color.dark1))
                .setFabPressedColor(getResources().getColor(R.color.colorPrimary))
                .setCheckNornalColor(getResources().getColor(R.color.dark1))
                .setCheckSelectedColor(getResources().getColor(R.color.colorPrimary))
                //...其他配置
                .build();

        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        ImageLoader imageloader = new UIImageLoader(this);
        CoreConfig coreConfig = new CoreConfig.Builder(this, imageloader, theme)
                .setFunctionConfig(functionConfig)
                .build();

        GalleryFinal.init(coreConfig);
    }
}
