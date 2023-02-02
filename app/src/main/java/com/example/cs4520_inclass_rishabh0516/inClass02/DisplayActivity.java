// Rishabh Sahu
// Assignment #2
package com.example.cs4520_inclass_rishabh0516.inClass02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs4520_inclass_rishabh0516.R;

public class DisplayActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView nameText;
    private TextView emailText;
    private TextView useText;
    private TextView moodtext;
    private ImageView mood3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        setTitle("Display Activity");

        imageView = findViewById(R.id.imageView);
        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        useText = findViewById(R.id.useText);
        moodtext = findViewById(R.id.moodtext);
        mood3 = findViewById(R.id.mood3);

        if(getIntent() != null && getIntent().getExtras() != null) {
            Profile profile = getIntent().getParcelableExtra(EditProfileActivity.Profile_Key);

            imageView.setImageDrawable(getDrawable(profile.getAvatarId()));
            nameText.setText(profile.getName());
            emailText.setText(profile.getEmail());
            useText.setText(profile.getDevice());
            moodtext.setText(profile.getEmotion());
            mood3.setImageDrawable(getDrawable(profile.getMoodId()));

        }
    }
}