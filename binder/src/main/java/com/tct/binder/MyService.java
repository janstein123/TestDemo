package com.tct.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "BinderDemo";

//    private Messenger mMsgr = new Messenger(new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            Log.d(TAG, "Service handleMessage, "+msg.toString());
//            Messenger messenger = msg.replyTo;
//            try {
//                messenger.send(Message.obtain(null, 0x1001));
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//    });

    class LocalBinder extends IMyAidlInterface.Stub {
        public MyService getService(){
            return MyService.this;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            //do nothing
        }

        @Override
        public int plus(int a, int b) throws RemoteException {
            return a+b;
        }

        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }
    }

    private final LocalBinder mBinder = new LocalBinder();

    public MyService() {

    }

    public String sayHello(){
        return "Hello";
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
//        return mMsgr.getBinder();
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }
}
