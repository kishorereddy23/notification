package com.kishore.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedInputStream;

public class MainActivity extends AppCompatActivity {
    NotificationManager nm;
    NotificationCompat.Builder builder;
    public static final int NOTIFICATION_ID = 3323;
    public static final String CHANNNEL_ID = "WELCOME";
    PendingIntent pi;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = new Intent(this, MainActivity.class);
        pi = PendingIntent.getActivity(this, NOTIFICATION_ID, i, PendingIntent.FLAG_UPDATE_CURRENT);
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createChannel();
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNNEL_ID, "KISHORE", NotificationManager.IMPORTANCE_HIGH);
            channel.setLightColor(Color.BLUE);
            channel.enableVibration(true);
            nm.createNotificationChannel(channel);
        }
    }

    public void notify(View view) {
        builder = new NotificationCompat.Builder(this,CHANNNEL_ID);
        builder.setContentTitle("welcome to all");
        builder.setContentText("notification");
        builder.addAction(R.drawable.ic_launcher_background,"reply",pi);
        builder.setPriority(Notification.PRIORITY_HIGH);
        builder = builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setContentIntent(pi);
        nm.notify(NOTIFICATION_ID, builder.build());

    }
}