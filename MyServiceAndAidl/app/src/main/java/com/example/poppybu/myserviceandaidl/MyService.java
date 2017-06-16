package com.example.poppybu.myserviceandaidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {

    public static final String TAG="MyService";
    private MyBinder myBinder=new MyBinder();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"oncreate() executed");
        Log.d("TAG", "process id is " + Process.class);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy() executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //return myBinder;
        return mBinder;
    }
    class MyBinder extends Binder{
        public void startDownload(){
            Log.d(TAG,"startDownload() executed");
        }
    }
    MyAIDLService.Stub mBinder=new MyAIDLService.Stub() {

        @Override
        public int plus(int a, int b) throws RemoteException {
            return a+b;
        }

        @Override
        public String toUpperCase(String str) throws RemoteException {
            if (str!=null){
                return str.toUpperCase();
            }
            return null;
        }
    };
}



























