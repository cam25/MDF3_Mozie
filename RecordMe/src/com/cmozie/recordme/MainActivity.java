package com.cmozie.recordme;

import java.io.File;
import java.io.IOException;



import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnInfoListener;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.drm.DrmErrorEvent;
import android.drm.DrmManagerClient;
import android.drm.DrmManagerClient.OnErrorListener;
import android.hardware.Camera;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity implements SurfaceHolder.Callback, OnInfoListener,OnErrorListener {

	public ImageButton startRecorder;
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 startRecorder = (ImageButton) findViewById(R.id.openRecorder);
		 record = (ImageButton) findViewById(R.id.recordButn);
		 playVid = (ImageButton) findViewById(R.id.playButn);
		 stopRecord = (ImageButton) findViewById(R.id.stopButn);
		 theView = (VideoView) this.findViewById(R.id.videoView1);
		
		context = this;
		
		
		 //Camera.open();
		
		startRecorder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mediaR != null) {
					Log.i("Not", "Null");
				}
				videoFile = Environment.getExternalStorageDirectory() + "/videoFile.mp4";
				Log.i("fileLoc", videoFile);
				File overwriteFile = new File(videoFile);
				
				if (overwriteFile.exists()) {
					Log.i("File Exists", "Overwrite?");
				}
				try {
				mediaR = new MediaRecorder();	
				
				theCamera.stopPreview();
				theCamera.unlock();
				
				
				mediaR.setCamera(theCamera);
				
				//audio source
				mediaR.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
				mediaR.setVideoSource(MediaRecorder.VideoSource.CAMERA);
				mediaR.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
				mediaR.setVideoSize(176, 144);
				mediaR.setVideoFrameRate(15);
				
				mediaR.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
				mediaR.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				mediaR.setMaxDuration(7000);
				mediaR.setPreviewDisplay(surface.getSurface());

				
				mediaR.setOutputFile(videoFile);
				mediaR.prepare();
				} catch (Exception e) {
					e.printStackTrace();
				}
				playVid.setEnabled(true);
				record.setEnabled(true);
			}
		});
	
		
		record.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mediaR.start();
				
				startRecorder.setEnabled(false);
				stopRecord.setEnabled(true);
				record.setEnabled(false);
			
			}
		});
		playVid.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				Intent infoIntent = new Intent(context,VideoPlayback.class);
				startActivity(infoIntent);
				
			}
		});
	
		
		stopRecord.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					mediaR.stop();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				releaseCam();
				releaseRecord();
				startRecorder.setEnabled(true);
			}
			
	});
			

		
		
	}
	
	
	private boolean checkCameraHardware(Context context) {
	    if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
	        // this device has a camera
	    	Log.i("Has", "camera");
	        return true;
	    } else {
	        // no camera on this device
	    	Log.i("No", "camera");
	        return false;
	    }
	}
	private void releaseCam() {
		// TODO Auto-generated method stub
		if (theCamera!= null) {
			try {
				theCamera.reconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			theCamera.release();
			theCamera = null;
		}
	}

	private void releaseRecord() {
		// TODO Auto-generated method stub
		if (mediaR != null) {
			mediaR.release();
			mediaR = null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onInfo(MediaRecorder mr, int what, int extra) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.i("Surface", "Created");
		
		try {
			if (theCamera == null) {
				Log.i("Null", "Camera");
			}
			theCamera.setPreviewDisplay(surface);
			
			if (surface == null) {
				Log.i("Null", "surface");
			}else if (surface !=null) {
				Log.i("Not", "Null");
			}
			theCamera.startPreview();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.i("Error", "Couldnt Start");
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder surface) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(DrmManagerClient client, DrmErrorEvent event) {
		// TODO Auto-generated method stub
		
		
	}
	protected void onResume() {
		Log.i("resume", "on");
		super.onResume();
		//if camera is open
	playVid.setEnabled(false);
	record.setEnabled(false);
	stopRecord.setEnabled(false);
		if (!startRecorder()) {
			Log.i("Recorder","Open");
			finish();
		}
	}

	private boolean startRecorder() {
		
		try {
			
			
			theCamera = Camera.open();
			if (theCamera != null) {
				Log.i("Cam", "Not null");
			}
			Camera.Parameters parameters = theCamera.getParameters();
			theCamera.lock();
			Log.i("Cam", parameters.toString());
			surface = theView.getHolder();
			Log.i("holder", surface.toString());
			surface.addCallback(this);
			surface.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			} catch (Exception e) {

				e.printStackTrace();
				
				return false;
			}		
		return true;
	}

}
