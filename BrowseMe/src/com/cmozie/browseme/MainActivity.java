package com.cmozie.browseme;

import java.net.URI;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		WebView browser = (WebView) findViewById(R.id.webViewer);
		
		WebSettings settings = browser.getSettings();
		settings.setJavaScriptEnabled(true);
		browser.addJavascriptInterface(new WebAppInterface(this), "Android");
		
		Uri url = Uri.parse("http://www.google.com");
		Intent webSite = new Intent(Intent.ACTION_VIEW, url);
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
