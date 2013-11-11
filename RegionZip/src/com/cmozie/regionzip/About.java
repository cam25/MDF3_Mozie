package com.cmozie.regionzip;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class About extends Activity {

	@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			setContentView(R.layout.about);
			TextView aboutInfo = (TextView) this.findViewById(R.id.aboutText);
			aboutInfo.setText("My names Cameron Mozie and I am a student at Full Sail University. I currently reside in Landover MD, and am looking to graduate Fall 2014.");
			ActionBar actionBar = getActionBar();
		    actionBar.setDisplayHomeAsUpEnabled(true);
		    
		    
			
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		
		//returns to main activity
		case android.R.id.home:
			Intent homeIntent = new Intent(this, MainActivity.class);
			  homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			  startActivity(homeIntent);

			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
