package com.faizurazadri.submission3_bfaa.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.faizurazadri.submission3_bfaa.model.DetailUserModel;
import com.faizurazadri.submission3_bfaa.model.UserModel;

import java.util.ArrayList;

import static com.faizurazadri.submission3_bfaa.db.UserDBContract.UserGithubColum.AVATAR;
import static com.faizurazadri.submission3_bfaa.db.UserDBContract.UserGithubColum.ID;
import static com.faizurazadri.submission3_bfaa.db.UserDBContract.UserGithubColum.TABLE_USER_NAME;
import static com.faizurazadri.submission3_bfaa.db.UserDBContract.UserGithubColum.USERNAME;

public class UserHelper {

    private static final String DATABASE_TABLE = TABLE_USER_NAME;
    private static UserHelper userHelper;
    private static UserDBHelper userDBHelper;
    private static SQLiteDatabase database;

    public UserHelper(Context context){
        userDBHelper = new UserDBHelper(context);
    }

    public static UserHelper getInstance(Context context){
        if (userHelper == null){
            synchronized (SQLiteOpenHelper.class){
                if (userHelper ==null){
                    userHelper =new UserHelper(context);
                }
            }
        }
        return userHelper;
    }

    public void open() throws SQLException{
        database = userDBHelper.getWritableDatabase();
    }

    public void close(){
        userDBHelper.close();
        if (database.isOpen())
            database.close();
    }

    public Cursor queryAll(){
        return database.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                ID + " ASC");
    }

    public Cursor queryById(String id){
        return database.query(DATABASE_TABLE, null
        ,ID + " = ?"
        , new String[]{id}
        ,null
        ,null
        ,null
        ,null);
    }

    public ArrayList<UserModel> getDataUserGithub(){
        ArrayList<UserModel> userModelArrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                USERNAME + " ASC",
                null);
        cursor.moveToFirst();
        UserModel userModel;
        if (cursor.getCount()>0){
            do {
                userModel = new UserModel();
                userModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                userModel.setAvatar(cursor.getString(cursor.getColumnIndexOrThrow(AVATAR)));
                userModel.setLogin(cursor.getString(cursor.getColumnIndexOrThrow(USERNAME)));

                userModelArrayList.add(userModel);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }cursor.close();
        return userModelArrayList;
    }

    public long inserUser(DetailUserModel detailUserModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, detailUserModel.getId());
        contentValues.put(USERNAME, detailUserModel.getLogin());
        contentValues.put(AVATAR, detailUserModel.getUrl());
        return database.insert(DATABASE_TABLE, null, contentValues);
    }

    public int deleteUserGithub(String id){
        return database.delete(TABLE_USER_NAME, ID+ " = '" + id + "'", null);
    }

    public long insertProvider(ContentValues contentValues){
        return database.insert(DATABASE_TABLE, null, contentValues);
    }

    public int UpdateProvider(String id, ContentValues contentValues){
        return database.update(DATABASE_TABLE, contentValues, ID + " =?", new String[]{id});
    }

    public int deleteProvider(String id){
        return database.delete(TABLE_USER_NAME, ID + "=?", new String[]{id});
    }
}
