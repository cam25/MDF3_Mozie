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

import java.io.File;
import java.io.IOException;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnInfoListener;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.drm.DrmErrorEvent;
import android.drm.DrmManagerClient;
import android.drm.DrmManagerClient.OnErrorListener;
import android.hardware.Camera;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity implements SurfaceHolder.Callback, OnInfoListener,OnErrorListener {

	public Button startRecorder;
	public ImageButton playVid;
	public ImageButton stopRecord;
	public VideoView theView;
	public Camera theCamera;
	public SurfaceHolder surface;
	public MediaRecorder mediaR;
	public String videoFile;
	public ImageButton record;
	public Context context;
	public Button save;
	public MediaPlayer mp;
	public ImageButton info;
	public TextView rMessage;
	static final int NOTIFICATION_ID = 1;
	
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		 record = (ImageButton) findViewById(R.id.recordButn);
		 playVid = (ImageButton) findViewById(R.id.playButn);
		 stopRecord = (ImageButton) findViewById(R.id.stopButn);
		 theView = (VideoView) this.findViewById(R.id.videoView1);
		 info = (ImageButton) findViewById(R.id.infoButn);
		 rMessage = (TextView) findViewById(R.id.recording);
		 context = this;
	
		
	
		
		record.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				initialize();
				//notification that the video has been stored and showing where its stored for easy access.
				Notification notification = new Notification();
				NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				
				NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context).
						setSmallIcon(R.drawable.ic_launcher).setContentTitle("RecordMe").setContentText("video stored: " + videoFile);
				
				notification = notificationBuilder.build();
				nm.notify(NOTIFICATION_ID, notification);
				
				if (mediaR == null) {
					mediaR = new MediaRecorder();	
				}
				//starts recording		
				mediaR.start();
				//sets message visible
				rMessage.setVisibility(View.VISIBLE);
				rMessage.setText("RECORDING...");
				playVid.setEnabled(false);
				stopRecord.setEnabled(true);
				
				//button toggle
				
			
			}
		});
		playVid.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				Intent infoIntent = new Intent(context,VideoPlayback.class);
				startActivity(infoIntent);
				rMessage.setVisibility(View.GONE);
				
			}
		});
	
		
		stopRecord.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				mediaR.setOnInfoListener(null);
				mediaR.setOnErrorListener(null);
				try {
					
					mediaR.stop();
					mediaR.reset();
					rMessage.setVisibility(View.GONE);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				releaseRecord();
				releaseCam();
				
				playVid.setEnabled(true);
			}
			
	});
			
		info.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				AlertDialog.Builder alert = new AlertDialog.Builder(context);
				alert.setTitle("How it Works");
				alert.setMessage("Click Record, Stop the record and play the video. Thats it! Enjoy");
				alert.setCancelable(false);
				alert.setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
				
					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.cancel();
					}
				});
				alert.show();
				
			}
		});
		
		
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	public void onPause(){
		releaseRecord();
		releaseCam();
		super.onPause();
		
	}
	
	/**
	 * Release cam.
	 */
	private void releaseCam() {
		// TODO Auto-generated method stub
		if (theCamera!= null) {
			try {
				theCamera.reconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.i("Error", "Here");
			}
			theCamera.release();
			theCamera = null;
		}
	}

	/**
	 * Release record.
	 */
	private void releaseRecord() {
		// TODO Auto-generated method stub
		if (mediaR != null) {
			mediaR.release();
			
		}
	}

	/**
	 * Initialize.
	 */
	public void initialize(){
		
		
		//set videoFile string to the value of the directory + the filename and filetype
		videoFile = Environment.getExternalStorageDirectory() + "/videoFile.mp4";
		Log.i("fileLoc", videoFile);
		
		//replaces current file 
		File overwriteFile = new File(videoFile);
		
		if (overwriteFile.exists()) {
			Log.i("File", "Exists");
			overwriteFile.delete();
		}
		try {
			
		//initializing MediaRecorder	
		mediaR = new MediaRecorder();	
		if (mediaR == null) {
			mediaR = new MediaRecorder();	
		}
		//when initializing the recorder stop the surface preview
		if (theCamera == null) {
			theCamera = Camera.open();
		}
		theCamera.stopPreview();
		
		//sets the display of the camera to 90 which turns camera to right side up for portrait recording
		theCamera.setDisplayOrientation(90);
		theCamera.unlock();
		
		//sets the media recorder to utilize the camera
		mediaR.setCamera(theCamera);
		
		//A/V source
		mediaR.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
		mediaR.setVideoSource(MediaRecorder.VideoSource.CAMERA);
		mediaR.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		mediaR.setVideoSize(176, 144);
		mediaR.setVideoFrameRate(30);
		//setting the video encodertype as well as audio
		mediaR.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
		mediaR.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		//set time period of recording to 1min
		mediaR.setMaxDuration(60000);
		
		//setting the preview display to the SurfaceHolder
		mediaR.setPreviewDisplay(surface.getSurface());
		
		//alerts the user that the max duration for recording has been reached.
		mediaR.setOnInfoListener(new MediaRecorder.OnInfoListener() {

		    public void onInfo(MediaRecorder mr, int what, int extra) {
		    // TODO Auto-generated method stub

		    if(what== MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED)
		    	 Toast.makeText(context, "Recording Time limit reached..", Toast.LENGTH_SHORT).show();
		    	
		    }
		});

		//setting the output file to the file type of my videoFile
		mediaR.setOutputFile(videoFile);
		mediaR.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//toggling buttons
	
		rMessage.setVisibility(View.GONE);
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

	/* (non-Javadoc)
	 * @see android.media.MediaRecorder.OnInfoListener#onInfo(android.media.MediaRecorder, int, int)
	 */
	@Override
	public void onInfo(MediaRecorder mr, int what, int extra) {
		// TODO Auto-generated method stub
		if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
	         Toast.makeText(context, "Time limit reached. Video recording is stopped now.", Toast.LENGTH_SHORT).show();
	         mediaR.stop();
		}
	}

	/* (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceChanged(android.view.SurfaceHolder, int, int, int)
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceCreated(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.i("Surface", "Created");
		
		try {
			if (theCamera == null) {
				Log.i("Null", "Camera");
			}
			theCamera.setPreviewDisplay(surface);
			theCamera.setDisplayOrientation(90);
			if (surface == null) {
				Log.i("Null", "surface");
			}else if (surface !=null) {
				Log.i("Not", "Null");
			}
			theCamera.startPreview();
		} catch (Exception e) {
			// TODO: handle exception
			theCamera.release();
			theCamera = null;
			e.printStackTrace();
			Log.i("Error", "Couldnt Start");
		}
	}

	/* (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceDestroyed(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder surface) {
		// TODO Auto-generated method stub
		releaseCam();
		releaseRecord();
	}

	/* (non-Javadoc)
	 * @see android.drm.DrmManagerClient.OnErrorListener#onError(android.drm.DrmManagerClient, android.drm.DrmErrorEvent)
	 */
	@Override
	public void onError(DrmManagerClient client, DrmErrorEvent event) {
		// TODO Auto-generated method stub
		
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	protected void onResume() {
		Log.i("resume", "on");
		super.onResume();
		//if camera is open
		

		if (!startRecorder()) {
			Log.i("Recorder","Open");
			finish();
		}
	}

	/**
	 * Start recorder.
	 *
	 * @return true, if successful
	 */
	@SuppressWarnings("deprecation")
	private boolean startRecorder() {
		
		try {
			//instantiates a new instance of the media recorder
			if (mediaR == null) {
				mediaR = new MediaRecorder();
			}
		
			
			theCamera = Camera.open();
			//instantiates a new instance of the camera if its null
			if (theCamera == null) {
				theCamera= Camera.open();
				
			}
			
			Camera.Parameters parameters = theCamera.getParameters();
			theCamera.lock();
			Log.i("Cam", parameters.toString());
			surface = theView.getHolder();
			Log.i("holder", surface.toString());
			surface.addCallback(this);
			surface.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			stopRecord.setEnabled(false);
			} catch (Exception e) {

				e.printStackTrace();
				
				return false;
			}		
		return true;
	}

}
