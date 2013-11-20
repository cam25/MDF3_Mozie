/*
 * project 			RegionZip
 * 
 * package			com.cmozie.classes
 * 
 * name				cameronmozie
 * 
 * date				Nov 20, 2013
 */
package com.cmozie.classes;

import com.cmozie.regionzip.R;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

// TODO: Auto-generated Javadoc
/**
 * The Class WidgetProvider.
 */
public class WidgetProvider extends AppWidgetProvider {
	public String returnedZip = "\n was the last \nselected Region";

/* (non-Javadoc)
 * @see android.appwidget.AppWidgetProvider#onReceive(android.content.Context, android.content.Intent)
 */
@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
	}

Context context;

	/* (non-Javadoc)
	 * @see android.appwidget.AppWidgetProvider#onDeleted(android.content.Context, int[])
	 */
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "Widget Removed", Toast.LENGTH_SHORT).show();
		
	}

	/* (non-Javadoc)
	 * @see android.appwidget.AppWidgetProvider#onUpdate(android.content.Context, android.appwidget.AppWidgetManager, int[])
	 */
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);

			
			RemoteViews theView = new RemoteViews(context.getPackageName(),R.layout.w_layout);
			theView.setTextViewText(R.id.updateInfo, WidgetSettings.zipp);
			appWidgetManager.updateAppWidget(appWidgetIds, theView);
		
	}

}
