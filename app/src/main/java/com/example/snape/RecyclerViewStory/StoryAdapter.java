package com.example.snape.RecyclerViewStory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.snape.R;
import com.example.snape.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

//import com.example.snape.R;

public class StoryAdapter extends RecyclerView.Adapter<StoryViewHolders> {

    private List<StoryObject> mUsersList;
    private Context mContext;

    public StoryAdapter(List<StoryObject> usersList, Context context) {
        this.mUsersList = usersList;
        this.mContext = context;
    }

    @Override
    public StoryViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_story_item, null);
        StoryViewHolders rcv = new StoryViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull final StoryViewHolders holder, int position) {
        holder.mEmail.setText(mUsersList.get(position).getEmail());
        holder.mEmail.setTag(mUsersList.get(position).getUid());

        holder.mLayout.setTag(mUsersList.get(position).getChatOrStory());

    }

    @Override
    public int getItemCount() {
        return this.mUsersList.size();
    }
}
