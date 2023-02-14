// Rishabh Sahu
// Assignment # 1
package com.example.cs4520_inclass_rishabh0516;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cs4520_inclass_rishabh0516.inClass01.InClass01;
import com.example.cs4520_inclass_rishabh0516.inClass02.EditProfileActivity;
import com.example.cs4520_inclass_rishabh0516.inClass03.InClass03;

public class MainActivity extends AppCompatActivity {
    private Button button_practice;
    private Button button_inclass1;

    private Button button_inclass2;
    private Button button_inclass3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_practice = findViewById(R.id.button_practice);
        button_inclass1 = findViewById(R.id.button_inclass1);
        button_inclass2 = findViewById(R.id.button_inclass2);
        button_inclass3 = findViewById(R.id.button_inclass3);

        button_practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PracticeActivity.class);
                startActivity(intent);
            }
        });

        button_inclass1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InClass01.class);
                startActivity(intent);
            }
        });

        button_inclass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        button_inclass3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InClass03.class);
                startActivity(intent);
            }
        });
    }
}