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

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.ViewHolderFavorit> {

    List<UserModel> userModelList;
    Context context;

    public AdapterFavorite(Context context) {
        this.context = context;
    }

    public void setUserModelList(ArrayList<UserModel> userModelArrayList){
        this.userModelList = userModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolderFavorit onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorit, parent, false);
        return new ViewHolderFavorit(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFavorit holder, int position) {
        UserModel userModel = userModelList.get(position);
        holder.username.setText(userModel.getLogin());
        Glide.with(context)
                .load(userModel.getAvatar())
                .into(holder.avatar);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailUserActivity.class);
            intent.putExtra("DATA_USER", userModel);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public class ViewHolderFavorit extends RecyclerView.ViewHolder {
        private TextView username;
        private ImageView avatar;
        public ViewHolderFavorit(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username_favorit);
            avatar = itemView.findViewById(R.id.avatar_favorit);
        }
    }
}
