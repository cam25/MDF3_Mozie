package com.cmozie.browseme;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class MainActivity extends Activity {

	public EditText website;
	public static WebView browser;
	URL actualURL = null;
	public ArrayList<String> arrayList1;
	public Spinner historySpinner;
	public static ArrayAdapter<String> adapter;
	public static Button back;
	public StringBuilder text;
	public static Button sendMail;
	private class myWebViewOnly extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView theView, String theURL){
			
				//sets the view to load the url input
				theView.loadUrl(theURL);
				//historySpinner = (Spinner) findViewById(R.id.historyList);
				//arrayList1.add(theURL);
				//adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_dropdown_item_1line,arrayList1);
				//Log.i("test", arrayList1.toString());
				//historySpinner.setAdapter(adapter);
				
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
		
		//sets the view span to not be zoomed in
		settings.setLoadWithOverviewMode(true);
		settings.setUseWideViewPort(true);
		
		//enables javascript to be used in browser
		browser.addJavascriptInterface(new WebAppInterface(this), "Android");
		
		//sets my webView to handled upon searches inside the webview
		browser.setWebViewClient(new myWebViewOnly());
	
		
		//elements
		Button search = (Button) findViewById(R.id.browseButn);
		final Button back = (Button) findViewById(R.id.backButn);
		Button forward = (Button) findViewById(R.id.forwardButn);
		sendMail = (Button) findViewById(R.id.emailButn);
		Button refresh = (Button) findViewById(R.id.refreshButn);
		Button clear = (Button) findViewById(R.id.clear);
		
		
		website = (EditText) findViewById(R.id.editText1);
	
		if (website.getText().toString().length() < 1) {
			Log.i("Test", "Test");
		}
		//conditions for buttons
		website.setSelected(false);
			search.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					//gets url and passes to browser
					String url = website.getText().toString();
					
					InputMethodManager imm = (InputMethodManager)getSystemService(
					Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(website.getWindowToken(), 0);
					
					//string builder appending http:// to beginning of url 
					text = new StringBuilder();
					text = new StringBuilder(url);
					text.insert(0, "http://");
					 
					 //adding items to arraylist for history
					 arrayList1 = new ArrayList<String>();
					 arrayList1.add(url);
					
					//loading url in webview
					browser.loadUrl(text.toString());
					Log.i("url", url);
					Log.i("List", arrayList1.toString());

					
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
		
		
			sendMail.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					//send an email
					Intent emailIntent = new Intent(Intent.ACTION_SEND);
					//specifies mail clients only
					emailIntent.setType("message/rfc822");
					startActivity(Intent.createChooser(emailIntent, "Choose an Email Account"));
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
			
			clear.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					website.setText("");
					
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
