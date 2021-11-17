package com.example.optumhackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        Button login = (Button) findViewById(R.id.login);

        Intent intent1=getIntent();
        String type=intent1.getStringExtra("type");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (type.equals("0")) {
                    Intent intent = new Intent(login2.this,doctorActivity.class);
                    startActivity(intent);
                    finish();
                }

                else{
                    Intent intent = new Intent(login2.this,mainscreen.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
