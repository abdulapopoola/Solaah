package com.codekraft.solaah;

import com.codekraft.data.Settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

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
	}
	
	/**
	 * Responds to Changes of state in toggle buttons
	 */
	OnCheckedChangeListener listener = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked) {
            if(toggleButton == fajrButton){
            	new Settings().setFajr(isChecked);
            	Log.v(TAG, "Fajr is " + Settings.Fajr);
            }else if(toggleButton == sunriseButton){
            	new Settings().setSunrise(isChecked);
            	Log.v(TAG, "Sunrise is " + Settings.Sunrise);
            }else if(toggleButton == zuhrButton){
            	new Settings().setZuhr(isChecked);
            	Log.v(TAG, "Zuhr is " + Settings.Zuhr);
            }else if(toggleButton == asrButton){
            	new Settings().setAsr(isChecked);
            	Log.v(TAG, "Asr is " + Settings.Asr);
            }else if(toggleButton == maghribButton){
            	new Settings().setMaghrib(isChecked);
            	Log.v(TAG, "Maghrib is " + Settings.Maghrib);
            }else if(toggleButton == ishaButton){
            	new Settings().setIsha(isChecked);
            	Log.v(TAG, "Isha is " + Settings.Isha);
            }
            
        }
	};
	
	/**
	 * Returns to Main Activity and updates Interface with new settings values
	 */
	public void updateSettings(){
		
		Intent returnIntent = new Intent();
		 setResult(RESULT_OK,returnIntent);        
		    finish();
	}
	
}
