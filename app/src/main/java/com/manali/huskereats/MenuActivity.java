package com.manali.huskereats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MenuAdapter menuAdapter;

    List<MenuItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        itemList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerMenu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList.add(new MenuItem(1, "Spicy Italian", R.drawable.spicy_italian));
        itemList.add(new MenuItem(1, "Sweet Onion Teriyaki", R.drawable.sweet_onion_teriyaki));

        menuAdapter = new MenuAdapter(this, itemList);
        recyclerView.setAdapter(menuAdapter);

    }
}
