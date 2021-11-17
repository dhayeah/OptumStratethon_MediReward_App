package com.example.optumhackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button doc,pat;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        Calendar calendar= Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY,18);
//        calendar.set(Calendar.MINUTE,51);
//        calendar.set(Calendar.SECOND,0);
//
//        Intent intent =new Intent(getApplicationContext(),Notification_reciever.class);
//        intent.setAction("hELLO");
//        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);



        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE,5);
        Intent intent = new Intent(this, NotificationReceiver.class);
        // intent.putExtra("this", (Parcelable) MainActivity.this);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        long time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
        if (System.currentTimeMillis() > time) {
            // setting time as AM and PM
            if (Calendar.AM_PM == 0)
                time = time + (1000 * 60 * 60 * 12);
            else
                time = time + (1000 * 60 * 60 * 24);
        }
        AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pendingIntent);

//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);



        doc= (Button) findViewById(R.id.doctor);
        pat= (Button) findViewById(R.id.patient);
        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,login2.class);
                intent.putExtra("type","0");
                startActivity(intent);
            }
        });
        pat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,login2.class);
                intent.putExtra("type","1");
                startActivity(intent);
            }
        });

    }
    }
