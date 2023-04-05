package com.example.cs4520_inclass_rishabh0516.inClass08;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cs4520_inclass_rishabh0516.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;


public class RegisterFragment extends Fragment implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private EditText first_name, last_name, display_name, email, password, repeatPassword;
    private Button buttonRegister2;
    private String firstname, lastname, username, email_str, password_str, rep_password;
    private IregisterFragmentAction mListener;
    private FirebaseFirestore firestore;

    public RegisterFragment() {
    }


    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IregisterFragmentAction){
            this.mListener = (IregisterFragmentAction) context;
        }else{
            throw new RuntimeException(context.toString()
                    + "must implement RegisterRequest");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        first_name = rootView.findViewById(R.id.first_name);
        last_name = rootView.findViewById(R.id.last_name);
        display_name = rootView.findViewById(R.id.display_name);
        email = rootView.findViewById(R.id.email);
        password = rootView.findViewById(R.id.password);
        repeatPassword = rootView.findViewById(R.id.repeatPassword);
        buttonRegister2 = rootView.findViewById(R.id.buttonRegister2);
        buttonRegister2.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        this.firstname = String.valueOf(first_name.getText()).trim();
        this.lastname = String.valueOf(last_name.getText()).trim();
        this.username = String.valueOf(display_name.getText()).trim();
        this.email_str = String.valueOf(email.getText()).trim();
        this.password_str = String.valueOf(password.getText()).trim();
        this.rep_password = String.valueOf(repeatPassword.getText()).trim();

        if(view.getId()== R.id.buttonRegister2) {
            if(first_name.equals("")){
                first_name.setError("Must input first name!");
            }

            if(last_name.equals("")){
                last_name.setError("Must input last name!");
            }

            if(username.equals("")){
                display_name.setError("Must input last name!");
            }

            if(email_str.equals("")){
                email.setError("Must input email!");
            }
            if(password_str.equals("")){
                password.setError("Password must not be empty!");
            }

            if(password_str.length() < 8){
                password.setError("Password must not be at least 8 characters long!");
            }

            if(!rep_password.equals(password_str)){
                repeatPassword.setError("Passwords must match!");
            }

            if(!firstname.equals("") && !lastname.equals("") && !username.equals("")
                    && !email_str.equals("")
                    && !password_str.equals("")
                    && rep_password.equals(password_str)){

                mAuth.createUserWithEmailAndPassword(email_str, password_str)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    mUser = task.getResult().getUser();

//                                    Adding name to the FirebaseUser...
                                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(username)
                                            .build();

                                    mUser.updateProfile(profileChangeRequest)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        // Prepare user data for Firestore
                                                        Map<String, Object> userData = new HashMap<>();
                                                        userData.put("first_name", firstname);
                                                        userData.put("last_name", lastname);
                                                        userData.put("username", username);
                                                        userData.put("email", email_str);
                                                        userData.put("password", password_str);
                                                        firestore.collection("Users")
                                                                .document(email_str)
                                                                .set(userData)
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {
                                                                        // User data successfully saved to Firestore
                                                                        Toast.makeText(getActivity(), "User data saved successfully.", Toast.LENGTH_SHORT).show();

                                                                        // Call registerDone() to navigate to the MainFragment
                                                                        mListener.registerDone(mUser);
                                                                    }
                                                                })
                                                                .addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {
                                                                        // Handle failure to save user data to Firestore
                                                                        Toast.makeText(getActivity(), "Failed to save user data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });
                                                    }
                                                }
                                            });
                                }
                            }
                        });

            }
        }
    }

    public interface IregisterFragmentAction {
        void registerDone(FirebaseUser mUser);
    }
}