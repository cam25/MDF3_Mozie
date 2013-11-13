package com.cmozie.classes;

import java.util.Random;

import com.cmozie.regionzip.R;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;

public class RegionWidget extends AppWidgetProvider {
	public String returnedZip = "\n was the last \nselected Region";
@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
	}

Context context;

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "Widget Removed", Toast.LENGTH_SHORT).show();
		
	}

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
