package com.faizurazadri.submission3_bfaa;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.faizurazadri.submission3_bfaa.receiver.MyReceiver;

import java.util.Calendar;

public class SettingActivity extends AppCompatActivity {

    private ImageView ganti_bahasa;
    private SharedPreferences sharedPreferences;
    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ganti_bahasa = findViewById(R.id.change_bahasa);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int notificationDaily = sharedPreferences.getInt("user_notification", 0);
        aSwitch = findViewById(R.id.switch_reminder);
        if (notificationDaily==1){
            aSwitch.setChecked(true);
        }else {
            aSwitch.setChecked(false);
        }
        
        aksiSwitch();

        ganti_bahasa.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        });
    }

    private void aksiSwitch() {
        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                setReminderOn(getApplicationContext());
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putInt("user_notification",1);
                editor.commit();
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.notifikasi_aktif), Toast.LENGTH_LONG).show();
            }else {
                setReminderOff(getApplicationContext());
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt("user_notification",0);
                editor.commit();
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.notifikasi_Mati), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setReminderOff(Context applicationContext) {
        Intent intent = new Intent(SettingActivity.this, MyReceiver.class);
        AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(applicationContext.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(applicationContext, 102, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if(alarmManager!=null){
            alarmManager.cancel(pendingIntent);
        }
    }

    private void setReminderOn(Context applicationContext) {
        Intent intent = new Intent(applicationContext, MyReceiver.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.HOUR_OF_DAY, 9);
        calendar.set(calendar.MINUTE,0);
        calendar.set(calendar.SECOND,0);

        AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(applicationContext.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(applicationContext, 102, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (alarmManager!=null){
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }
    
    
}