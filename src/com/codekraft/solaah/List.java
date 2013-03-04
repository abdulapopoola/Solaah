package com.codekraft.solaah;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class List extends Activity {
	
	public static final String TAG = "Prayer Times 1.0";
	private ListView lv;
	private ArrayList<HashMap<String, String>> list = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<HashMap<String,String>>(); 
        lv = (ListView) findViewById(R.id.listView1);
       
		populateList();
        SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		list,
        		R.layout.row,
        		new String[] {"salah","hour"},
        		new int[] {R.id.salah, R.id.timeView}
        		);
       // populateList();
        lv.setAdapter(adapter);
    }
    
    public class Info{
 	   String id, name, comment;
 	
    }
    

    private void populateList() {
    	HashMap<String, String> map = new HashMap<String, String>();
        map.put("salah", "Fajr");
        map.put("hour", "13:15");
        list.add(map);
        map = new HashMap<String, String>();
        map.put("salah", "Sunrise");
        map.put("hour", "14:06");
        list.add(map); 
        map.put("salah", "Zuhr");
        map.put("hour", "16:15");
        list.add(map); 
        map.put("salah", "Asr");
        map.put("hour", "18:15");
        list.add(map);
    }
}