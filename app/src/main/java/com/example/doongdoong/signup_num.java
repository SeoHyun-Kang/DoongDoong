package com.example.doongdoong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class signup_num extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_num);

        ImageButton btn_next = (ImageButton) findViewById(R.id.btn_disable);
        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), signup_info_child.class);
                startActivity(intent);

                switch (view.getId()) {
                    case R.id.btn_disable:
                        btn_next.setSelected(true);
                }
            }
        });

        ImageButton btn_child = (ImageButton) findViewById(R.id.child);
        btn_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.child:
                        btn_child.setSelected(true);
                        break;
                }
            }
        });
        ImageButton btn_children = (ImageButton) findViewById(R.id.children);
        btn_children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.children:
                        btn_children.setSelected(true);
                        break;
                }
            }
        });
    }
}