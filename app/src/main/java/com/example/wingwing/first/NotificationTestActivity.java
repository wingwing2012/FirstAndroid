package com.example.wingwing.first;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;


public class NotificationTestActivity extends Activity {

    static final int NOTIFICATION_ID = 0x123;
    NotificationManager mNM = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void send(View view) {
        Intent intent = new Intent(NotificationTestActivity.this, OtherActivity.class);
        PendingIntent pi = PendingIntent.getActivities(NotificationTestActivity.this, 0, new Intent[]{intent}, 0);
        Notification notify = new Notification.Builder(this)
                .setAutoCancel(true)
                .setTicker("有新消息")
                .setSmallIcon(R.drawable.p)
                .setContentTitle("有一条新通知")
                .setContentText("恭喜你，您加薪了，工资增加20%！")
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pi)
                .build();
        mNM.notify(NOTIFICATION_ID, notify);
    }

    public void cancel(View view) {
        mNM.cancel(NOTIFICATION_ID);
    }
}
