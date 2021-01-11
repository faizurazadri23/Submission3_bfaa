package com.faizurazadri.submission3_bfaa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.faizurazadri.submission3_bfaa.R;
import com.faizurazadri.submission3_bfaa.model.FollowerModel;
import com.faizurazadri.submission3_bfaa.model.UserModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterFollowers extends RecyclerView.Adapter<AdapterFollowers.ViewHolderFollowers> {

    private Context context;
    private ArrayList<FollowerModel> followerModelArrayList = new ArrayList<>();

    public AdapterFollowers(Context context, ArrayList<FollowerModel> followerModelArrayList) {
        this.context = context;
        this.followerModelArrayList = followerModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolderFollowers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_followers, parent, false);
        return new ViewHolderFollowers(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFollowers holder, int position) {
        FollowerModel followerModel = followerModelArrayList.get(position);
        holder.username.setText(followerModel.getMasuk());
        Glide.with(context)
                .load(followerModel.getUrl())
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return followerModelArrayList.size();
    }

    public class ViewHolderFollowers extends RecyclerView.ViewHolder {

        private TextView username;
        private ImageView avatar;

        public ViewHolderFollowers(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username_follower);
            avatar = itemView.findViewById(R.id.avatar_follower);
        }
    }
}
