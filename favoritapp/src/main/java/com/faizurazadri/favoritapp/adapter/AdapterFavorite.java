package com.faizurazadri.favoritapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.faizurazadri.favoritapp.R;
import com.faizurazadri.favoritapp.model.UserModel;

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.ViewHolderFavorit> {

    private Cursor cursor;
    Context context;

    public AdapterFavorite(Context context) {
        this.context = context;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    private UserModel getItemData(int position) {
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("INVALID");
        }
        return new UserModel(cursor);
    }

    @NonNull
    @Override
    public ViewHolderFavorit onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorit, parent, false);
        return new ViewHolderFavorit(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFavorit holder, int position) {
        UserModel userModel = getItemData(position);
        holder.username.setText(userModel.getLogin());
        Glide.with(context)
                .load(userModel.getAvatar())
                .into(holder.avatar);

    }

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;
        return cursor.getCount();
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
