package com.example.beerlovers.service;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.beerlovers.AppWidget;
import com.example.beerlovers.BuildConfig;
import com.example.beerlovers.model.BaseResponse;
import com.example.beerlovers.model.Beer;

import java.io.IOException;

import retrofit2.Call;


public class UpdateWidgetService extends IntentService {

    public UpdateWidgetService() {
        super("UpdateWidgetService");
    }


    public static void startAction(Context context, int appWidgetId) {
        Intent intent = new Intent(context, UpdateWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            handleNextUpdate(intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0));

        }
    }

    private void handleNextUpdate(int intExtra) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        Call<BaseResponse<Beer>> call = RequestService.retrofit.create(RequestService.class).getRandomBeer(BuildConfig.API_KEY);
        try {
            BaseResponse<Beer> response = call.execute().body();
            AppWidget.updateWidget(this, appWidgetManager, intExtra, response.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
