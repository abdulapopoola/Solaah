package com.codekraft.solaah;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.codekraft.data.Constants;
import com.codekraft.data.PrayerTime;
import com.codekraft.data.SQLDbAdapter;

public class MainActivity extends Activity {
	private static final String TAG = "salaahApp";
	private SQLDbAdapter myDbHelper;
	protected String[] from;
	protected int[] to;
	private ArrayList<HashMap<String, String>> list;
	private String nextSalaah = "";

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
		setupData(today);

		setupDisplay();
	}

	private void setupDisplay() {
		TextView textView = (TextView) findViewById(R.id.dateTextView);
		String date = Utilities
				.getCurrentDateOrTime(Constants.DISPLAY_DATE_FORMAT);
		textView.setText(date);

		textView = (TextView) findViewById(R.id.nextSalaahtextView);
		textView.setText(nextSalaah);

		ListView listView = (ListView) findViewById(R.id.timesList);
		SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.row,
				new String[] { "salaah", "time" }, new int[] { R.id.salah,
						R.id.timeView });

		listView.setAdapter(adapter);
		MyCount counter = new MyCount(5000,1000);
        counter.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void setupData(String date) {
		Cursor cursor = myDbHelper.getTimingsForDate(date);
		cursor.moveToFirst();

		SimpleDateFormat df = new SimpleDateFormat(Constants.DB_TIME_FORMAT,
				Locale.US);
		String timeNow = Utilities
				.getCurrentDateOrTime(Constants.DB_TIME_FORMAT);
		boolean nextSalaahFound = false;
		Date now;

		for (PrayerTime p : PrayerTime.values()) {
			HashMap<String, String> map = new HashMap<String, String>();
			String entry = p.toString();
			String time = cursor.getString(cursor.getColumnIndex(entry));
			map.put("salaah", entry);
			map.put("time", time);
			list.add(map);

			try {
				now = df.parse(timeNow);
				Date salaahTime = df.parse(time);
				if (salaahTime.compareTo(now) > 0 && !nextSalaahFound) {
					nextSalaah = entry;
					nextSalaahFound = true;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public class MyCount extends CountDownTimer {
		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			// Setup counter to next salaah -> maybe I should pull it out into another utility method that returns next for a given time
			TextView textView = (TextView) findViewById(R.id.nextSalaahtextView);
			textView.setText("done");
		}

		@Override
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
			TextView textView = (TextView) findViewById(R.id.nextSalaahtextView);
			textView.setText(Long.toString(millisUntilFinished/1000));
		}
	}
}