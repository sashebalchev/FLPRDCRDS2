package com.dmu.sash.flprdcrds.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, NotificationIntentService.class);
        context.startService(intent1);
    }
}
