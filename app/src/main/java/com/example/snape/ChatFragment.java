package com.example.snape;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.snape.RecyclerViewStory.StoryAdapter;
import com.example.snape.RecyclerViewStory.StoryObject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static ChatFragment newInstance(){
        ChatFragment fragment = new ChatFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat ,container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new StoryAdapter(getDataSet(), getContext());
        //mRecyclerView.setItemAnimator(null);
        mRecyclerView.setAdapter(mAdapter);

        Button mRefresh = view.findViewById(R.id.refresh);
        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                listenForData();
            }
        });
        return view;
    }

    private void clear() {
        int size = this.results.size();
        this.results.clear();
        mAdapter.notifyItemRangeChanged(0, size);
    }

    private ArrayList<StoryObject> results = new ArrayList<>();
    private ArrayList<StoryObject> getDataSet() {
        listenForData();
        return results;
    }

    private void listenForData(){
        DatabaseReference receiveDb = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("received");
        receiveDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snap : dataSnapshot.getChildren()){
                        getUserInfo(snap.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void getUserInfo(String key) {
        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("users").child(key);
        userDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String email = dataSnapshot.child("email").getValue().toString();
                    String uid = dataSnapshot.getRef().getKey();

                    StoryObject obj = new StoryObject(email, uid, "chat");
                    if(!results.contains(obj)){
                        results.add(obj);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
