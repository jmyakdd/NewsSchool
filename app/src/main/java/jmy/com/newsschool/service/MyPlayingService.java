package jmy.com.newsschool.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyPlayingService extends Service {
    public static final String ACTION_START_COMMAND = "ACTION_START_COMMAND";
    private MyBinder myBinder = new MyBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("test","service onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("test","service onStartCommand");
        sendBroadcast(new Intent().setAction(ACTION_START_COMMAND));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("test","service destroy");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("test","service create");
    }

    public class MyBinder extends Binder{
        public void startDownload(){
            Log.e("test","start download");
        }
    }
}
