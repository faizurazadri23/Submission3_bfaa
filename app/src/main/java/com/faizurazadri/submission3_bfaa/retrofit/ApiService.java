package com.faizurazadri.submission3_bfaa.retrofit;

import com.faizurazadri.submission3_bfaa.BuildConfig;
import com.faizurazadri.submission3_bfaa.model.DetailUserModel;
import com.faizurazadri.submission3_bfaa.model.FollowerModel;
import com.faizurazadri.submission3_bfaa.model.FollowingModel;
import com.faizurazadri.submission3_bfaa.model.ResponeModelUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/users")
    @Headers(BuildConfig.GITHUB_TOKEN)
    Call<ResponeModelUser> aksiCariPengguna(@Query("q") String username);

    @GET("users/{username}")
    @Headers(BuildConfig.GITHUB_TOKEN)
    Call<DetailUserModel> getDetailUser(@Path("username") String username);

    @GET("users/{username}/followers")
    @Headers(BuildConfig.GITHUB_TOKEN)
    Call<List<FollowerModel>> getDataFollower(@Path("username") String username);

    @GET("users/{username}/following")
    @Headers(BuildConfig.GITHUB_TOKEN)
    Call<List<FollowingModel>> getDataFollowing(@Path("username") String username);
}
