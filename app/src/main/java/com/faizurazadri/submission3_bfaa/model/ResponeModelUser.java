package com.faizurazadri.submission3_bfaa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponeModelUser {

    @SerializedName("total_count")
    private int total;

    @SerializedName("incomplete_results")
    private boolean incompleteresults;

    @SerializedName("items")
    private List<UserModel> items;

    public List<UserModel> getItems() {
        return items;
    }
}
