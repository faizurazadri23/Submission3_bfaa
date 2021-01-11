package com.faizurazadri.favoritapp.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.faizurazadri.favoritapp.db.UserDBContract;
import com.google.gson.annotations.SerializedName;

import static com.faizurazadri.favoritapp.db.UserDBContract.getFavorit;

public class UserModel implements Parcelable {

    @SerializedName("login")
    public String login;

    @SerializedName("avatar_url")
    public String avatar;

    @SerializedName("id")
    public int id;

    public UserModel(){
    }

    public UserModel(Cursor cursor){
        this.id = cursor.getInt(0);
        this.login = getFavorit(cursor, UserDBContract.UserGithubColum.USERNAME);
        this.avatar = getFavorit(cursor, UserDBContract.UserGithubColum.AVATAR);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected UserModel(Parcel in) {
        login = in.readString();
        avatar = in.readString();
        id = in.readInt();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(avatar);
        dest.writeInt(id);
    }
}
