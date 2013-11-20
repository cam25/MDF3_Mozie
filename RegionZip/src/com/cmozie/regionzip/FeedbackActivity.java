/*
 * project 			RegionZip
 * 
 * package			com.cmozie.regionzip
 * 
 * name				cameronmozie
 * 
 * date				Nov 20, 2013
 */
package com.cmozie.regionzip;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;


// TODO: Auto-generated Javadoc
/**
 * The Class FeedbackActivity.
 */
@SuppressLint("SetJavaScriptEnabled")
public class FeedbackActivity extends Activity {
	public static WebView theWebView;
	
	//javascript interface
/**
	 * The Class JavaScriptInterface.
	 */
	public class JavaScriptInterface {
		
	    Context context;
	   
	
	    /**
    	 * Instantiates a new java script interface.
    	 *
    	 * @param c the c
    	 */
    	JavaScriptInterface(Context c) {
	        context = c;
	       Log.i("Test", "Test1");
	    
	    }
	    
	    
	    /**
    	 * Gets the jS text.
    	 *
    	 * @param textValue the text value
    	 * @param rateValue the rate value
    	 * @return the jS text
    	 */
    	@JavascriptInterface
	    public void getJSText(String textValue, String rateValue) {
	    	//toast for sending feedback
	        Toast.makeText(context, "Thanks for your Feedback!", Toast.LENGTH_SHORT).show();
	        
	        //email intent 
	        Intent emailIntent = new Intent(Intent.ACTION_SEND);
	        emailIntent.setType("message/rfc822");
	        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "cmozie@ymail.com" });
	        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "RegionZip Feedback");
	        emailIntent.putExtra(Intent.EXTRA_TEXT, "You rated the app: " + rateValue + " \nComments:" + textValue);
	        startActivity(Intent.createChooser(emailIntent, "Send Feedback Via Email:"));
	        //sets image on screen 
	        theWebView.loadUrl("file:///android_asset/good.png");
	    }
	  
	  
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		
		//sets the webview
		WebView theWebView = (WebView) findViewById(R.id.webView);
		theWebView.loadUrl("file:///android_asset/feedback2.html");
	
		//adds the javascript interface to the webview for access to between html/webview via javascript
		theWebView.addJavascriptInterface(new JavaScriptInterface(this), "Android");
		
		WebSettings webSettings = theWebView.getSettings();
		
		//enables javascript
		webSettings.setJavaScriptEnabled(true);
		
		//enables actionbar
		ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    
	    
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		//transitions to each page and back via click of item in action bar
		case android.R.id.home:
			Intent homeIntent = new Intent(this, MainActivity.class);
			  homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			  startActivity(homeIntent);
			break;
		case R.id.sendFeedback:
			Intent feedBackIntent = new Intent(this, FeedbackActivity.class);
			  startActivity(feedBackIntent);
			  break;
		case R.id.aboutPage:
			Intent aboutIntent = new Intent(this, AboutActivity.class);
			  startActivity(aboutIntent);		
		break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
		
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
