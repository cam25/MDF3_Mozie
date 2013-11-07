/*
 * project 			RecordMe
 * 
 * package			com.cmozie.recordme
 * 
 * name				cameronmozie
 * 
 * date				Nov 7, 2013
 */
package com.cmozie.recordme;
import com.cmozie.recordme.R;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoPlayback.
 */
public class VideoPlayback extends Activity {
	
	VideoView theView;
	String path;
	Context context;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//sets my video view to full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        //set the view to my layout
        setContentView(R.layout.video);
        
		context = this;
		theView = (VideoView) findViewById(R.id.videoView1);
		path = "/storage/emulated/0/videoFile.mp4";

		//allows for playback
		theView.setVideoPath(path);
		theView.setMediaController(new MediaController(context));
		theView.start();
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
