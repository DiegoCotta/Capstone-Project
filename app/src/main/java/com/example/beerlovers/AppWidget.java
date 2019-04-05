package com.example.beerlovers;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.RemoteViews;

import com.example.beerlovers.model.Beer;
import com.example.beerlovers.service.UpdateWidgetService;
import com.example.beerlovers.utils.CircleTransform;
import com.example.beerlovers.view.activities.BeerDetailsActivity;
import com.example.beerlovers.view.activities.MainActivity;
import com.squareup.picasso.Picasso;

/**
 * Implementation of App Widget functionality.
 */
public class AppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                final int appWidgetId, final Beer beer) {

        // Construct the RemoteViews object
        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        Intent intent = new Intent(context, BeerDetailsActivity.class);
        intent.putExtra(BeerDetailsActivity.BEER_KEY, beer);
        intent.putExtra(BeerDetailsActivity.FROM, true);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        if (beer == null) {
            views.setViewVisibility(R.id.content, View.GONE);
            views.setViewVisibility(R.id.progressBar, View.VISIBLE);
        } else {
            views.setViewVisibility(R.id.content, View.VISIBLE);
            views.setViewVisibility(R.id.progressBar, View.GONE);
            views.setTextViewText(R.id.tvTitle, beer.getNameDisplay());
            views.setTextViewText(R.id.tvDescription, String.format(context.getString(R.string.widget_text), beer.getStyle().getShortName(), beer.getIbu() != null ? beer.getIbu() : beer.getStyle().getIbuMin(), beer.getAbv() != null ? beer.getAbv() : beer.getStyle().getAbvMin()));
            if (beer.getLabels() != null && beer.getLabels().getIcon() != null) {
                Handler uiHandler = new Handler(Looper.getMainLooper());
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.get()
                                .load(beer.getLabels().getIcon())
                                .transform(new CircleTransform())
                                .into(views, R.id.ivBeer, new int[]{appWidgetId});
                    }
                });

            } else {
                Handler uiHandler = new Handler(Looper.getMainLooper());
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.get().load(R.mipmap.ic_launcher)
                                .transform(new CircleTransform())
                                .into(views, R.id.ivBeer, new int[]{appWidgetId});
                    }
                });
            }
        }
        views.setOnClickPendingIntent(R.id.content, pendingIntent);
        Intent mainIntent = new Intent(context, MainActivity.class);
        PendingIntent mainPendingIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);
        views.setOnClickPendingIntent(R.id.appwidget, mainPendingIntent);

        Intent nextIntent = new Intent(context, UpdateWidgetService.class);
        nextIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        PendingIntent nextPendingIntent = PendingIntent.getService(context, appWidgetId, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        views.setOnClickPendingIntent(R.id.ibNext, nextPendingIntent);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //Start the intent service update widget action, the service takes care of updating the widgets UI
        for (int appWidgetId : appWidgetIds)
            UpdateWidgetService.startAction(context, appWidgetId);
    }

    public static void updateWidget(Context context, AppWidgetManager appWidgetManager,
                                    int appWidgetId, Beer beer) {
        updateAppWidget(context, appWidgetManager, appWidgetId, beer);

    }
}

