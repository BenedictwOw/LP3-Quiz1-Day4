package com.myapplicationdev.android.lp3quiz1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnBasic, btnPicture, btnInbox;
    int notificationID = 88;
    int requestCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBasic = (Button) findViewById(R.id.btnBasic);
        btnPicture = (Button) findViewById(R.id.btnNotification);
        btnInbox = (Button) findViewById(R.id.btnInboxStyle);

        final NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new
                    NotificationChannel("default", "Default Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);


            channel1.setDescription("Default Description");
            notificationManager.createNotificationChannel(channel1);

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel2 = new
                    NotificationChannel("default", "Marketing Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);


            channel2.setDescription("~1 notification per day");
            notificationManager.createNotificationChannel(channel2);

        }


        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        final PendingIntent pIntent = PendingIntent.getActivity (MainActivity.this, requestCode, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);



        btnBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "default");
                builder.setContentTitle("LP3 Quiz1");
                builder.setContentText("This is a simple");
                builder.setSmallIcon(android.R.drawable.btn_star_big_off);
                builder.setContentIntent(pIntent);
                builder.setAutoCancel(true);

                Notification n = builder.build();

                notificationManager.notify(notificationID, n);
                finish();
            }
        });

        btnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                PendingIntent pIntent = PendingIntent.getActivity
                        ( MainActivity.this, requestCode, intent,
                                PendingIntent.FLAG_CANCEL_CURRENT);
                Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.koala);
                NotificationCompat.BigTextStyle bigText = new
                        NotificationCompat.BigTextStyle();
                bigText.setBigContentTitle("This is Big Picture");
                bigText.bigText("Koala!");
                bigText.setSummaryText("I love Nina!");


                NotificationCompat.Builder builder = new
                        NotificationCompat.Builder(MainActivity.this, "default");
                builder.setContentTitle("This is Big Picture");
                builder.setContentText("Koala!");
                builder.setSmallIcon(android.R.drawable.btn_star_big_off);
                builder.setLargeIcon(picture);
                builder .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(picture)
                        .bigLargeIcon(null));
                builder.setContentIntent(pIntent);
                builder.setAutoCancel(true);

                Notification n = builder.build();

                notificationManager.notify(notificationID, n);
                finish();
            }
        });

        btnInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> al = new ArrayList<>();
                al.add("This is the first line");
                al.add("This is the second line");
                al.add("This is the third line");

                NotificationCompat.InboxStyle inboxS = new NotificationCompat.InboxStyle();
                for (String tmp : al){ //iterate the entire ArrayList object and extract each element into variable tmp
                    inboxS.addLine(tmp);  //do something to the tmp, which is to retrive the value and place into Notification Inbox Style
                };
                inboxS.setBigContentTitle("Inbox style");
                inboxS.setSummaryText("List of entries");

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "default");
                builder.setContentTitle("Inbox style");
                builder.setContentText("Expand to see content");
                builder.setStyle(new NotificationCompat.InboxStyle()
                .addLine(al.get(0)).addLine(al.get(1)).addLine(al.get(2)));
                builder.setSmallIcon(android.R.drawable.btn_star_big_off);
                builder.setContentIntent(pIntent);
                builder.setAutoCancel(true);

                Notification n = builder.build();

                notificationManager.notify(notificationID, n);
                finish();
            }
        });

    }
}
