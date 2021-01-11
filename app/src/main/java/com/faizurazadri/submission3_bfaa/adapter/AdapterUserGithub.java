package com.faizurazadri.submission3_bfaa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.faizurazadri.submission3_bfaa.DetailUserActivity;
import com.faizurazadri.submission3_bfaa.R;
import com.faizurazadri.submission3_bfaa.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterUserGithub extends RecyclerView.Adapter<AdapterUserGithub.ViewHolderUser> {

    private Context context;
    private List<UserModel> userModels = new ArrayList<>();

    public AdapterUserGithub(Context context, List<UserModel> userModels) {
        this.context = context;
        this.userModels = userModels;
    }

    @NonNull
    @Override
    public ViewHolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users, parent, false);
        return new ViewHolderUser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUser holder, int position) {
        Glide.with(context)
                .load(userModels.get(position).getAvatar())
                .into(holder.avatar);

        holder.username.setText(userModels.get(position).getLogin());

        holder.itemView.setOnClickListener(v -> {
            UserModel userModel = userModels.get(position);
            Intent toDetail = new Intent(context, DetailUserActivity.class);
            toDetail.putExtra("DATA_USER", userModel);
            v.getContext().startActivity(toDetail);
        });
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public class ViewHolderUser extends RecyclerView.ViewHolder {

        private ImageView avatar;
        private TextView username;

        public ViewHolderUser(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.avatar_user);
            username = itemView.findViewById(R.id.username_github);
        }
    }
}
