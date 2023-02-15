package com.example.cs4520_inclass_rishabh0516.inClass03;
// Rishabh Sahu
// Assignment 3

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs4520_inclass_rishabh0516.ProfileFragment;
import com.example.cs4520_inclass_rishabh0516.R;
import com.example.cs4520_inclass_rishabh0516.inClass02.Profile;

public class Display extends Fragment {
    private Profile profile;
    private ImageView imageView;
    private TextView nameText;
    private TextView emailText;
    private TextView useText;
    private TextView moodtext;
    private ImageView mood3;

    public Display() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            profile = getArguments().getParcelable(EditProfile.Profile_Key);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_display, container, false);
        getActivity().setTitle("Display Fragment");
        imageView = rootView.findViewById(R.id.imageView);
        nameText = rootView.findViewById(R.id.nameText);
        emailText = rootView.findViewById(R.id.emailText);
        useText = rootView.findViewById(R.id.useText);
        moodtext = rootView.findViewById(R.id.moodtext);
        mood3 = rootView.findViewById(R.id.mood3);


        imageView.setImageResource(profile.getAvatarId());
        nameText.setText(profile.getName());
        emailText.setText(profile.getEmail());
        useText.setText(profile.getDevice());
        moodtext.setText(profile.getEmotion());
        mood3.setImageResource(profile.getMoodId());


        return rootView;
    }
}