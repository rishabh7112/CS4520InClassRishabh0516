package com.example.cs4520_inclass_rishabh0516;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;

import java.io.Serializable;

public class SelectActivity extends AppCompatActivity {

    private ImageButton avatar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        setTitle("Select Avatar");
        avatar1 = findViewById(R.id.avatar1);

        avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int drawable = avatar1.getId();
                Intent toEdit = new Intent();
                toEdit.putExtra("ToEdit", R.drawable.avatar_f_1);
                setResult(RESULT_OK, toEdit);
                finish();
            }
        });

    }
}