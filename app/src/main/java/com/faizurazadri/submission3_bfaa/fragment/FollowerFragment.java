package com.faizurazadri.submission3_bfaa.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.faizurazadri.submission3_bfaa.R;
import com.faizurazadri.submission3_bfaa.adapter.AdapterFollowers;
import com.faizurazadri.submission3_bfaa.model.FollowerModel;
import com.faizurazadri.submission3_bfaa.model.UserModel;
import com.faizurazadri.submission3_bfaa.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowerFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public FollowerFragment() {

    }


    public static FollowerFragment newInstance(String param1, String param2) {
        FollowerFragment fragment = new FollowerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follower, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewFollower=view.findViewById(R.id.rv_follower);
        recyclerViewFollower.setLayoutManager(new LinearLayoutManager(view.getContext()));

        UserModel userModel = Objects.requireNonNull(getActivity().getIntent().getParcelableExtra("DATA_USER"));

        Call<List<FollowerModel>> followermodel = ApiClient.getApiService().getDataFollower(Objects.requireNonNull(userModel).getLogin());
        followermodel.enqueue(new Callback<List<FollowerModel>>() {
            @Override
            public void onResponse(Call<List<FollowerModel>> call, Response<List<FollowerModel>> response) {
                ArrayList<FollowerModel> followerModelArrayList = new ArrayList<>();
                if (response.isSuccessful()){
                    if (response.body()!=null){
                        followerModelArrayList.addAll(response.body());
                        recyclerViewFollower.setAdapter(new AdapterFollowers(getContext(), followerModelArrayList));
                    }
                }else {
                    Toast.makeText(getContext(), getResources().getString(R.string.tidak_berhasil), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<FollowerModel>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.gagal), Toast.LENGTH_LONG).show();
            }
        });
    }
}