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

import com.varvoux.aurelie.mynewsapp.Adapter.TopStoriesAdapter;
import com.varvoux.aurelie.mynewsapp.R;
import com.varvoux.aurelie.mynewsapp.retrofit.TopStoriesRequest.TopStoriesParentObject;
import com.varvoux.aurelie.mynewsapp.retrofit.RetrofitClient;
import com.varvoux.aurelie.mynewsapp.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    public TopStoriesFragment() {
        // Required empty public constructor
    }
    public static TopStoriesFragment newInstance(){
        return (new TopStoriesFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_stories, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mProgressBar = view.findViewById(R.id.spinner);

        initService();

        return view;
    }

    private void initService() {
        RetrofitService retrofitService = RetrofitClient.getInstance().create(RetrofitService.class);
        Call<TopStoriesParentObject> call = retrofitService.getTopStories();
        call.enqueue(new Callback<TopStoriesParentObject>() {
            @Override
            public void onResponse(Call<TopStoriesParentObject> call, Response<TopStoriesParentObject> response) {
                mProgressBar.setVisibility(View.GONE);
                initRecyclerViewTopStories(response.body());
            }

            @Override
            public void onFailure(Call<TopStoriesParentObject> call, Throwable t) {
                Log.e("TAG",t.getMessage());
            }
        });
    }

    private void initRecyclerViewTopStories(TopStoriesParentObject parentObject){
        TopStoriesAdapter adapter = new TopStoriesAdapter(parentObject.getResults(),getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(layoutManager);
    }
}
