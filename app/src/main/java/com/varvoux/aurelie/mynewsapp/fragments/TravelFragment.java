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

import com.varvoux.aurelie.mynewsapp.Adapter.TravelAdapter;
import com.varvoux.aurelie.mynewsapp.R;
import com.varvoux.aurelie.mynewsapp.retrofit.RetrofitClient;
import com.varvoux.aurelie.mynewsapp.retrofit.RetrofitService;
import com.varvoux.aurelie.mynewsapp.retrofit.TravelRequest.TravelParentObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TravelFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    public TravelFragment() {
        // Required empty public constructor
    }
    public static TravelFragment newInstance(){
        return (new TravelFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_travel, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mProgressBar = view.findViewById(R.id.spinner);


        initRetrofitServiceTravel();

        return view;
    }

    private void initRetrofitServiceTravel(){
        RetrofitService retrofitService = RetrofitClient.getInstance().create(RetrofitService.class);
        Call<TravelParentObject> call = retrofitService.getTravel();
        call.enqueue(new Callback<TravelParentObject>() {
            @Override
            public void onResponse(Call<TravelParentObject> call, Response<TravelParentObject> response) {
                mProgressBar.setVisibility(View.GONE);
                initRecyclerViewTravel(response.body());
            }

            @Override
            public void onFailure(Call<TravelParentObject> call, Throwable t) {
                Log.e("TAG",t.getMessage());
            }
        });
    }

    private void initRecyclerViewTravel(TravelParentObject parentObject){
        TravelAdapter adapter = new TravelAdapter(parentObject.getResponse().getDocs(),getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(layoutManager);

    }

}
