/*
 * project 			RegionZip
 * 
 * package			com.cmozie.classes
 * 
 * name				cameronmozie
 * 
 * date				Nov 20, 2013
 */


package com.cmozie.classes;


import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;



// TODO: Auto-generated Javadoc
/**
 * The Class SearchForm.
 */
@SuppressLint("ViewConstructor")
public class SearchForm extends LinearLayout {
	
	EditText searchField;
	Button searchButn;
	
	
	/**
	 * Instantiates a new search form.
	 *
	 * @param context the context
	 * @param hint the hint
	 * @param buttonText the button text
	 */
	public SearchForm(Context context,String hint, String buttonText) {
		
		//allows to import the value
		super(context);
		
		searchField = new EditText(context);
		
		LayoutParams lp;
		
		lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
		
		searchField.setLayoutParams(lp);
		searchField.setHint(hint);
		
		searchButn = new Button(context);
		searchButn.setText(buttonText);
		
		this.addView(searchField);
		this.addView(searchButn);
		
		lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		this.setLayoutParams(lp);
		
	}
	
	/**
	 * Gets the field.
	 *
	 * @return the field
	 */
	public EditText getField(){
		
		return searchField;
	}

	/**
	 * Gets the button.
	 *
	 * @return the button
	 */
	public Button getButton(){
		
		return searchButn;
	}
}
