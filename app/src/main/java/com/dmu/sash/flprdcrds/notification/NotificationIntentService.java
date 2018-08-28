package com.dmu.sash.flprdcrds.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.support.v4.app.NotificationManagerCompat;

import com.dmu.sash.flprdcrds.MainActivity;
import com.dmu.sash.flprdcrds.R;

public class NotificationIntentService extends JobIntentService {
    public static final int REQUEST_CODE = 100;
    private static final int NOTIFICATION_ID = 1;
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Notification.Builder notificationBuilder = new Notification.Builder(this)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_msg))
                .setSmallIcon(R.drawable.baseline_school_black_24dp)
                .setAutoCancel(true);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(pendingIntent);
        Notification notification = notificationBuilder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notification);
    }
}
