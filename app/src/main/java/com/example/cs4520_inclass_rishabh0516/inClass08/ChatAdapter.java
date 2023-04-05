package com.example.cs4520_inclass_rishabh0516.inClass08;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs4520_inclass_rishabh0516.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<Message> messagesList;

    public ChatAdapter(List<Message> messagesList) {
        this.messagesList = messagesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = messagesList.get(position);
        holder.textViewChat.setText(message.getSenderName() + ": " + message.getMessage());
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewChat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewChat = itemView.findViewById(R.id.textViewChat);
        }
    }
}

