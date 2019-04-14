package com.example.beerlovers.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.example.beerlovers.AppWidget;
import com.example.beerlovers.BuildConfig;
import com.example.beerlovers.model.BaseResponse;
import com.example.beerlovers.model.Beer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateWidgetService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startMyOwnForeground();
        else
            startForeground(1, new Notification());
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void startMyOwnForeground(){
        String NOTIFICATION_CHANNEL_ID = "com.example.simpleapp";
        String channelName = "My Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.build();
        startForeground(2, notification);
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
         RequestService.retrofit.create(RequestService.class).getRandomBeer(BuildConfig.API_KEY).enqueue(new Callback<BaseResponse<Beer>>() {
            @Override
            public void onResponse(Call<BaseResponse<Beer>> call, Response<BaseResponse<Beer>> response) {
                AppWidget.updateWidget(UpdateWidgetService.this, appWidgetManager, intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0), response.body().getData());
            }

            @Override
            public void onFailure(Call<BaseResponse<Beer>> call, Throwable t) {
                AppWidget.updateWidget(UpdateWidgetService.this, appWidgetManager, intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0), new Beer());
            }
        });

        stopSelf();
        return START_NOT_STICKY;
    }
}
