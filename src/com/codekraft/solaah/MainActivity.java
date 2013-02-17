package com.codekraft.solaah;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.codekraft.data.SQLDbAdapter;

public class MainActivity extends Activity {
	private static final String TAG = "salaahApp";
	private SQLDbAdapter myDbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myDbHelper = new SQLDbAdapter(this);
		myDbHelper.getReadableDatabase();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/**
	 * Gets today's date in this format: 2-Feb
	 * */
	private String getTodayDate() {
		Calendar c = Calendar.getInstance();
		
		SimpleDateFormat df = new SimpleDateFormat("d-MMM");
		String formattedDate = df.format(c.getTime());
		
		return formattedDate;
	}
	
	private String getPrayerTimesForDate(String date)
	{
		Cursor cursor = myDbHelper.getTimingsForDate(date);
		
		return "";
	}
	

}