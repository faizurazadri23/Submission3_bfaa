package com.faizurazadri.favoritapp.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class UserDBContract {

    public static final String AUTHORITY = "com.faizurazadri.submission3_bfaa";
    public static final String SCHEME = "content";

    public static final class UserGithubColum implements BaseColumns{
        public static final String TABLE_USER_NAME = "user";
        public static final String ID="id";
        public static final String USERNAME ="username";
        public static final String AVATAR= "avatar";
        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_USER_NAME)
                .build();
    }

    public static String getFavorit(Cursor cursor, String colum){
        return cursor.getString(cursor.getColumnIndex(colum));
    }
}
