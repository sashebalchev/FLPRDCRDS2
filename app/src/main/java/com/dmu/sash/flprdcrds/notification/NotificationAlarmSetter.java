package com.dmu.sash.flprdcrds.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.dmu.sash.flprdcrds.settings.PreferencesProvider;

import java.util.Calendar;

public class NotificationAlarmSetter {

    private SharedPreferences preferences;
    private Context context;

    public NotificationAlarmSetter(Context context) {
        this.context = context;
        preferences = new PreferencesProvider(context).getSharedPreferences();
    }

    public void setAlarm() {
        Intent notifyIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                NotificationIntentService.REQUEST_CODE, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("FIRST_TIME", true);
            editor.apply();
        }
    }
}
