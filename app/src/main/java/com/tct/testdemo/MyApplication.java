package com.tct.testdemo;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by tao.j on 2017/12/18.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        HotFixEngine.copyDexFileToAppAndFix(this, "TestBeanHotFix.dex", true);

    }

    private String getVersionName() {
        String name = null;
        try {
            name = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }
}
