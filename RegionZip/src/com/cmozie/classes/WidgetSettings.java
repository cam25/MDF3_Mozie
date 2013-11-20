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

import java.net.URL;
import com.cmozie.regionzip.MainActivity;
import com.cmozie.regionzip.R;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;

// TODO: Auto-generated Javadoc
/**
 * The Class WidgetSettings.
 */
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
	public static String zipp;
	public RemoteViews rViews;
	public ImageButton widget;
	public LinearLayout theLayout;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.widgetset);
		
		//UI elements
		theLayout = (LinearLayout)findViewById(R.id.layout);
		regions = (RadioGroup) findViewById(R.id.radioGroup1);
		north = (RadioButton)findViewById(R.id.north);
		south = (RadioButton)findViewById(R.id.south);
		east = (RadioButton)findViewById(R.id.east);
		west = (RadioButton)findViewById(R.id.west);
		showRegion = (Button)findViewById(R.id.button1);
		widget = (ImageButton) this.findViewById(R.id.widgButn);
		context = this;
		
		
		//On clicks for color toggling
		north.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				theLayout.setBackgroundColor(Color.BLUE);
			}
		});
		south.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				theLayout.setBackgroundColor(Color.RED);
			}
		});
		
		east.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

		    	theLayout.setBackgroundColor(Color.GREEN);
			}
		});
		
			west.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					theLayout.setBackgroundColor(Color.YELLOW);
				}
			});
		
	
			
		showRegion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		
				//bundle for extras
				Bundle theExtras = getIntent().getExtras();
				if (theExtras != null) {
					
					//int holding the widgit id valid/invalid
					int widgId = theExtras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
					
					
					//if the widgit id is not invalid
					if (widgId != AppWidgetManager.INVALID_APPWIDGET_ID) {
						switch (regions.getCheckedRadioButtonId()) {
						case R.id.north:
							Log.i("button", "north");
							zipp = "10007";
							 
							Intent intent = new Intent(Intent.ACTION_VIEW,
					    			
					    			
									Uri.parse("http://maps.google.com/maps?q="+zipp+"&zoom=14&size=512x512&maptype=roadmap&sensor=false"));

							    	//starts the intent activity for map view
							    	startActivity(intent);
							    	rViews = new RemoteViews(context.getPackageName(),R.layout.w_layout);
									
									//passes the data to my widget
									rViews.setTextViewText(R.id.updateInfo, "Last Area Located:\n "+ zipp );
									theLayout.setBackgroundColor(Color.BLUE);
							break;
						case R.id.south:
							Log.i("button", "south");
							zipp = "33133";
							Intent intent2 = new Intent(Intent.ACTION_VIEW,
					    			
					    			
									Uri.parse("http://maps.google.com/maps?q="+zipp+"&zoom=14&size=512x512&maptype=roadmap&sensor=false"));

							    	//starts the intent activity
							    	startActivity(intent2);
							    	rViews = new RemoteViews(context.getPackageName(),R.layout.w_layout);
									
							    	//passes the data to my widget
							    	rViews.setTextViewText(R.id.updateInfo, "Last Area Located:\n "+ zipp );
							    	theLayout.setBackgroundColor(Color.RED);
							break;
							
						case R.id.east:
							zipp = "10001";
							Intent intent3 = new Intent(Intent.ACTION_VIEW,
					    			
					    			
									Uri.parse("http://maps.google.com/maps?q="+zipp+"&zoom=14&size=512x512&maptype=roadmap&sensor=false"));

							    	//starts the intent activity
							    	startActivity(intent3);
							    	rViews = new RemoteViews(context.getPackageName(),R.layout.w_layout);
									
							    	//passes the data to my widget
							    	rViews.setTextViewText(R.id.updateInfo, "Last Area Located:\n "+ zipp );
							    	theLayout.setBackgroundColor(Color.GREEN);
							Log.i("button", "east");
							break;
						case R.id.west:
							zipp = "94105";
							Intent intent4 = new Intent(Intent.ACTION_VIEW,
					    			
					    			
									Uri.parse("http://maps.google.com/maps?q="+zipp+"&zoom=14&size=512x512&maptype=roadmap&sensor=false"));

							    	//starts the intent activity
							    	startActivity(intent4);
							    	rViews = new RemoteViews(context.getPackageName(),R.layout.w_layout);
									
							    	//passes the data to my widget
							    	rViews.setTextViewText(R.id.updateInfo, "Last Area Located:\n "+ zipp );
							Log.i("button", "west");
							theLayout.setBackgroundColor(Color.YELLOW);
							break;
						default:
							break;
						}				
						
						//intent/pending intent to make my widget clickable and open my main activity
						Intent imageButnNtent = new Intent(context,MainActivity.class);
						PendingIntent pi = PendingIntent.getActivity(context, 0, imageButnNtent, PendingIntent.FLAG_UPDATE_CURRENT);
						
						
						rViews.setOnClickPendingIntent(R.id.widgButn, pi);
						AppWidgetManager.getInstance(context).updateAppWidget(widgId, rViews);
						
						Intent result = new Intent();
						result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgId);
						setResult(RESULT_OK,result);
						finish();
					}
					
				}
			}
			
		});
		
		
	}

	
	
}
