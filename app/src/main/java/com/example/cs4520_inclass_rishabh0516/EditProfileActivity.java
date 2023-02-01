package com.example.cs4520_inclass_rishabh0516;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditProfileActivity extends AppCompatActivity {
    private ImageButton imageButton;
    private Button submit_button;

    private EditText textName;

    private EditText textEmail;

    private SeekBar moodBar;

    private ImageView mood;

    ActivityResultLauncher<Intent> startActivityForResult
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK) {
                imageButton = findViewById(R.id.imageButton);
                int drawable = result.getData().getIntExtra("ToEdit",0);
                imageButton.setImageResource(drawable);
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_editprofile);

        setTitle("Edit Profile Activity");

        imageButton = findViewById(R.id.imageButton);
        submit_button = findViewById(R.id.submit_button);
        textName = findViewById(R.id.textName);
        textEmail = findViewById(R.id.textEmail);
        moodBar = findViewById(R.id.moodBar);
        mood = findViewById(R.id.mood);





        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
                Matcher mat = pattern.matcher(textEmail.getText().toString());
                if(textName.getText().toString().equals("") || textEmail.getText().toString().equals("")) {
                    Toast.makeText(EditProfileActivity.this, "Name or email was left blank",Toast.LENGTH_LONG).show();
                }
                else if(mat.matches() == false)
                    Toast.makeText(EditProfileActivity.this, "Please enter a valid email address", Toast.LENGTH_LONG).show();
                else {
                    Intent intent = new Intent(EditProfileActivity.this, DisplayActivity.class);
                    startActivity(intent);
                }
            }
        });

        moodBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i == 0) {
                    mood.setImageResource(R.drawable.angry);
                }
                if(i == 1) {
                    mood.setImageResource(R.drawable.sad);
                }
                if(i == 2) {
                    mood.setImageResource(R.drawable.happy);
                }
                if(i == 3) {
                    mood.setImageResource(R.drawable.awesome);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}