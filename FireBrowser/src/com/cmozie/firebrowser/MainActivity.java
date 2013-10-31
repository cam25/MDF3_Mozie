/*
 * project 			FireBrowser
 * 
 * package			com.cmozie.firebrowser
 * 
 * name				cameronmozie
 * 
 * date				Oct 31, 2013
 */
package com.cmozie.firebrowser;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {
	public String webSite;
	public EditText urlString;
	Context context;
	public StringBuilder text;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button search = (Button) findViewById(R.id.searchButn);
		 urlString = (EditText) findViewById(R.id.editText1);

		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					
					 webSite = urlString.getText().toString();
					Log.i("website", webSite);
					
					 text = new StringBuilder(webSite); 
					 text.insert(0, "http://");
					 
					 Log.i("test", text.toString());
					
					if (urlString.length() > 1) {
						Intent theIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(text.toString()));
						startActivity(theIntent);
					}
					
					
					
				} catch (Exception e) {
					// TODO: handle exception
					
					e.printStackTrace();
				}
				
			}
		});
		
		
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
