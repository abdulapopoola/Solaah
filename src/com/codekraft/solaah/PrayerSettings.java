package com.codekraft.solaah;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ToggleButton;

public class PrayerSettings extends Activity{

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
        
	}
}
