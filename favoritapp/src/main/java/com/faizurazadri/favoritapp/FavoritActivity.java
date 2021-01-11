package com.faizurazadri.favoritapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import com.faizurazadri.favoritapp.adapter.AdapterFavorite;

import static com.faizurazadri.favoritapp.db.UserDBContract.UserGithubColum.CONTENT_URI;

public class FavoritActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFavorit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        recyclerViewFavorit = findViewById(R.id.rv_favorit);
        recyclerViewFavorit.setHasFixedSize(true);
        recyclerViewFavorit.setLayoutManager(new LinearLayoutManager(this));

        if (savedInstanceState==null){
            new Favorit().execute();
        }
    }

    private class Favorit extends AsyncTask<Void, Void, Cursor>{

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getApplicationContext().getContentResolver().query(CONTENT_URI, null,null,null,null);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            AdapterFavorite adapterFavorite = new AdapterFavorite(getApplicationContext());
            adapterFavorite.setCursor(cursor);
            adapterFavorite.notifyDataSetChanged();
            recyclerViewFavorit.setAdapter(adapterFavorite);
        }
    }

}