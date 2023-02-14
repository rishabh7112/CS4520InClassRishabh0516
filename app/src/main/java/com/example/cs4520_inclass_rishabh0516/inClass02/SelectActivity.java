// Rishabh Sahu
// Assignment #2
package com.example.cs4520_inclass_rishabh0516.inClass02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.cs4520_inclass_rishabh0516.R;

public class SelectActivity extends AppCompatActivity {

    private ImageButton avatar1;
    private ImageButton avatar2;
    private ImageButton avatar3;
    private ImageButton avatar4;
    private ImageButton avatar5;
    private ImageButton avatar6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        setTitle("Select Avatar");
        avatar1 = findViewById(R.id.fragement_avatar1);
        avatar2 = findViewById(R.id.avatar2);
        avatar3 = findViewById(R.id.avatar3);
        avatar4 = findViewById(R.id.avatar4);
        avatar5 = findViewById(R.id.avatar5);
        avatar6 = findViewById(R.id.avatar6);

        avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int drawable = avatar1.getId();
                Intent toEdit = new Intent();
                toEdit.putExtra("ToEdit", R.drawable.avatar_f_1);
                setResult(RESULT_OK, toEdit);
                finish();
            }
        });
        avatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toEdit = new Intent();
                toEdit.putExtra("ToEdit", R.drawable.avatar_m_3);
                setResult(RESULT_OK, toEdit);
                finish();
            }
        });

        avatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toEdit = new Intent();
                toEdit.putExtra("ToEdit", R.drawable.avatar_f_2);
                setResult(RESULT_OK, toEdit);
                finish();
            }
        });

        avatar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toEdit = new Intent();
                toEdit.putExtra("ToEdit", R.drawable.avatar_m_2);
                setResult(RESULT_OK, toEdit);
                finish();
            }
        });

        avatar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int drawable = avatar1.getId();
                Intent toEdit = new Intent();
                toEdit.putExtra("ToEdit", R.drawable.avatar_f_3);
                setResult(RESULT_OK, toEdit);
                finish();
            }
        });

        avatar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int drawable = avatar1.getId();
                Intent toEdit = new Intent();
                toEdit.putExtra("ToEdit", R.drawable.avatar_m_1);
                setResult(RESULT_OK, toEdit);
                finish();
            }
        });

    }
}