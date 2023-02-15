package com.example.cs4520_inclass_rishabh0516.inClass03;
// Rishabh Sahu
// Assignment 3
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs4520_inclass_rishabh0516.R;
import com.example.cs4520_inclass_rishabh0516.inClass02.Profile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EditProfile extends Fragment {

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

    private int moodId;

    final static String Profile_Key = "profile";

    private Profile profile;
    private int avatar;


    public EditProfile() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        if(getArguments()!= null && getArguments().containsKey(SelectAvatar.Avatar_Key)) {
            profile = getArguments().getParcelable(SelectAvatar.Avatar_Key);
            textName.setText(profile.getName());
            textEmail.setText(profile.getEmail());
            mood.setImageResource(profile.getMoodId());
            mood_text.setText(profile.getEmotion());
            imageButton.setImageResource(profile.getAvatarId());

            if(profile.getEmotion() == null) {
                profile.setEmotion("Your current mood: happy");
                profile.setMoodId(R.drawable.happy);
                mood.setImageResource(profile.getMoodId());
                mood_text.setText(profile.getEmotion());
            }

            if (profile.getDevice() != null && profile.getDevice().equals("I use Android!")) {
                radio_group.check(R.id.androidButton);
            }
            if (profile.getDevice() != null && profile.getDevice().equals("I use iOS!")) {
                radio_group.check(R.id.iosButton);
            }

            if (profile.getDevice() != null && profile.getEmotion().equals("I am angry!")) {
                moodBar.setProgress(0);
            }

            if (profile.getDevice() != null && profile.getEmotion().equals("I am sad!")) {
                moodBar.setProgress(1);
            }

            if (profile.getDevice() != null && profile.getEmotion().equals("I am happy!")) {
                moodBar.setProgress(2);
            }

            if (profile.getDevice() != null && profile.getEmotion().equals("I am awesome!")) {
                moodBar.setProgress(3);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        getActivity().setTitle("Edit Profile Fragment");




        imageButton = rootView.findViewById(R.id.imageButton);
        submit_button = rootView.findViewById(R.id.submit_button);
        textName = rootView.findViewById(R.id.textName);
        textEmail = rootView.findViewById(R.id.textEmail);
        moodBar = rootView.findViewById(R.id.moodBar);
        mood = rootView.findViewById(R.id.mood);
        radio_group = rootView.findViewById(R.id.radio_group);
        mood_text = rootView.findViewById(R.id.mood_text);


        profile = new Profile(textName.getText().toString(), textEmail.getText().toString(), device_selected, mood2, avatar, moodId);
        profile.setMoodId(R.drawable.happy);
        profile.setEmotion("Your current mood: happy");


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile.setName(textName.getText().toString());
                profile.setEmail(textEmail.getText().toString());
                profile.setDevice(device_selected);
                profile.setEmotion(mood2);
                profile.setMoodId(moodId);
                profile.setAvatarId(avatar);

                Bundle bundle = new Bundle();
                bundle.putParcelable(Profile_Key, profile);
                Navigation.findNavController(rootView).navigate(R.id.action_fragmentHome_to_selectAvatar, bundle);
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

       submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
                Matcher mat = pattern.matcher(textEmail.getText().toString());
                if(textName.getText().toString().equals("") || textEmail.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Name or email was left blank",Toast.LENGTH_LONG).show();
                }
                else if(mat.matches() == false)
                    Toast.makeText(getActivity(), "Please enter a valid email address", Toast.LENGTH_LONG).show();
                else {
                    profile.setName(textName.getText().toString());
                    profile.setEmail(textEmail.getText().toString());
                    profile.setDevice(device_selected);
                    profile.setEmotion(mood2);
                    profile.setMoodId(moodId);
                    profile.setAvatarId(avatar);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(Profile_Key, profile);
                    Navigation.findNavController(rootView).navigate(R.id.action_fragmentHome_to_fragmentDisplay, bundle);
                }
            }
        });


        return rootView;
    }
}