package com.codekraft.solaah;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			myDbHelper = new SQLDbAdapter(this);
		} catch (IOException e) {
			Log.e("DB FAILED", TAG);
		}

		String today = getTodayDate(Constants.DB_DATE_FORMAT);
		ArrayList<HashMap<String, String>> list = getPrayerTimesForDate(today);
		
		TextView textView = (TextView) findViewById(R.id.dateTextView);
		String date = getTodayDate(Constants.DISPLAY_DATE_FORMAT);
		textView.setText(date);
				
		ListView listView = (ListView) findViewById(R.id.timesList);
		
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
	 * Gets today's date in specified format
	 * */
	private String getTodayDate(String format) {
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat(format);
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
			
			SimpleDateFormat dt = new SimpleDateFormat("hh:mm a");
			Date dat;
			try {
				dat = dt.parse(time);
				Log.i(dat.toString(), "TIME");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
        
		return list;
	}
}