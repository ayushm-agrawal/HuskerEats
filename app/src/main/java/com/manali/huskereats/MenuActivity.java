package com.manali.huskereats;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MenuAdapter menuAdapter;
    Button placeOrderButton;

    List<MenuItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        itemList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerMenu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        placeOrderButton = findViewById(R.id.btnPlaceOrder);


        itemList.add(new MenuItem(1, "Spicy Italian", R.drawable.spicy_italian));
        itemList.add(new MenuItem(1, "Sweet Onion Teriyaki", R.drawable.sweet_onion_teriyaki));
        itemList.add(new MenuItem(1, "Oven Roasted Chicken", R.drawable.oven_roasted_chicken));
        itemList.add(new MenuItem(1, "Meatball Marinanra", R.drawable.meatball_marinara));

        menuAdapter = new MenuAdapter(this, itemList);
        recyclerView.setAdapter(menuAdapter);
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "Hello Bois!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(view.getContext(), MapsActivity.class));
            }
        });

    }
}
