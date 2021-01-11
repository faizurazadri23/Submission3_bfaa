package com.faizurazadri.submission3_bfaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.faizurazadri.submission3_bfaa.adapter.AdapterFavorite;
import com.faizurazadri.submission3_bfaa.db.UserHelper;
import com.faizurazadri.submission3_bfaa.model.UserModel;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    private UserHelper userHelper;
    private ArrayList<UserModel> userModelArrayList = new ArrayList<>();
    private AdapterFavorite adapterFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        userHelper = new UserHelper(getApplicationContext());
        userHelper.open();
        userModelArrayList = userHelper.getDataUserGithub();
        ambilData();
    }

    private void ambilData() {
        RecyclerView recyclerViewData= findViewById(R.id.rv_favorit);
        recyclerViewData.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewData.setHasFixedSize(true);
        adapterFavorite = new AdapterFavorite(getApplicationContext());
        recyclerViewData.setAdapter(adapterFavorite);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userModelArrayList = userHelper.getDataUserGithub();
        adapterFavorite.setUserModelList(userModelArrayList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userHelper.close();
    }
}