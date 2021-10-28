package com.example.doongdoong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ImageButton btn_check_code = (ImageButton) findViewById(R.id.btn_check_code);
        btn_check_code.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), setting_code.class);
                startActivity(intent);

                switch (view.getId()) {
                    case R.id.btn_check_code:
                        btn_check_code.setSelected(true);
                }
            }
        });

        ImageButton btn_edit_info = (ImageButton) findViewById(R.id.btn_edit_info);
        btn_edit_info.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), setting_info.class);
                startActivity(intent);

                switch (view.getId()) {
                    case R.id.btn_check_code:
                        btn_edit_info.setSelected(true);
                }
            }
        });

        ImageButton btn_edit_record= (ImageButton) findViewById(R.id.btn_edit_basic_record);
        btn_edit_record.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), setting_default.class);
                startActivity(intent);

                switch (view.getId()) {
                    case R.id.btn_edit_basic_record:
                        btn_edit_record.setSelected(true);
                }
            }
        });

    }
}