package com.example.snape.RecyclerViewReceiver;

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

public class ReceiverAdapter extends RecyclerView.Adapter<ReceiverViewHolders> {

    private List<ReceiverObject> mUsersList;
    private Context mContext;

    public ReceiverAdapter(List<ReceiverObject> usersList, Context context) {
        this.mUsersList = usersList;
        this.mContext = context;
    }

    @Override
    public ReceiverViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_receiver_item, null);
        ReceiverViewHolders rcv = new ReceiverViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull final ReceiverViewHolders holder, int position) {
        holder.mEmail.setText(mUsersList.get(position).getEmail());
        holder.mReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean receiveState = !mUsersList.get(holder.getLayoutPosition()).getReceive();
                mUsersList.get(holder.getLayoutPosition()).setReceive(receiveState);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mUsersList.size();
    }
}
