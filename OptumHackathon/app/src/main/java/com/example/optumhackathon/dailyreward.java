package com.example.optumhackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class dailyreward extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailyreward);
        //balance
        databaseHandler db=new databaseHandler(dailyreward.this);
        StringBuilder buff = new StringBuilder();
        Cursor data = db.getdata();
        if(data.getCount()<0){
            Toast.makeText(dailyreward.this,"failed",Toast.LENGTH_LONG).show();
        }
        else{
            int i=0;
            while(data.moveToNext()){
                if(i==0)
                    buff.append(data.getString(3)).toString();
                i++;
            }}
        TextView balance=findViewById(R.id.balance);
        balance.setText(buff+"$");
        TextView curdate=findViewById(R.id.curdate);
        Date c= Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        curdate.setText(simpleDateFormat.format(c));
//        AlertDialog.Builder builder = new AlertDialog.Builder(dailyreward.this);
//        builder.setCancelable(true);
//        builder.setTitle("Current reward:");
//        builder.setMessage(buff);
//        builder.show();
    }
}