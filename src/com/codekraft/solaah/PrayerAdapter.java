package com.codekraft.solaah;

import android.content.Context;
import android.widget.ArrayAdapter;

public class PrayerAdapter extends ArrayAdapter<String>{
	
	private final Context context;
	private final String[] parameters;
	
	public PrayerAdapter(Context context, String[] parameters) {
		super(context, R.layout.row, parameters);
		this.context = context;
		this.parameters = parameters;
		
	}
}
