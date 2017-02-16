package assignment.android.acadgild.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import java.util.Calendar;
import java.util.Date;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by DivyaVipin on 2/14/2017.
 */

public class MyLocalService extends Service {
    private final IBinder binder = new LocalBinder();
    public class LocalBinder extends Binder
    {
        MyLocalService getService() {
            return MyLocalService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {//onBind() returns a binder so we need a LocalBinder class
        return binder;
    }
    public Date getCurrentDate() {//MyLocalService is providing Date feature as a service
        return Calendar.getInstance().getTime();
    }
}
