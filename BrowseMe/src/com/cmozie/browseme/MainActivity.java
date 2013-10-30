package com.cmozie.browseme;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

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
	URL actualURL = null;
	private class myWebViewOnly extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView theView, String theURL){
			
				//sets the view to load the url input
				theView.loadUrl(theURL);
				
				return true;
			}
			
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//setting intent to getIntent
		Intent intent = getIntent();
		
		//getting the data inthe intent
		Uri fireBrowserURI = intent.getData();
		
		//scheme
		Log.i("scheme", fireBrowserURI.getScheme());
		//host
		Log.i("host", fireBrowserURI.getHost());
		//path
		Log.i("path", fireBrowserURI.getPath());
		try {				
			//combines the uri parts to a whole url string
			 actualURL = new URL(fireBrowserURI.getScheme(),
					 		fireBrowserURI.getHost(),
					 		fireBrowserURI.getPath());
			 				Log.i("URL = ", actualURL.toString());
			 				
			 				
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
			
		
		final WebView browser = (WebView) findViewById(R.id.webViewer);
		//passing url from intent to broswer/webview here
		browser.loadUrl(actualURL.toString());
		WebSettings settings = browser.getSettings();
		settings.setJavaScriptEnabled(true);
		
		browser.addJavascriptInterface(new WebAppInterface(this), "Android");
		
		browser.setWebViewClient(new myWebViewOnly());
		
		
		
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
					website.setText("");
					
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
	

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
