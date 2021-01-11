package com.faizurazadri.submission3_bfaa.model;

import com.google.gson.annotations.SerializedName;

public class FollowingModel {

    @SerializedName("login")
    public String masuk;

    @SerializedName("avatar_url")
    public String url;

    @SerializedName("id")
    public int id;

    public String getMasuk() {
        return masuk;
    }

    public void setMasuk(String masuk) {
        this.masuk = masuk;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
