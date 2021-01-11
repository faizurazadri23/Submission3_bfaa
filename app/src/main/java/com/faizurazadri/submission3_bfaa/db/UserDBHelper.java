package com.faizurazadri.submission3_bfaa.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.faizurazadri.submission3_bfaa.db.UserDBContract.UserGithubColum.TABLE_USER_NAME;

public class UserDBHelper extends SQLiteOpenHelper {

    private static final String USER_DB_NAME = "UserGithub";
    private static final int VERSI_DB = 1;

    private static String SQL_CREATE_TABLE =String.format(
            "CREATE TABLE %s" +
                    "(%s INTEGER PRIMARY KEY," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL)",
            TABLE_USER_NAME,
            UserDBContract.UserGithubColum.ID,
            UserDBContract.UserGithubColum.USERNAME,
            UserDBContract.UserGithubColum.AVATAR

    );

    public UserDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public UserDBHelper(Context context) {
        super(context, USER_DB_NAME, null, VERSI_DB);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER_NAME);
        onCreate(db);
    }

}
