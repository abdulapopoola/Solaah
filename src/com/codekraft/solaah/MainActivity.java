package com.codekraft.solaah;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codekraft.data.Constants;
import com.codekraft.data.PrayerTime;
import com.codekraft.data.SQLDbAdapter;

public class MainActivity extends Activity {
	private static final String TAG = "salaahApp";
	private SQLDbAdapter myDbHelper;
	protected String[] from;
	protected int[] to;
	private ArrayList<HashMap<String, String>> list;
	private ArrayList<String> timeList;
	private ArrayAdapter<String> adapter;
	private static final int SETTINGS_REQUEST = 0;
	private static int LAST_ID = 123;
//	private String[] timeList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try {
			myDbHelper = new SQLDbAdapter(this);
		} catch (IOException e) {
			Log.e("DB FAILED", TAG);
		}
		String today = Utilities.getCurrentDateOrTime(Constants.DB_DATE_FORMAT);
		list = new ArrayList<HashMap<String, String>>();
		timeList = new ArrayList<String>();
		setupData(today);

		setupDisplay();
	}

	private void setupDisplay() {
		TextView textView = (TextView) findViewById(R.id.dateTextView);
		String date = Utilities
				.getCurrentDateOrTime(Constants.DISPLAY_DATE_FORMAT);
		textView.setText(date);
		String nextSalaah = getNextSalaah();

		textView = (TextView) findViewById(R.id.nextSalaahtextView);
		textView.setText("Next Salaah: " + nextSalaah);

		ListView listView = (ListView) findViewById(R.id.timesList);
		adapter = new PrayerAdapter(this, timeList);
		
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	 public boolean onOptionsItemSelected(MenuItem item) {
		 Log.v(TAG,"Item " + item.getItemId() +":  " + R.id.menu_settings);
		    if (item.getItemId() == R.id.menu_settings) {
		    	 Log.v(TAG,"Works");
		    	showSettings();
		        return true;
		    }
		    return false;
		}
	
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 if(requestCode == SETTINGS_REQUEST && resultCode == RESULT_OK){
			 Log.v(TAG,"Update Main UI");
			 adapter.notifyDataSetChanged();
		 }
			 
	 }
	 
	 /**
	  * Show Athan Settings
	  */
	private void showSettings() {
		Intent intent = new Intent(this, PrayerSettings.class);
		startActivityForResult(intent, 0);
	}
	
	
	private void setupData(String date) {
		Cursor cursor = myDbHelper.getTimingsForDate(date);
		cursor.moveToFirst();
		
		for (PrayerTime p : PrayerTime.values()) {
			HashMap<String, String> map = new HashMap<String, String>();
			String entry = p.toString();
			String time = cursor.getString(cursor.getColumnIndex(entry));
			map.put(Constants.SALAAH_KEY, entry);
			map.put(Constants.TIME_KEY, time);
			list.add(map);
			/*Populate ArrayList for Adapter*/
			timeList.add(time);

		}

	}

	private String getNextSalaah() {
		SimpleDateFormat df = new SimpleDateFormat(Constants.DB_TIME_FORMAT, Locale.US);
		String timeNow = Utilities.getCurrentDateOrTime(Constants.DB_TIME_FORMAT);
		boolean nextSalaahFound = false;
		String nextPrayer = "";
		Date now;

		for (HashMap<String, String> map : list) {
			String time = map.get(Constants.TIME_KEY);
			try {
				now = df.parse(timeNow);
				Date salaahTime = df.parse(time);
				if (salaahTime.compareTo(now) > 0 && !nextSalaahFound) {
					nextPrayer = map.get(Constants.SALAAH_KEY);
					nextSalaahFound = true;
                    /*Set Alarm for all times except Sunrise*/
                     setAlarm(nextPrayer,time);

					break;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return nextPrayer;
	}
	
	/**
	  * Increases the ID for the Alarm PendingIntent
	  */
	private void updateIntentID(){
		
		LAST_ID += 1;
		Log.v(TAG,"NExt ID is " + LAST_ID);
		
	}
	private void setAlarm( String prayerTime, String timeString){
		
		/* Parse String to Time */
		String[] timing;
		timing = timeString.split(" ");
		String[] hourMinute = timing[0].split(":");
		Time time = new Time();
		time.hour = Integer.parseInt(hourMinute[0]);
		time.minute = Integer.parseInt(hourMinute[1]);
		
		int AmPm = (timing[1].equals("AM"))? 0:1;
		Log.v(TAG, "Time is " + hourMinute[0] + ": " + hourMinute[1] + " " + AmPm);
		Calendar calendar = Calendar.getInstance();
		Log.v(TAG,"Now Time:" + calendar.getTime());
		calendar.set(Calendar.HOUR, time.hour);
		calendar.set(Calendar.MINUTE, time.minute);
		calendar.set(Calendar.AM_PM, AmPm);
		Log.v(TAG,"Updated Time: " +  calendar.getTime());
		
		Intent intent = new Intent(getApplicationContext(), SalaahBackgroundService.class);
	    intent.putExtra("PrayerTime", prayerTime);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), LAST_ID, intent, 0);
	    updateIntentID();
	    
	    AlarmManager am=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
	    am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
	    Toast.makeText(MainActivity.this, "Start Alarm!", Toast.LENGTH_LONG).show();
	}

}