package com.example.cs4520_inclass_rishabh0516.inClass08;
// Rishabh Sahu
// Assignment #8

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.cs4520_inclass_rishabh0516.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class InClass08 extends AppCompatActivity implements
        LoginFragment.IloginFragmentAction, RegisterFragment.IregisterFragmentAction,
        MainFragment.IMainFragmentAction {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class8);
        setTitle("FireBase");
//        Initializing Firebase Authentication...
        mAuth = FirebaseAuth.getInstance();
    }

    private void populateScreen() {
        //      Check for Authenticated users ....
        if(currentUser != null){
            //The user is authenticated, Populating The Main Fragment....
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerMain, MainFragment.newInstance(),"mainFragment")
                    .commit();

        }else{
//            The user is not logged in, load the login Fragment....
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerMain, LoginFragment.newInstance(),"loginFragment")
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        populateScreen();
    }

    @Override
    public void populateMainFragment(FirebaseUser mUser) {
        this.currentUser = mUser;
        populateScreen();
    }

    @Override
    public void populateRegisterFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerMain, RegisterFragment.newInstance(), "registerFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void signOut() {
        mAuth.signOut();
        currentUser = null;
        populateScreen();
    }

    @Override
    public void openChatFragment(String friendName, String friendEmail) {
        ChatFragment chatFragment = ChatFragment.newInstance(friendEmail);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerMain, chatFragment, "chatFragment")
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void registerDone(FirebaseUser mUser) {
        this.currentUser = mUser;
        populateScreen();
    }
}