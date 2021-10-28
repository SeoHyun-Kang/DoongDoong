package com.example.doongdoong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class signup_record_start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_record_start);

        ImageButton btn_next = (ImageButton) findViewById(R.id.btn_disable);
        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), signup_code.class);
                startActivity(intent);

                switch (view.getId()) {
                    case R.id.btn_disable:
                        btn_next.setSelected(true);
                }
            }
        });
        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), signup_info_child.class);
                startActivity(intent);
            }
        });


    }
}