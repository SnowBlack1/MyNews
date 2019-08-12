package com.varvoux.aurelie.mynewsapp.retrofit.TravelRequest;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("docs")
    @Expose
    private List<TravelDoc> docs = null;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<TravelDoc> getDocs() {
        return docs;
    }

    public void setDocs(List<TravelDoc> docs) {
        this.docs = docs;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}