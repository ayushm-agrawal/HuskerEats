package com.manali.huskereats;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private Context context;
    private List<Restaurants> data;

    public RecyclerViewAdapter(Context context, List<Restaurants> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.card_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void  onBindViewHolder(ViewHolder holder, int position) {

        holder.txt_restaurant.setText(data.get(position).getRestaurant_name());
        holder.restaurant_thumbnail.setImageResource(data.get(position).getThumbnail());
        holder.cardView_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Put the intent to
                Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(v.getContext(), MenuActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_restaurant;
        ImageView restaurant_thumbnail;
        CardView cardView_home;

         ViewHolder(View item) {
            super(item);

            txt_restaurant = itemView.findViewById(R.id.listRestaurantName);
            restaurant_thumbnail = itemView.findViewById(R.id.listImg);
            cardView_home = itemView.findViewById(R.id.card_view_home_page);
        }
    }
}
