package com.faizurazadri.submission3_bfaa.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.faizurazadri.submission3_bfaa.db.UserHelper;

import java.util.Objects;

import static com.faizurazadri.submission3_bfaa.db.UserDBContract.AUTHORITY;
import static com.faizurazadri.submission3_bfaa.db.UserDBContract.UserGithubColum.CONTENT_URI;
import static com.faizurazadri.submission3_bfaa.db.UserDBContract.UserGithubColum.TABLE_USER_NAME;

public class MyContentProvider extends ContentProvider {

    private static final int USER = 0;
    private static final int ID_USER = 1;
    UserHelper userHelper;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static{
        uriMatcher.addURI(AUTHORITY, TABLE_USER_NAME, USER);
        uriMatcher.addURI(AUTHORITY, TABLE_USER_NAME + "/#", ID_USER);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int delete;
        switch (uriMatcher.match(uri)){
            case ID_USER:
                delete = userHelper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                delete = 0;
                break;
        }

        if (delete>0){
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }
        return delete;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
       long add;
       Uri uri1 = null;
       switch (uriMatcher.match(uri)){
           case USER:
               add= userHelper.insertProvider(values);
               if (add>0){
                   uri1 = ContentUris.withAppendedId(CONTENT_URI, add);
               }
               break;

           default:
               add=0;
               break;
       }

       if (add>0) {
           Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
       }
       return uri1;
    }

    @Override
    public boolean onCreate() {
        userHelper =  UserHelper.getInstance(getContext());
        userHelper.open();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch (uriMatcher.match(uri)){
            case USER:
                cursor = userHelper.queryAll();
                break;

            case ID_USER:
                cursor= userHelper.queryById(uri.getLastPathSegment());
                break;

            default:
                cursor = null;
                break;
        }

        if (cursor!=null){
            cursor.setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(), uri);
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int update;
        switch (uriMatcher.match(uri)){
            case ID_USER:
                update = userHelper.UpdateProvider(uri.getLastPathSegment(), values);
                break;
            default:
                update = 0;
                break;
        }

        if (update>0){
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }

        return update;
    }
}