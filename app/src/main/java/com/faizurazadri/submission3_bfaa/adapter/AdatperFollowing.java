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
import com.faizurazadri.submission3_bfaa.model.FollowingModel;

import java.util.ArrayList;

public class AdatperFollowing extends RecyclerView.Adapter<AdatperFollowing.ViewholderFollowing> {

    private Context context;
    private ArrayList<FollowingModel> followingModelArrayList=new ArrayList<>();

    public AdatperFollowing(Context context, ArrayList<FollowingModel> followingModelArrayList) {
        this.context = context;
        this.followingModelArrayList = followingModelArrayList;
    }

    @NonNull
    @Override
    public ViewholderFollowing onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_following, parent, false);
        return new ViewholderFollowing(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewholderFollowing holder, int position) {
        FollowingModel followerModel = followingModelArrayList.get(position);
        holder.username.setText(followerModel.getMasuk());
        Glide.with(context)
                .load(followerModel.getUrl())
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return followingModelArrayList.size();
    }

    public class ViewholderFollowing extends RecyclerView.ViewHolder {

        private TextView username;
        private ImageView avatar;

        public ViewholderFollowing(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username_following);
            avatar = itemView.findViewById(R.id.avatar_following);
        }
    }
}
