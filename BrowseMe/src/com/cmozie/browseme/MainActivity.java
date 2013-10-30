package com.cmozie.browseme;

import java.net.URI;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	public EditText website;
	public static WebView browser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final WebView browser = (WebView) findViewById(R.id.webViewer);
		
		WebSettings settings = browser.getSettings();
		settings.setJavaScriptEnabled(true);
		browser.addJavascriptInterface(new WebAppInterface(this), "Android");
		
		browser.setWebViewClient(new myWebViewOnly());
		browser.loadUrl("http://www.bing.com");
		Button search = (Button) findViewById(R.id.browseButn);
		Button back = (Button) findViewById(R.id.backButn);
		Button forward = (Button) findViewById(R.id.forwardButn);
		Button history = (Button) findViewById(R.id.historyButn);
		Button refresh = (Button) findViewById(R.id.refreshButn);
		website = (EditText) findViewById(R.id.editText1);
		
		
		//conditions for buttons
		
			search.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					//gets url and passes to browser
					String url = website.getText().toString();
					browser.loadUrl(url);
					Log.i("url", url);
					
					
				}
			});
		
		
			back.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (browser.canGoBack()) {
						
						//back
						browser.goBack();
					}
					
				}
			});
		
		
			
			forward.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (browser.canGoForward()) {
						//forward
						browser.goForward();
					}
					
				}
			});
		
		
			history.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
			
			refresh.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//reload button
					browser.reload();
				}
			});
			
		}
	
	//class for my intent. 
	private class myWebViewOnly extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView theView, String theURL){
			
				
				if (Uri.parse(theURL).getHost().equals("http://www.google.com")) {
					return false;
					
				}
				Intent theIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(theURL));
				startActivity(theIntent);
				
				return true;
			}
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
