package com.faizurazadri.submission3_bfaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.faizurazadri.submission3_bfaa.adapter.AdapterPage;
import com.faizurazadri.submission3_bfaa.db.UserDBContract;
import com.faizurazadri.submission3_bfaa.db.UserDBHelper;
import com.faizurazadri.submission3_bfaa.db.UserHelper;
import com.faizurazadri.submission3_bfaa.model.DetailUserModel;
import com.faizurazadri.submission3_bfaa.model.UserModel;
import com.faizurazadri.submission3_bfaa.retrofit.ApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.faizurazadri.submission3_bfaa.db.UserDBContract.UserGithubColum.TABLE_USER_NAME;

public class DetailUserActivity extends AppCompatActivity {

    DetailUserModel detailUserModel;
    UserModel userModel;
    private UserHelper userHelper;
    private ArrayList<UserModel> userModelArrayList = new ArrayList<>();
    boolean status_fav = false;
    FloatingActionButton floatingActionButton;
    ViewPager viewPager;
    ImageView avatar;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        TextView nama = (TextView)findViewById(R.id.nama_panjang);
        TextView username =  (TextView)findViewById(R.id.username_detail);
        TextView location =  (TextView) findViewById(R.id.location_detail);
        avatar = findViewById(R.id.avatar_detail_user);
        tabLayout =findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpager);
        floatingActionButton = findViewById(R.id.fav_favorit);

        userModel = getIntent().getParcelableExtra("DATA_USER");

        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Detail " + userModel.getLogin());
        }

        final ProgressDialog progressDialog = new ProgressDialog(DetailUserActivity.this);
        progressDialog.setMessage(getResources().getString(R.string.menunggu));
        progressDialog.setCancelable(false);
        progressDialog.show();
        Glide.with(DetailUserActivity.this)
                .load(userModel.getAvatar())
                .into(avatar);

        username.setText(userModel.getLogin());

        Call<DetailUserModel> detailUserModelCall = ApiClient.getApiService().getDetailUser(userModel.getLogin());
        detailUserModelCall.enqueue(new Callback<DetailUserModel>() {
            @Override
            public void onResponse(Call<DetailUserModel> call, Response<DetailUserModel> response) {
                detailUserModel = response.body();
                nama.setText(detailUserModel.getName());
                location.setText(detailUserModel.getLocation());
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<DetailUserModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.gagal), Toast.LENGTH_LONG).show();
            }
        });

        tampil_pager();

        userHelper = UserHelper.getInstance(getApplicationContext());

        status_favorit(check_favorit(userModel.getLogin()));

        fav_favorit();

    }

    public void fav_favorit(){
        floatingActionButton.setOnClickListener(v -> {
            status_fav=!status_fav;

            if (check_favorit(userModel.getLogin())){
                floatingActionButton.setImageResource(R.drawable.icon_favorit_2);
                userModelArrayList =userHelper.getDataUserGithub();
                userHelper.deleteUserGithub(String.valueOf(detailUserModel.getId()));
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.berhasil_menghapus), Toast.LENGTH_LONG).show();
            }else {
                userModelArrayList = userHelper.getDataUserGithub();
                userHelper.inserUser(detailUserModel);
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.berhasil_memasukkan), Toast.LENGTH_LONG).show();
                floatingActionButton.setImageResource(R.drawable.icon_favorit);
            }
        });
    }

    private boolean check_favorit(String login) {
        String ch = UserDBContract.UserGithubColum.USERNAME + "=?";
        String[] change ={login};
        String limit = "1";
        userHelper = new UserHelper(this);
        userHelper.open();
        userModel = getIntent().getParcelableExtra("DATA_USER");
        floatingActionButton.setImageResource(R.drawable.icon_favorit);

        UserDBHelper userDBHelper = new UserDBHelper(getApplicationContext());
        SQLiteDatabase database = userDBHelper.getWritableDatabase();
        @SuppressLint("Recyle")
        Cursor cursor = database.query(TABLE_USER_NAME, null, ch, change, null,null,null, limit);
        boolean check = (cursor.getCount()>0);
        cursor.close();
        return check;
    }

    public void status_favorit(Boolean status_fav){
        if (status_fav){
            floatingActionButton.setImageResource(R.drawable.icon_favorit);
        }else{
            floatingActionButton.setImageResource(R.drawable.icon_favorit_2);
        }
    }

    private void tampil_pager(){
        AdapterPage adapterPage = new AdapterPage(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapterPage);
        tabLayout.setupWithViewPager(viewPager);
        getSupportActionBar().setElevation(0);
    }
}