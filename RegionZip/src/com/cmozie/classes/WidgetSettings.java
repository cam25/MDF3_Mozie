package com.cmozie.classes;

import java.net.MalformedURLException;
import java.net.URL;

import com.cmozie.regionzip.MainActivity;
import com.cmozie.regionzip.R;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.Toast;

public class WidgetSettings extends Activity {

	AppWidgetManager manager;
	Context context;
	int id;
	public URL theUrl = null;
	public WebView wv;
	public String url;
	public RadioGroup regions;
	public RadioButton north,south,east,west;
	public int checkedID;
	public Button showRegion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widgetset);
		regions = (RadioGroup) findViewById(R.id.radioGroup1);
		north = (RadioButton)findViewById(R.id.north);
		south = (RadioButton)findViewById(R.id.south);
		east = (RadioButton)findViewById(R.id.east);
		west = (RadioButton)findViewById(R.id.west);
		
		showRegion = (Button)findViewById(R.id.button1);
		
		showRegion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (regions.getCheckedRadioButtonId()) {
				case R.id.north:
					Log.i("button", "north");
					break;
				case R.id.south:
					Log.i("button", "south");
					break;
					
				case R.id.east:
					Log.i("button", "east");
					break;
				case R.id.west:
					Log.i("button", "west");
					break;
				default:
					break;
				}
			}
		});
		
		
	}

	
	
}
