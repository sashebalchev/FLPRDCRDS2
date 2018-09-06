package com.dmu.sash.flprdcrds.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.dmu.sash.flprdcrds.helpers.PreferencesManager;

import java.util.Calendar;

public class NotificationAlarmSetter {

    private Context context;

    public NotificationAlarmSetter(Context context) {
        this.context = context;
    }

    //TODO Set notification channel for SDK 26+.
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
            PreferencesManager preferencesManager = new PreferencesManager(context);
            preferencesManager.setNotificationFlag(true);
        }
    }
}
