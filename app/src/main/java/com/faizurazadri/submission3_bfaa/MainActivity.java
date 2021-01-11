package com.faizurazadri.submission3_bfaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.faizurazadri.submission3_bfaa.adapter.AdapterUserGithub;
import com.faizurazadri.submission3_bfaa.model.ResponeModelUser;
import com.faizurazadri.submission3_bfaa.model.UserModel;
import com.faizurazadri.submission3_bfaa.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btn_cari;
    private EditText input_cari;
    private ProgressBar progressBar;
    private RecyclerView recyclerViewGithubUser;
    private List<UserModel> userModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_cari = findViewById(R.id.edt_username);
        btn_cari = findViewById(R.id.btn_cari);
        progressBar = findViewById(R.id.progress_pencarian);
        recyclerViewGithubUser = findViewById(R.id.rv_pencarian);

        recyclerViewGithubUser.setLayoutManager(new LinearLayoutManager(this));

        btn_cari.setOnClickListener(v -> setBtn_cari(input_cari.getText().toString()));
    }


    private void setBtn_cari(final String username){
        progressBar.setVisibility(View.VISIBLE);
        Call<ResponeModelUser> responeModelUserCall = ApiClient.getApiService().aksiCariPengguna(username);
        responeModelUserCall.enqueue(new Callback<ResponeModelUser>() {
            @Override
            public void onResponse(Call<ResponeModelUser> call, Response<ResponeModelUser> response) {
                if (response.isSuccessful()){
                    userModels = response.body().getItems();
                    recyclerViewGithubUser.setAdapter(new AdapterUserGithub(MainActivity.this, userModels));
                    progressBar.setVisibility(View.GONE);
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.tidak_berhasil), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponeModelUser> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.gagal), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.pengaturan_bar:
                Intent pengaturan = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(pengaturan);
                break;

            case R.id.favorite_bar:
                Intent favorit = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(favorit);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
    }
}