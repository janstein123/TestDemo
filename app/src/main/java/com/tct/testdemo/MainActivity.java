package com.tct.testdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.PathClassLoader;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int granted = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (granted != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult, requestCode: " + requestCode);
        for(int i = 0; i < permissions.length; i++){
            Log.d(TAG, permissions[i]+", "+grantResults[i]);
        }
    }

    public void onBtnClick(View view) {
        TestBean bean = new TestBean();
        Toast.makeText(this, bean.getVersion(), Toast.LENGTH_SHORT).show();
    }

    public void onFixClick(View view) {
//        HotFixEngine.copyDexFileToAppAndFix(this, "TestBeanHotFix.dex", true);

        Log.d("tao", "" + getClassLoader());
        PathClassLoader pathClassLoader = (PathClassLoader) getClassLoader();

        try {
            Field field = Class.forName("dalvik.system.BaseDexClassLoader").getDeclaredField("pathList");
            field.setAccessible(true);
            Object list = field.get(pathClassLoader);
            Log.d("tao", list.getClass().getName() + ", " + list.toString());

            Field field2 = Class.forName("dalvik.system.DexPathList").getDeclaredField("dexElements");
            field2.setAccessible(true);

            Object dexElements = field2.get(list);
            int len = Array.getLength(dexElements);
            for (int i = 0; i < len; i++) {
                Log.d("tao", Array.get(dexElements, i).toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
