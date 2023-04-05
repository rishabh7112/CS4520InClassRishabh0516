package com.example.cs4520_inclass_rishabh0516.inClass08;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ChatFragment extends Fragment {
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private List<Message> messagesList;
    private EditText textMessage;
    private Button buttonMessage;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private String friendEmail;





    public ChatFragment() {
        // Required empty public constructor
    }


    public static ChatFragment newInstance(String friendEmail) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString("friend_email", friendEmail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            friendEmail = getArguments().getString("friend_email");
        }

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    private void sendMessage(String messageText) {
        String senderName = currentUser.getDisplayName().split(" ")[0];
        Message message = new Message(currentUser.getEmail(), senderName, friendEmail, messageText, System.currentTimeMillis());

        db.collection("messages")
                .add(message)
                .addOnSuccessListener(documentReference -> {
                    //Log.d(TAG, "Message sent: " + documentReference.getId());
                    fetchMessages();
                })
                .addOnFailureListener(e -> {
                    //Log.w(TAG, "Error sending message", e);
                    Toast.makeText(getContext(), "Failed to send message", Toast.LENGTH_SHORT).show();
                });
    }

    private void fetchMessages() {
        db.collection("messages")
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        messagesList.clear();
                        for (DocumentSnapshot document : task.getResult()) {
                            Message message = document.toObject(Message.class);
                            if ((message.getSenderEmail().equals(currentUser.getEmail()) && message.getReceiverEmail().equals(friendEmail))
                                    || (message.getSenderEmail().equals(friendEmail) && message.getReceiverEmail().equals(currentUser.getEmail()))) {
                                messagesList.add(message);
                            }
                        }
                        chatAdapter.notifyDataSetChanged();
                        recyclerView.scrollToPosition(messagesList.size() - 1);
                    } else {
                        // Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }



    // Add this method
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        textMessage = view.findViewById(R.id.textMessage);
        buttonMessage = view.findViewById(R.id.button_message);

        messagesList = new ArrayList<>();
        chatAdapter = new ChatAdapter(messagesList);
        recyclerView.setAdapter(chatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fetchMessages();

        // Set up the button click listener
        buttonMessage.setOnClickListener(v -> {
            String messageText = textMessage.getText().toString().trim();
            if (!messageText.isEmpty()) {
                sendMessage(messageText);
                textMessage.setText("");
            }
        });

    }

}