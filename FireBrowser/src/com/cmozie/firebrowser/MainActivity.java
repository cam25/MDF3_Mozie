package com.cmozie.firebrowser;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	public String webSite;
	public EditText urlString;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button search = (Button) findViewById(R.id.searchButn);
		 urlString = (EditText) findViewById(R.id.editText1);
		

		 //String url = "http://www.nike.com";
		// urlString.setText(url);
		 
		
		 
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					 webSite = urlString.getText().toString();
					Log.i("website", webSite);
					
					if (urlString.length() > 1) {
						Intent theIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webSite));
						startActivity(theIntent);
					}
					
					
					
				} catch (Exception e) {
					// TODO: handle exception
					
					e.printStackTrace();
				}
				
			}
		});
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
