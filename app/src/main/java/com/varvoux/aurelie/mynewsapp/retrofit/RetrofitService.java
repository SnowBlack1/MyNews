package com.varvoux.aurelie.mynewsapp.retrofit;

import com.varvoux.aurelie.mynewsapp.retrofit.MostPopularRequest.MostPopularParentObject;
import com.varvoux.aurelie.mynewsapp.retrofit.TopStoriesRequest.TopStoriesParentObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("/svc/topstories/v2/home.json?api-key=EjFoG6oBEBGEC5vowClsef2PQiayA4Tz")
    Call<TopStoriesParentObject> getTopStories();

    @GET("/svc/mostpopular/v2/emailed/1.json?api-key=EjFoG6oBEBGEC5vowClsef2PQiayA4Tz")
    Call<MostPopularParentObject> getMostPopular();
}
