package com.codekraft.solaah;

import java.util.ArrayList;

import com.codekraft.data.PrayerTime;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PrayerAdapter extends ArrayAdapter<String>{
	
	private final Context context;
	private final  ArrayList<String> timeList;
	private final PrayerTime[] prayers;
	
	public PrayerAdapter(Context context, ArrayList<String> timeList) {
		super(context, R.layout.row, timeList);
		this.context = context;
		this.timeList = timeList;
		
		prayers = PrayerTime.values();
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row, null, true);
		TextView salahView = (TextView) rowView.findViewById(R.id.salah);
		TextView timeView = (TextView) rowView.findViewById(R.id.timeView);
		ImageView iconView = (ImageView) rowView.findViewById(R.id.alarmIcon);
		
		PrayerTime prayerTime = prayers[position];
		salahView.setText(prayerTime.toString());
		
		String time = timeList.get(position);
		timeView.setText(time);
		
		
		SharedPreferences sharedPreferences = context.getSharedPreferences("SETTINGS_PREF", Context.MODE_PRIVATE);
		Boolean alarmBool = sharedPreferences.getBoolean(prayerTime.toString(), true);
		if(alarmBool)
			iconView.setVisibility(View.VISIBLE);
		else
			iconView.setVisibility(View.GONE);
		
		return rowView;
		
	}
}
