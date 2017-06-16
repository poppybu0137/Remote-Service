package com.example.poppybu.myserviceandaidl;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;

    private MyService.MyBinder myBinder;
    private MyAIDLService myAIDLService;

    private static final String TAG="MainActivity";

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            myAIDLService=MyAIDLService.Stub.asInterface(service);
            try{
                int result=myAIDLService.plus(3,5);
                String upperStr=myAIDLService.toUpperCase("hello world");
                Log.d("TAG","result is "+result);
                Log.d("TAG","upperStr is "+upperStr);
            }catch (RemoteException e){
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService=(Button)findViewById(R.id.start_service);
        stopService=(Button)findViewById(R.id.stop_service);
        bindService=(Button)findViewById(R.id.bind_service);
        unbindService=(Button)findViewById(R.id.unbind_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        Log.d(TAG,"onCreate executed()");
        Log.d("TAG","process id is"+Process.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart executed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume executed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause executed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop executed");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart executed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy executed");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent=new Intent(this,MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent=new Intent(this,MyService.class);
                stopService(stopIntent);
                break;

            //Android5.0之后，禁止使用隐式调用Service;
            case R.id.bind_service:
                //Intent bindIntent=new Intent(this, MyService.class);
                Intent bindIntent=new Intent();
                bindIntent.setAction("com.example.poppybu.myserviceandaidl.MyAIDLService");
                bindIntent.setPackage(getPackageName());
                //getApplicationContext().startService(bindIntent);
                getApplicationContext().bindService(bindIntent,connection,BIND_AUTO_CREATE);
                //bindService(bindIntent,connection,BIND_AUTO_CREATE);
                //startService(bindIntent);
                break;
            case R.id.unbind_service:
                getApplicationContext().unbindService(connection);
                break;

            default:
                break;
        }
    }
}


























