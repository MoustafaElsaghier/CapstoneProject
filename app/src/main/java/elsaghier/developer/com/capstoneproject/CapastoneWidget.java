package elsaghier.developer.com.capstoneproject;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class CapastoneWidget extends AppWidgetProvider {

    static String HOTEL_NAME = "Arafa";
    static String HOTEL_ADDRESS = "hotel address";
    static String RESTAURANT_NAME = "Apples's";
    static String RESTAURANT_ADDRESS = "ADDRESS OF Apples's";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.capastone_widget);
        views.setTextViewText(R.id.appwidget_hotelAddress, HOTEL_ADDRESS);
        views.setTextViewText(R.id.widget_hotel_name, HOTEL_NAME);

        views.setTextViewText(R.id.appwidget_restaurantAddress, RESTAURANT_ADDRESS);
        views.setTextViewText(R.id.widget_restaurant_name, RESTAURANT_NAME);

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

