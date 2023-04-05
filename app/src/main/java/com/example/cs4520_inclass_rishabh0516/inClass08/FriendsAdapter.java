package com.example.cs4520_inclass_rishabh0516.inClass08;
import com.example.cs4520_inclass_rishabh0516.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {
    private List<User> userList;
    private LayoutInflater layoutInflater;
    private MainFragment.IMainFragmentAction mListener;


    public FriendsAdapter(Context context, List<User> userList, MainFragment.IMainFragmentAction mListener) {
        this.userList = userList;
        this.layoutInflater = LayoutInflater.from(context);
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycler_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User currentUser = userList.get(position);
        holder.textViewName.setText(currentUser.getFirst_name() + " " + currentUser.getLast_name());
        holder.buttonEdit.setOnClickListener(v -> {
            User friend = userList.get(position);
            mListener.openChatFragment(friend.getFirst_name() + " " + friend.getLast_name(), friend.getEmail());
        });
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        Button buttonEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewChat);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
        }

    }
}
