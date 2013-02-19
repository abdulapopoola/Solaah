package com.codekraft.solaah;

import java.io.IOException;
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

		try {
			myDbHelper = new SQLDbAdapter(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("DB FAILED", TAG);
		}
		//myDbHelper.getReadableDatabase();

		String today = getTodayDate();
		getPrayerTimesForDate(today);
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
		int colCount = cursor.getColumnCount();
		Log.e(""+colCount, TAG);
		cursor.moveToFirst();
		String var1 = cursor.getString(3);
		Log.e(var1, TAG);
//		for (int i=0; i < colCount; i++) {
//			  String var1 = cursor.getString(i);
//			  Log.i(var1, "Hello");
//		}
		
		return "";
	}

}