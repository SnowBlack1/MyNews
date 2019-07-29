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
import com.varvoux.aurelie.mynewsapp.R;
import com.varvoux.aurelie.mynewsapp.retrofit.TopStoriesRequest.TopStoriesPojo;

import java.util.List;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.TopStoriesViewHolder>{

    private List<TopStoriesPojo> myList;
    private Context context;


    public TopStoriesAdapter(List<TopStoriesPojo> myList, Context context) {
        this.myList = myList;
        this.context = context;
    }

    @NonNull
    @Override
    public TopStoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new TopStoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopStoriesViewHolder holder, int position) {
        if (myList != null && myList.size() > 0) {
            holder.title.setText((CharSequence) myList.get(position).getTitle());

            if (myList.get(position).getMultimedia().size() > 0)
                Glide.with(context)
                        .load(myList.get(position).getMultimedia().get(0).getUrl())
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

    class TopStoriesViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView title;

        public TopStoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
        }
    }
}
