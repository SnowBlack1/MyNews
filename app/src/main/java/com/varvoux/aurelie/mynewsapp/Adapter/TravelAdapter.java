package com.varvoux.aurelie.mynewsapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.varvoux.aurelie.mynewsapp.R;
import com.varvoux.aurelie.mynewsapp.retrofit.TravelRequest.TravelDoc;

import java.util.List;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.TravelViewHolder> {

    private List<TravelDoc> myList;
    private Context context;
    private String url = "https://static01.nyt.com/";

    public TravelAdapter(List<TravelDoc> myList, Context context) {
        this.myList = myList;
        this.context = context;
        Log.e("TRAVEL",myList.size() + "");
    }

    @NonNull
    @Override
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);

        return new TravelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelAdapter.TravelViewHolder holder, int position) {

        if (myList != null && myList.size() > 0) {
            holder.title.setText((CharSequence) myList.get(position).getHeadline().getMain());

            if (myList.get(position).getMultimedia().size() > 0)
                Glide.with(context)
                        .load(url + myList.get(position).getMultimedia().get(0).getUrl())
//PENSER A GERER LE FAIT QUAND PAS D'IMAGES -> laisser en blanc ou mettre qq chose ?
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

    class TravelViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView title;

        public TravelViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
        }
    }
}
