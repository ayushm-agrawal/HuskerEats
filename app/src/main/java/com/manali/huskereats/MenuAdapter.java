package com.manali.huskereats;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{

    private Context context;
    private List<MenuItem> itemList;
    private List<String> orderList = new ArrayList<String>();

    public MenuAdapter(Context context, List<MenuItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.menu_item, parent, false);
        MenuViewHolder menuViewHolder = new MenuViewHolder(view);
        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuViewHolder holder, int position) {
        MenuItem menuItem = itemList.get(position);

        holder.itemName.setText(menuItem.getItemName());
        holder.foodImage.setImageDrawable(context.getResources().getDrawable(menuItem.getImage()));
        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderList.add(holder.itemName.getText().toString());
                Toast.makeText(view.getContext(), holder.itemName.getText().toString() + " added to cart!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {

        ImageView foodImage;
        TextView itemName;
        CardView itemCard;
        FloatingActionButton shoppingCart;

        public MenuViewHolder(View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.foodImage);
            itemName = itemView.findViewById(R.id.itemName);
            itemCard = itemView.findViewById(R.id.itemCard);
        }
    }
}
