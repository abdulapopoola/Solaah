package com.codekraft.solaah;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.codekraft.data.SQLDbAdapter;

public class MainActivity extends Activity {
	private static final String TAG = "salaahApp";
	private SQLDbAdapter myDbHelper;
	protected String[] from;
	protected int[] to;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			myDbHelper = new SQLDbAdapter(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("DB FAILED", TAG);
		}

		String today = getTodayDate();
		getPrayerTimesForDate(today);

		// From is the column name in your cursor where you're getting the data
		// to is the id of the view it will map to
		from = new String[] { "Fajr", "Sunrise", "Zuhr", "Asr", "Maghrib", "Isha" };
		to = new int[] { 
				R.id.fajr_row,
				R.id.sunrise_row,
				R.id.zuhr_row,
				R.id.asr_row,
				R.id.maghrib_row,
				R.id.isha_row
		};
		
		Cursor cursor = myDbHelper.getTimingsForDate(today);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
			    this, 
			    R.layout.prayer_times_rows, 
			    cursor, 
			    from, 
			    to,
			    0);

		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
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

	private String getPrayerTimesForDate(String date) {
		Cursor cursor = myDbHelper.getTimingsForDate(date);
		cursor.moveToFirst();
		// int colCount = cursor.getColumnCount();
		String var1 = cursor.getString(3);

		return "";
	}
}