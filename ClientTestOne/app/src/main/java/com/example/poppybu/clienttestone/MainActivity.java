package com.example.poppybu.clienttestone;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//import com.example.poppybu.myserviceandaidl;

import com.example.poppybu.myserviceandaidl.MyAIDLService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG="MainActivity";

    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;

    private MyAIDLService myAIDLService;

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            myAIDLService=MyAIDLService.Stub.asInterface(service);
            try {
                int result1=myAIDLService.plus(1,1);
                int result=myAIDLService.plus(50,50);
                String upperStr=myAIDLService.toUpperCase("comes from ClientTestOne");
                Log.d(TAG,"result1 is "+result1);
                Log.d(TAG,"result is "+result);
                Log.d(TAG,"upperStr is "+upperStr);
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
            case R.id.bind_service:
                //Intent bindIntent=new Intent("com.example.poppybu.myserviceandaidl.MyAIDLService");
                //bindService(bindIntent,connection,BIND_AUTO_CREATE);
                Intent bindIntent=new Intent();
                bindIntent.setAction("com.example.poppybu.myserviceandaidl.MyAIDLService");
                //bindIntent.setPackage(getPackageName());
                bindIntent.setPackage("com.example.poppybu.myserviceandaidl");
                getApplicationContext().bindService(bindIntent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                getApplicationContext().unbindService(connection);
                break;
            default:
                break;

        }
    }
}
