package com.codekraft.solaah;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.codekraft.data.SQLDbAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SQLDbAdapter myDbHelper = new SQLDbAdapter(this);
		myDbHelper.getReadableDatabase();
		// myDbHelper.openDataBase();

		// Change to getReadableDatabase
		// try {
		// myDbHelper.openDataBase();
		// } catch (SQLException sqle) {
		// throw sqle;
		// }
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
	public String getDateToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String currentDateandTime = sdf.format(new Date());
		
		return "";
	}

}