package com.cmozie.regionzip;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;


public class Feedback extends Activity {
	public static WebView theWebView;
public class JavaScriptInterface {
		
	    Context context;

	 
	    JavaScriptInterface(Context c) {
	        context = c;
	       Log.i("Test", "Test1");
	    
	    }
	    
	    @JavascriptInterface
	    public void getJSText(String textValue, String rateValue) {
	        Toast.makeText(context, rateValue, Toast.LENGTH_SHORT).show();
	    }
	  
	  
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		
		WebView theWebView = (WebView) findViewById(R.id.webView);
		theWebView.loadUrl("file:///android_asset/feedback2.html");
	
		//final JavaScriptInterface jsInterface = new JavaScriptInterface(this);
		
		theWebView.addJavascriptInterface(new JavaScriptInterface(this), "Android");
		
		WebSettings webSettings = theWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		
		ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	
	    
	}
	
	
	public boolean onConsoleMessage(ConsoleMessage cm) 
    {
        Log.d("ShowMote", cm.message() + " -- From line "
                             + cm.lineNumber() + " of "
                             + cm.sourceId() );
        return true;
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
