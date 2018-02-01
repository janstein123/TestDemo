package com.tct.binder;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BinderDemo";

    private Messenger mService;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "client handleMessage " + msg);
        }
    };

    private Messenger mMessager = new Messenger(mHandler);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartClick(View view) {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//                Log.d(TAG, "onServiceConnected, "+componentName+",  "+((MyService.LocalBinder)iBinder).getService().sayHello());
                Log.d(TAG, "onServiceConnected, " + componentName + ",  " + iBinder.getClass());
//                mService = new Messenger(iBinder);
//                try {
//                    Message msg = Message.obtain(null, 0x1000);
//                    msg.replyTo = mMessager;
//
//
//                    mService.send(msg);
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
                IMyAidlInterface myAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
                try {
                    Log.d(TAG, "get pid: " + myAidlInterface.getPid()+", my pid: "+ Process.myPid());
                    Log.d(TAG, "plus: " + myAidlInterface.plus(1, 100));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        }, BIND_AUTO_CREATE);
    }
}
