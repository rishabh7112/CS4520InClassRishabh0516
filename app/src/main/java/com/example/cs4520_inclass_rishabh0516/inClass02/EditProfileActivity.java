
// Rishabh Sahu
// Assignment #2
package com.example.cs4520_inclass_rishabh0516.inClass02;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs4520_inclass_rishabh0516.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditProfileActivity extends AppCompatActivity {
    private ImageButton imageButton;
    private Button submit_button;

    private EditText textName;

    private EditText textEmail;

    private SeekBar moodBar;

    private ImageView mood;

    private RadioGroup radio_group;

    private TextView mood_text;

    private String device_selected;

    private String mood2;

    private int avatarId;

    private int moodId;

    final static String Profile_Key = "fromEditToDisplay";

    ActivityResultLauncher<Intent> avatarActivityForResult
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK) {
                imageButton = findViewById(R.id.imageButton);
                int drawable = result.getData().getIntExtra("ToEdit",0);
                avatarId = drawable;
                imageButton.setImageDrawable(getDrawable(drawable));
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
        radio_group = findViewById(R.id.radio_group);
        mood_text = findViewById(R.id.mood_text);


        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.androidButton) {
                    device_selected = "I use Android!";
                }
                if(i == R.id.iosButton) {
                    device_selected = "I use iOS!";
                }
            }
        });



        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent avatar_intent = new Intent(EditProfileActivity.this, SelectActivity.class);
                avatarActivityForResult.launch(avatar_intent);
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
                    Profile profile = new Profile(textName.getText().toString(), textEmail.getText().toString(), device_selected, mood2, avatarId, moodId);
                    intent.putExtra(Profile_Key, profile);
                    startActivity(intent);
                }
            }
        });

        moodBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i == 0) {
                    mood.setImageResource(R.drawable.angry);
                    moodId = R.drawable.angry;
                    mood2 = "I am angry!";
                    mood_text.setText("Your current mood: angry");
                }
                if(i == 1) {
                    mood.setImageResource(R.drawable.sad);
                    moodId = R.drawable.sad;
                    mood2 = "I am sad!";
                    mood_text.setText("Your current mood: sad");
                }
                if(i == 2) {
                    mood.setImageResource(R.drawable.happy);
                    moodId = R.drawable.happy;
                    mood2 = "I am happy!";
                    mood_text.setText("Your current mood: happy");
                }
                if(i == 3) {
                    mood.setImageResource(R.drawable.awesome);
                    moodId = R.drawable.awesome;
                    mood2 = "I am awesome!";
                    mood_text.setText("Your current mood: awesome");
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