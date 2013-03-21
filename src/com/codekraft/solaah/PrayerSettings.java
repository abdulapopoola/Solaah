package com.codekraft.solaah;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

import com.codekraft.data.Constants;
import com.codekraft.data.PrayerTime;


public class PrayerSettings extends Activity{
	private String TAG = "Prayer Settings";
	private ToggleButton fajrButton;
	private ToggleButton sunriseButton;
	private ToggleButton zuhrButton;
	private ToggleButton asrButton;
	private ToggleButton maghribButton;
	private ToggleButton ishaButton;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
        fajrButton = (ToggleButton) findViewById(R.id.fajrToggle);
        sunriseButton = (ToggleButton) findViewById(R.id.sunriseToggle);
        zuhrButton = (ToggleButton) findViewById(R.id.zuhrToggle);
        asrButton = (ToggleButton) findViewById(R.id.asrToggle);
        maghribButton = (ToggleButton) findViewById(R.id.maghribToggle);
        ishaButton = (ToggleButton) findViewById(R.id.ishaToggle);
        
        /* Set Change Check Listener to Toggle Button */
        fajrButton.setOnCheckedChangeListener(listener);
        sunriseButton.setOnCheckedChangeListener(listener);
        zuhrButton.setOnCheckedChangeListener(listener);
        asrButton.setOnCheckedChangeListener(listener);
        maghribButton.setOnCheckedChangeListener(listener);
        ishaButton.setOnCheckedChangeListener(listener);
        
        LoadPreferences();
	}
	
	private void SavePreferences(String key, Boolean value){
	    SharedPreferences sharedPreferences = getSharedPreferences("SETTINGS_PREF", MODE_PRIVATE);
	    SharedPreferences.Editor editor = sharedPreferences.edit();
	    editor.putBoolean(key, value);
	    editor.commit();
	   }
	  
	   private void LoadPreferences(){
	    SharedPreferences sharedPreferences = getSharedPreferences("SETTINGS_PREF", MODE_PRIVATE);
	   Boolean fajr = sharedPreferences.getBoolean(PrayerTime.FAJR.toString(), true);
	   Boolean sunrise = sharedPreferences.getBoolean(PrayerTime.SUNRISE.toString(), true);
	   Boolean zuhr = sharedPreferences.getBoolean(PrayerTime.ZUHR.toString(), true);
	   Boolean asr = sharedPreferences.getBoolean(PrayerTime.ASR.toString(), true);
	   Boolean maghrib = sharedPreferences.getBoolean(PrayerTime.MAGHRIB.toString(), true);
	   Boolean isha = sharedPreferences.getBoolean(PrayerTime.ISHA.toString(), true);
	   
	   fajrButton.setChecked(fajr);
	   sunriseButton.setChecked(sunrise);
	   zuhrButton.setChecked(zuhr);
	   asrButton.setChecked(asr);
	   maghribButton.setChecked(maghrib);
	   fajrButton.setChecked(fajr);
	   Log.v(TAG,"Settings: " + fajr + "/" + sunrise + "/ " + zuhr + "/" + asr + "/" + maghrib  + "/ " +  isha);
	    
	   }
	
	/**
	 * Responds to Changes of state in toggle buttons
	 */
	OnCheckedChangeListener listener = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked) {
            if(toggleButton == fajrButton){
            	SavePreferences(PrayerTime.FAJR.toString(), isChecked);
            }else if(toggleButton == sunriseButton){
            	SavePreferences(PrayerTime.SUNRISE.toString(), isChecked);
            }else if(toggleButton == zuhrButton){
            	SavePreferences(PrayerTime.ZUHR.toString(), isChecked);
            }else if(toggleButton == asrButton){
            	SavePreferences(PrayerTime.ASR.toString(), isChecked);
            }else if(toggleButton == maghribButton){
            	SavePreferences(PrayerTime.MAGHRIB.toString(), isChecked);
            }else if(toggleButton == ishaButton){
            	SavePreferences(PrayerTime.ISHA.toString(), isChecked);
            }
            LoadPreferences();
        }
	};
	
	/**
	 * Returns to Main Activity and updates Interface with new settings values
	 */
	public void updateSettings(View v){
		
		Intent returnIntent = new Intent();
		 setResult(RESULT_OK,returnIntent);        
		    finish();
	}
	
}
