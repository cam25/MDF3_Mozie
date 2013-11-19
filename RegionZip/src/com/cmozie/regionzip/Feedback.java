package com.cmozie.regionzip;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;

@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
public class Feedback extends Activity {
	public static WebView theWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		
		WebView theWebView = (WebView) findViewById(R.id.webView);
		theWebView.loadUrl("file:///android_asset/feedback.html");
	
		JavaScriptInterface jsInterface = new JavaScriptInterface(this);
		
		theWebView.addJavascriptInterface(jsInterface, "CallJava");
		
		WebSettings webSettings = theWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		
		ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	
	    
	}
	
	public class JavaScriptInterface {
	    Context context;

	  
	    JavaScriptInterface(Context c) {
	        context = c;
	    }
	    public void getJSText(){
	    	AlertDialog.Builder myDialog
	        = new AlertDialog.Builder(Feedback.this);
	        myDialog.setTitle("Test!");
	        myDialog.setMessage("Hopefully this loads!");
	        myDialog.setPositiveButton("ON", null);
	        myDialog.show();
	    }

	  
	}
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
