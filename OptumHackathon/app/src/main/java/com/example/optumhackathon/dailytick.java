package com.example.optumhackathon;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class dailytick extends AppCompatActivity {

    int flag=0;
    CheckBox checkBox;
    TextView time,medicine,dosage,curTime;
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailytick);
        Calendar calendar= GregorianCalendar.getInstance();
        long hour=calendar.get(Calendar.HOUR);
        //Toast.makeText(dailytick.this,String.valueOf(hour),Toast.LENGTH_LONG).show();
        databaseHandler db=new databaseHandler(dailytick.this);
        String buff = new String();
        String m="",d="";
        Cursor data = db.getdata();
        if(data.getCount()<0){
            Toast.makeText(dailytick.this,"failed",Toast.LENGTH_LONG).show();
        }
        else{
            int i=0;
            while(data.moveToNext()){
//                if(i==0) {
//                    buff.append(data.getString(2)).toString();
//                    System.out.println(data.getString(0)+"+"+data.getString(1)+"+"+data.getString(2)+"+"+data.getString(3)+"+"+data.getString(4));
//                }
//                i++;
//                System.out.println( buff.append(data.getString(2)).toString());
                buff=data.getString(2);
                m=data.getString(0);
                d=data.getString(1);
            }}
        //Toast.makeText(dailytick.this,buff,Toast.LENGTH_LONG).show();


        medicine=findViewById(R.id.medicineName);
        dosage=findViewById(R.id.dosagelevel);
        medicine.setText(m);
        dosage.setText(d+"mg/day");
        curTime=findViewById(R.id.curtime);
        LocalTime localTime=LocalTime.now();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("hh:mm a");
        String temp1=localTime.format(dateTimeFormatter);
//        for(int i=0;i<temp1.length();i++){
//            System.out.println(temp1.charAt(i));
//        }
//        System.out.println(temp1.length()+"--"+temp1);
//        temp1=buff.toString();
//        System.out.println("---------------------------------------------------------------------------------------");
//        for (int i = 0; i < temp1.length(); i++) {
//            System.out.println(temp1.charAt(i));
//        }
//        System.out.println(temp1.length()+"--"+temp1);

        StringBuilder temp=new StringBuilder();
        temp.append(temp1.charAt(0)).append(temp1.charAt(1)).append(temp1.charAt(temp1.length()-1)).append(temp1.charAt(temp1.length()-2));
     //   System.out.println(temp.toString());


        checkBox=findViewById(R.id.checked);
        StringBuilder temp2=new StringBuilder();
        temp2.append(buff.charAt(0)).append(buff.charAt(1)).append(buff.charAt(temp1.length()-1)).append(buff.charAt(buff.length()-2));
      //  System.out.println(temp2.toString());
        TextView message=findViewById(R.id.message);
        if(temp.toString().equals(temp2.toString())){
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setEnabled(true);
            message.setVisibility(View.INVISIBLE);
        }
        else{
            checkBox.setVisibility(View.INVISIBLE);
            checkBox.setEnabled(false);
            message.setVisibility(View.VISIBLE);
        }
        curTime.setText("Current time :"+temp1);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(temp.toString().equals(temp2.toString())){
                  db.UpdateReward();
                }
            }
        });


    }
}