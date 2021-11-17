package com.example.optumhackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class doctorActivity extends AppCompatActivity {

    TextView timep;
    EditText medicine,dosage;
    Button missed,reward;
    int hour,min;

    databaseHandler db;
    String storeTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        timep=findViewById(R.id.timeID);
        timep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        doctorActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hour = hourOfDay;
                                min =minute;
                                String timeStr = hour+":"+min;
                                @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = df.parse(timeStr);
                                    @SuppressLint("SimpleDateFormat") SimpleDateFormat fd12 = new SimpleDateFormat(
                                            "hh:mm:aa"
                                    );
                                    assert date != null;
                                    storeTime=fd12.format(date);
                                    timep.setText(fd12.format(date));
                                }
                                catch (ParseException e){
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(hour,min);
                timePickerDialog.show();
            }
        });
        reward=findViewById(R.id.doctorCheckreward);
        missed=findViewById(R.id.noofdaysmissed);
        Button set = findViewById(R.id.setdata);
        db=new databaseHandler(doctorActivity.this);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicine=findViewById(R.id.doctorMedicineName);
                dosage=findViewById(R.id.doctorDosageLevel);
                String medName = medicine.getText().toString();
                String doslevel = dosage.getText().toString();
                System.out.println(storeTime+"---------"+medName+"---------"+doslevel+"-----------");
                boolean r=db.insertData(medName,doslevel,storeTime,"0","0");
                if (r){
                    Toast.makeText(doctorActivity.this,"success",Toast.LENGTH_LONG).show();
            }}
        });

        reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder buff = new StringBuilder();
                Cursor data = db.getdata();
                if(data.getCount()<0){
                    Toast.makeText(doctorActivity.this,"failed",Toast.LENGTH_LONG).show();
                }
                else{
                    int i=0;
                while(data.moveToNext()){
                    if(i==0)
                        buff.append(data.getString(0)).append(":").append(data.getString(3)).append("\n").toString();
                    i++;
                }}
                AlertDialog.Builder builder = new AlertDialog.Builder(doctorActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Current reward:");
                builder.setMessage(buff);
                builder.show();
            }
        });

        missed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder buff = new StringBuilder();
                Cursor data = db.getdata();
                if(data.getCount()<0){
                    Toast.makeText(doctorActivity.this,"failed",Toast.LENGTH_LONG).show();
                }
                else{
                    int i=0;
                    while(data.moveToNext()){
                        if(i==0)
                            buff.append(data.getString(0)).append(":").append(data.getString(4)).append("\n").toString();
                        i++;
                    }
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(doctorActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Missed days:");
                builder.setMessage(buff);
                builder.show();            }
        });

    }
}