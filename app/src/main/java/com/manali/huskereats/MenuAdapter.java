package com.manali.huskereats;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{

    private Context context;
    private List<MenuItem> itemList;

    public MenuAdapter(MenuActivity menuActivity, List<MenuItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.menu_item, null);
        MenuViewHolder menuViewHolder = new MenuViewHolder(view);
        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem menuItem = itemList.get(position);

        holder.itemName.setText(menuItem.getItemName());
        holder.foodImage.setImageDrawable(context.getResources().getDrawable(menuItem.getImage()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {

        ImageView foodImage;
        TextView itemName;

        public MenuViewHolder(View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.foodImage);
            itemName = itemView.findViewById(R.id.itemName);
        }
    }
}
