package com.varvoux.aurelie.mynewsapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.varvoux.aurelie.mynewsapp.Adapter.MostPopularAdapter;
import com.varvoux.aurelie.mynewsapp.retrofit.MostPopularRequest.MostPopularParentObject;
import com.varvoux.aurelie.mynewsapp.R;
import com.varvoux.aurelie.mynewsapp.retrofit.RetrofitClient;
import com.varvoux.aurelie.mynewsapp.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    public MostPopularFragment() {
        // Required empty public constructor
    }

    public static MostPopularFragment newInstance(){
        return (new MostPopularFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_most_popular, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mProgressBar = view.findViewById(R.id.spinner);

        initRetrofitService();
         return view;
    }

    private void initRetrofitService(){
        RetrofitService retrofitService = RetrofitClient.getInstance().create(RetrofitService.class);
        Call<MostPopularParentObject> call2 = retrofitService.getMostPopular();
        call2.enqueue(new Callback<MostPopularParentObject>() {
            @Override
            public void onResponse(Call<MostPopularParentObject> call, Response<MostPopularParentObject> response) {
                mProgressBar.setVisibility(View.GONE);
                initRecyclerViewMostPopular(response.body());
            }

            @Override
            public void onFailure(Call<MostPopularParentObject> call, Throwable t) {
                Log.e("TAG", t.getMessage());
            }
        });
    }

    private void initRecyclerViewMostPopular (MostPopularParentObject parentObject){
        MostPopularAdapter adapter = new MostPopularAdapter(parentObject.getResults(), getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(layoutManager);
    }

}
