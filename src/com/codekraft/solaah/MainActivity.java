package com.codekraft.solaah;

import com.codekraft.data.SQLDbAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.database.SQLException;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SQLDbAdapter myDbHelper = new SQLDbAdapter(this);
		myDbHelper = new SQLDbAdapter(this);

		//Change to getReadableDatabase
		try {
			myDbHelper.openDataBase();
		} catch (SQLException sqle) {
			throw sqle;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}