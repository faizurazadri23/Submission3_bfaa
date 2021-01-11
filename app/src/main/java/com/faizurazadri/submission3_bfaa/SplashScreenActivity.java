package com.faizurazadri.submission3_bfaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread menuUtama=new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    Intent toMain = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(toMain);
                }
            }
        };
        menuUtama.start();
    }
}