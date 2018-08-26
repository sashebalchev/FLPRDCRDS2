package com.dmu.sash.flprdcrds.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DeviceBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() != null && intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            NotificationAlarmSetter notificationAlarmSetter = new NotificationAlarmSetter(context);
            notificationAlarmSetter.setAlarm();
        }
    }
}
