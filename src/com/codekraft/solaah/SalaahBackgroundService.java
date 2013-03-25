package com.codekraft.solaah;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;

public class SalaahBackgroundService extends BroadcastReceiver {
	private String TAG = "SalaahBackgroundService";
	private String prayerTime;
	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v(TAG,"Package Received");
		
		Bundle extras = intent.getExtras();
        if (extras != null ){
        	prayerTime = (String) extras.getCharSequence("PrayerTime");
        }
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "WAKEY WAKEY");
        wl.acquire();

       Notification noti = new Notification(R.drawable.adhan,
	    	     "Notification!",
	    	     System.currentTimeMillis());
	    	
	    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	    // Hide the notification after its selected
	    noti.flags |= Notification.FLAG_AUTO_CANCEL;

	    String notificationTitle = "Salaah!";
	    String notificationText = "It's time for " + prayerTime;
		Intent myIntent = new Intent(context, MainActivity.class);
	    PendingIntent pendingIntent
	      = PendingIntent.getActivity(context,
	        0, myIntent,
	        Intent.FLAG_ACTIVITY_NEW_TASK);
	    noti.defaults |= Notification.DEFAULT_SOUND;
	    noti.flags |= Notification.FLAG_AUTO_CANCEL;
	    noti.setLatestEventInfo(context,
	       notificationTitle,	
	       notificationText,
	       pendingIntent);
	    notificationManager.notify(123, noti);
       wl.release();
		
	}

}
