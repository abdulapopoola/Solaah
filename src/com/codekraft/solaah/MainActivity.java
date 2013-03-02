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
		
		//From is the column name in your cursor where you're getting the data
		//to is the id of the view it will map to
		from = new String[]{"Month"};
		to = new int[]{R.id.text1};
		
		Cursor cursor = myDbHelper.getTimingsForDate(today);
		CustomCursorAdapter adapter = new CustomCursorAdapter(this, R.layout.solaah_row, cursor, from, to);
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

	private String getPrayerTimesForDate(String date)
	{
		Cursor cursor = myDbHelper.getTimingsForDate(date);
		cursor.moveToFirst();
		//int colCount = cursor.getColumnCount();		
		String var1 = cursor.getString(3);
		
		
		
		return "";
	}
	
	protected class CustomCursorAdapter extends SimpleCursorAdapter  {
        private int layout; 
        private LayoutInflater inflater;
        private Context context;

        public CustomCursorAdapter (Context context, int layout, Cursor c, String[] from, int[] to) {
            super(context, layout, c, from, to);
            this.layout = layout;
            this.context = context;
            inflater = LayoutInflater.from(context);

        }


        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            Log.i("NewView", "TUNTUTN");

            View v = inflater.inflate(R.layout.solaah_row, parent, false);

            return v;
        }

        @Override
        public void bindView(View v, Context context, Cursor c) {
                    //1 is the column where you're getting your data from
            String name = c.getString(1);
            /**
             * Next set the name of the entry.
             */
            TextView name_text = (TextView) v.findViewById(R.id.text1);
            if (name_text != null) {
                name_text.setText(name);
            }   
        }
	
//	private void fillData(String date)
//	{
//        // Get all of the notes from the database and create the item list
//        Cursor c = myDbHelper.getTimingsForDate(date);
//        startManagingCursor(c);
//
//        String[] from = new String[] { "Fajr" };
//        int[] to = new int[] { R.id.text1 };
//        
//        // Now create an array adapter and set it to display using our row
//        SimpleCursorAdapter notes =
//            new SimpleCursorAdapter(this, R.layout.solaah_row, c, from, to);
//        setListAdapter(notes);
//    }
	}
}