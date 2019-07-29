package com.varvoux.aurelie.mynewsapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.varvoux.aurelie.mynewsapp.retrofit.MostPopularRequest.Result;
import com.varvoux.aurelie.mynewsapp.R;

import java.util.List;

public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularAdapter.MostPopularViewHolder> {
    private List<Result> myList;
    private Context context;


    public MostPopularAdapter(List<Result> myList, Context context) {
        this.myList = myList;
        this.context = context;
    }

    @NonNull
    @Override
    public MostPopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new MostPopularViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MostPopularViewHolder holder, int position) {
        if (myList != null && myList.size() > 0) {
            holder.title.setText((CharSequence) myList.get(position).getTitle());

            Glide.with(context)
                    .load(myList.get(position).getMedia().get(0).getMediaMetadata().get(0).getUrl())
                    .placeholder(android.R.drawable.ic_input_add)
                    .circleCrop()
                    .into(holder.mImageView);
        }

    }

    @Override
    public int getItemCount() {
        if (myList != null)
            return myList.size();
        else return 0;
    }

    class MostPopularViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView title;

        public MostPopularViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
        }

    }
}
