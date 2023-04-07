package com.example.cs4520_inclass_rishabh0516.inClass08;
// Rishabh Sahu
// Assignment #8

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cs4520_inclass_rishabh0516.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements View.OnClickListener {
    private Button buttonSignOut;
    private IMainFragmentAction mListener;

    private RecyclerView messages;

    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private FriendsAdapter friendsAdapter;
    private List<User> userList;



    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IMainFragmentAction) {
            mListener = (IMainFragmentAction) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement IMainFragmentAction");
        }
    }

    private void fetchUsers() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String currentEmail = currentUser.getEmail();

        db.collection("Users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        userList = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            User user = document.toObject(User.class);

                            // Exclude the current user
                            if (!user.getEmail().equals(currentEmail)) {
                                userList.add(user);
                            }
                        }
                        friendsAdapter = new FriendsAdapter(getActivity(), userList, mListener);
                        recyclerView.setAdapter(friendsAdapter);
                    } else {
                        //Log.w("MainFragment", "Error getting documents.", task.getException());
                    }
                });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        buttonSignOut = rootView.findViewById(R.id.buttonSignOut);
        buttonSignOut.setOnClickListener(this);
        recyclerView = rootView.findViewById(R.id.messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db = FirebaseFirestore.getInstance();
        fetchUsers();

        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignOut:
                mListener.signOut();
                break;
        }
    }


    public interface IMainFragmentAction {
        void signOut();
        void openChatFragment(String friendName, String friendEmail);
    }
}