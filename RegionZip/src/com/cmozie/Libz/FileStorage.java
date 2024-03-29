/*
 * project 			RegionZip
 * 
 * package			com.cmozie.Libz
 * 
 * name				cameronmozie
 * 
 * date				Nov 20, 2013
 */

package com.cmozie.Libz;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

// TODO: Auto-generated Javadoc
/**
 * The Class FileStuff.
 */
public class FileStorage {

	/**
	 * Store string file.
	 *
	 * @param context the context
	 * @param filename the filename
	 * @param content the content
	 * @param external the external
	 * @return the boolean
	 */
	
	private static FileStorage m_instance;
	
	//constructor
	/**
	 * Instantiates a new file stuff.
	 */
	private FileStorage(){
		
		
	}
	
	/**
	 * Gets the single instance of FileStuff.
	 *
	 * @return single instance of FileStuff
	 */
	public static FileStorage getInstance(){
		if (m_instance == null) {
			m_instance = new FileStorage();
			
		}
		return m_instance;
				
		
	}
	
	
	/**
	 * Store string file.
	 *
	 * @param context the context
	 * @param filename the filename
	 * @param content the content
	 * @param external the external
	 * @return the boolean
	 */
	@SuppressWarnings("resource")
	public static Boolean storeStringFile(Context context, String filename, String content, Boolean external){
		
		try {
			File file;
			FileOutputStream fos = null;
			if (external) {
				file = new File(context.getExternalFilesDir(null),filename);
				fos = new FileOutputStream(file);
				
			}else {
				
				fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
			}
			fos.write(content.getBytes());
			
			
			Log.i("WRITING OF STRING FILE", "Success!");
			fos.close();
			
		} catch (IOException e) {
			
			Log.i("WRITE ERROR",filename);
		}
		
			return true;
	}

	/**
	 * Store object file.
	 *
	 * @param context the context
	 * @param filename the filename
	 * @param content the content
	 * @param external the external
	 * @return the boolean
	 */
	@SuppressWarnings("resource")
	public static Boolean storeObjectFile(Context context, String filename, Object content, Boolean external){
		try {
			File file;
			FileOutputStream fos;
			ObjectOutputStream oos;
			if (external) {
				file = new File(context.getExternalFilesDir(null),filename);
				fos = new FileOutputStream(file);
				
			}else {
				
				fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
			}
			oos = new ObjectOutputStream(fos);
			oos.writeObject(content);
			oos.close();
			
			fos.close();
		} catch (IOException e) {
			
			Log.i("WRITE ERROR",filename);
		}
		return true;
	}
	
	/**
	 * Read string file.
	 *
	 * @param context the context
	 * @param filename the filename
	 * @param external the external
	 * @return the string
	 */
	@SuppressWarnings("resource")
	public static String readStringFile(Context context, String filename, Boolean external){
		
		String content = "";
		try {
			File file;
			FileInputStream fin;
			if (external) {
				file = new File(context.getExternalFilesDir(null), filename);
				fin = new FileInputStream(file);
			}else{
				file = new File(filename);
				fin = context.openFileInput(filename);
				
				
			}
			
			BufferedInputStream bin = new BufferedInputStream(fin);
			byte[] contentBytes = new byte[1024];
			int bytesRead = 0;
			StringBuffer contentBuffer = new StringBuffer();
			
			while((bytesRead = bin.read(contentBytes))!= -1){
				
				content = new String(contentBytes,0,bytesRead);
				contentBuffer.append(content);
				
			}
			content = contentBuffer.toString();
			
			fin.close();
		} catch (FileNotFoundException e) {
			Toast toast = Toast.makeText(context, "Nothing in local storage  ", Toast.LENGTH_SHORT);
			
			toast.show();
			Log.e("READ ERROR","FILE NOT FOUND" + filename);
		}catch (IOException e) {
			Log.e("READ ERROR","I/O ERROR");
		}
		
		
		return content;
		
	}

/**
 * Read object file.
 *
 * @param context the context
 * @param filename the filename
 * @param external the external
 * @return the object
 */
@SuppressWarnings("resource")
public static Object readObjectFile(Context context, String filename, Boolean external){
		
		Object content = new Object();
		try {
			File file;
			FileInputStream fin;
			if (external) {
				file = new File(context.getExternalFilesDir(null), filename);
				fin = new FileInputStream(file);
			}else{
				file = new File(filename);
				fin = context.openFileInput(filename);
				
			}
			ObjectInputStream ois = new ObjectInputStream(fin);
			try {
				content = (Object) ois.readObject();
			} catch (Exception e) {
				Log.e("READ ERROR","INVALID JAVA OBJECT FILE");
			}
			ois.close();
			fin.close();
		} catch (FileNotFoundException e) {
			Log.e("READ ERROR","FILE NOT FOUND" + filename);
			return null;
		}catch (IOException e) {
			Log.e("READ ERROR","I/O ERROR");
			
		}
		return content;
		
	}
}
