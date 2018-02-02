package elsaghier.developer.com.capstoneproject.WidgetFiles;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import elsaghier.developer.com.capstoneproject.Models.SharedPreferenceFiles;
import elsaghier.developer.com.capstoneproject.R;

/**
 * Implementation of App Widget functionality.
 */
public class CapastoneWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.capastone_widget);
        views.setTextViewText(R.id.appwidget_movieName,
                SharedPreferenceFiles.
                        getFromSharedPreference(context, "film_name"));

        views.setTextViewText(R.id.appwidget_restaurantName,
                SharedPreferenceFiles.
                        getFromSharedPreference(context, "rest_name"));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}

