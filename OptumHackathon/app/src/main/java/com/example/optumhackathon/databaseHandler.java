package com.example.optumhackathon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class databaseHandler extends SQLiteOpenHelper{
    public databaseHandler(Context context){
        super(context,"description.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS medicine_description(Medicine Text,Dosage Text,Time Text,Reward Text,Missed Test)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS medicine_description");

    }

    public boolean insertData(String medicine,String dosage,String time,String reward,String missed){
        System.out.println(time+"--------*******-----------------------------");
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Medicine",medicine);
        contentValues.put("Dosage",dosage);
        contentValues.put("Time",time);
        contentValues.put("Reward",reward);
        contentValues.put("Missed",missed);
        long result = DB.insert("medicine_description",null,contentValues);
        return result != -1;
    }

    public void UpdateReward(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor data = getdata();
        String buff = new String();

        if(data.getCount()<0){
            buff="0";
        }
        else{
            while(data.moveToNext()){
                //buff.append(data.getString(3)).toString();
                buff=data.getString(3);
            }
        }


       // buff.append(data.getString(3)).toString();
        int rewards=Integer.parseInt(String.valueOf(buff))+1;
        String query="UPDATE medicine_description SET Reward ="+ String.valueOf(rewards);
        db.execSQL(query);
    }
    public void UpdateMissedDays(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor data = getdata();
        StringBuilder buff = new StringBuilder();
        if(data.getCount()<0){
            buff.append("1");
        }
        else{
            int i=0;
            while(i!=1){
                buff.append(data.getString(4)).toString();
                i++;}
        }

        // buff.append(data.getString(4)).toString();
        int missedDays=Integer.parseInt(String.valueOf(buff))+1;
        String query="UPDATE medicine_description SET Missed ="+ String.valueOf(missedDays);
        db.execSQL(query);
    }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from medicine_description ",null);
        return cursor;
    }

}

