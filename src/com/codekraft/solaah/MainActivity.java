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
import android.widget.SimpleCursorAdapter;

import com.codekraft.data.Constants;
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
		//Refactor by using loop
		Cursor cursor = myDbHelper.getTimingsForDate(date);
		cursor.moveToFirst();
		
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>(); 
		String time = "";
		
        time = cursor.getString(cursor.getColumnIndex(Constants.FAJR) );
        map.put("salaah", Constants.FAJR);
        map.put("time", time);
        list.add(map);
        map = new HashMap<String, String>();
        
        time = cursor.getString(cursor.getColumnIndex(Constants.SUNRISE) );        
        map.put("salaah", Constants.SUNRISE);
        map.put("time", time);
        list.add(map);
        map = new HashMap<String, String>();
        
        time = cursor.getString(cursor.getColumnIndex(Constants.ZUHR) );
        map.put("salaah", Constants.ZUHR);
        map.put("time", time);
        list.add(map);
        map = new HashMap<String, String>();
        
        time = cursor.getString(cursor.getColumnIndex(Constants.ASR) );
        map.put("salaah", Constants.ASR);
        map.put("time", time);
        list.add(map);
        map = new HashMap<String, String>();
        
        time = cursor.getString(cursor.getColumnIndex(Constants.MAGHRIB) );
        map.put("salaah", Constants.MAGHRIB);
        map.put("time", time);
        list.add(map);
        map = new HashMap<String, String>();
        
        time = cursor.getString(cursor.getColumnIndex(Constants.ISHA) );
        map.put("salaah", Constants.ISHA);
        map.put("time", time);
        list.add(map);
        
		return list;
	}
}