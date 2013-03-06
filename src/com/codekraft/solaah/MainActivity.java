package com.codekraft.solaah;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.codekraft.data.Constants;
import com.codekraft.data.PrayerTime;
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
		ArrayList<HashMap<String, String>> list = getPrayerTimesForDate(today);
		Log.i(list.toString(), TAG);

		ListView listView = (ListView) findViewById(R.id.listView1);
		
		SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		list,
        		R.layout.row,
        		new String[] {"salaah","time"},
        		new int[] {R.id.salah, R.id.timeView}
        		);
		
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

	private ArrayList<HashMap<String, String>> getPrayerTimesForDate(String date) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		Cursor cursor = myDbHelper.getTimingsForDate(date);
		cursor.moveToFirst();
		
		for(PrayerTime p : PrayerTime.values()){
			HashMap<String, String> map = new HashMap<String, String>();
			String entry = p.toString();
			String time = cursor.getString(cursor.getColumnIndex(entry));
			map.put("salaah", entry);
	        map.put("time", time);
			list.add(map);
		}
        
		return list;
	}
}