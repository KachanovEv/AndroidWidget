package com.mobile.androidwidget.view.widget


import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Build
import android.util.SizeF
import android.widget.RemoteViews

import com.mobile.androidwidget.R

class WeatherWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {

    val smallView = RemoteViews(context.packageName, R.layout.widget_weather_small)
    val mediumView = RemoteViews(context.packageName, R.layout.widget_weather_medium)
    val largeView = RemoteViews(context.packageName, R.layout.widget_weather_lage)

    val viewMapping: Map<SizeF, RemoteViews> = mapOf(
        SizeF(180f, 110f) to smallView,
        SizeF(270f, 110f) to mediumView,
        SizeF(270f, 280f) to largeView
    )

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        appWidgetManager.updateAppWidget(appWidgetId, RemoteViews(viewMapping))
    } else {
        appWidgetManager.updateAppWidget(appWidgetId, smallView)
    }
}