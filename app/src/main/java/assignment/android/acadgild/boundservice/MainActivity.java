/*
A bound service is a service which allows other applications to bind and interact with it.
This is the implementation of Service where we have to override onBind() that will return IBinder.
 To bind and unbind service from main thread of activity we call bindService() and unbindService() respectively.
 android.content.ServiceConnection is an interface which is used to monitor the state of service. We need to override following methods.
onServiceConnected(ComponentName name, IBinder service) : This is called when service is connected to the application.
onServiceDisconnected(ComponentName name) : This is called when service is disconnected.
*/



package assignment.android.acadgild.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnShow;
    TextView txtShow;
    MyLocalService localService;
    private boolean isBound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setIds();
    }
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity.this, MyLocalService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(connection);
            isBound = false;
        }
    }

    public  void setIds()
    {
        btnShow=(Button)findViewById(R.id.btn_date);
        txtShow=(TextView)findViewById(R.id.txtViewTime);
    }
    public void displayDate(View v) {
        if (isBound) {

            Date date = localService.getCurrentDate();
            txtShow.setText(""+date);
            Toast.makeText(this, String.valueOf(date), Toast.LENGTH_SHORT).show();
        }
    }

    private ServiceConnection connection=new ServiceConnection() {//Service Connection is an interface which has 2 methods
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyLocalService.LocalBinder binder = (MyLocalService.LocalBinder) iBinder;
            localService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;

        }
    };
}
