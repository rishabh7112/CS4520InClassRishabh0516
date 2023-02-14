package com.example.cs4520_inclass_rishabh0516.inClass03;
// Rishabh Sahu
// Assignment 3
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cs4520_inclass_rishabh0516.ProfileFragment;
import com.example.cs4520_inclass_rishabh0516.R;
import com.example.cs4520_inclass_rishabh0516.inClass02.Profile;
import com.example.cs4520_inclass_rishabh0516.inClass03.EditProfile;


public class SelectAvatar extends Fragment {
    private ImageButton fragement_avatar1;
    private ImageButton avatar2;
    private ImageButton avatar3;
    private ImageButton avatar4;
    private ImageButton avatar5;
    private ImageButton avatar6;
    private Profile profile;

    final static String Avatar_Key = "saved profile";

    public SelectAvatar() {
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
        View rootView = inflater.inflate(R.layout.fragment_select_avatar, container, false);
        getActivity().setTitle("Select Avatar Fragment");
        fragement_avatar1 = rootView.findViewById(R.id.fragement_avatar1);
        avatar2 = rootView.findViewById(R.id.avatar2);
        avatar3 = rootView.findViewById(R.id.avatar3);
        avatar4 = rootView.findViewById(R.id.avatar4);
        avatar5 = rootView.findViewById(R.id.avatar5);
        avatar6 = rootView.findViewById(R.id.avatar6);

        fragement_avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                profile.setAvatarId(R.drawable.avatar_f_1);
                bundle.putParcelable(Avatar_Key, profile);
                Navigation.findNavController(rootView).navigate(R.id.action_selectAvatar_to_fragmentHome, bundle);
            }
        });
        avatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                profile.setAvatarId(R.drawable.avatar_m_3);
                bundle.putParcelable(Avatar_Key, profile);
                Navigation.findNavController(rootView).navigate(R.id.action_selectAvatar_to_fragmentHome, bundle);
            }
        });

        avatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                profile.setAvatarId(R.drawable.avatar_f_2);
                bundle.putParcelable(Avatar_Key, profile);
                Navigation.findNavController(rootView).navigate(R.id.action_selectAvatar_to_fragmentHome, bundle);
            }
        });

        avatar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                profile.setAvatarId(R.drawable.avatar_m_2);
                bundle.putParcelable(Avatar_Key, profile);
                Navigation.findNavController(rootView).navigate(R.id.action_selectAvatar_to_fragmentHome, bundle);

            }
        });

        avatar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                profile.setAvatarId(R.drawable.avatar_f_3);
                bundle.putParcelable(Avatar_Key, profile);
                Navigation.findNavController(rootView).navigate(R.id.action_selectAvatar_to_fragmentHome, bundle);

            }
        });

        avatar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                profile.setAvatarId(R.drawable.avatar_m_1);
                bundle.putParcelable(Avatar_Key, profile);
                Navigation.findNavController(rootView).navigate(R.id.action_selectAvatar_to_fragmentHome, bundle);
            }
        });
        return rootView;
    }
}