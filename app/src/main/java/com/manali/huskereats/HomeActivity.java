package com.manali.huskereats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<Restaurants> listRestaurants;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar1);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Users");


        //adding the list of restaurants for the recycler view
        listRestaurants = new ArrayList<>();

        listRestaurants.add(new Restaurants("Amigos", R.mipmap.amigos));
        listRestaurants.add(new Restaurants("Arby's", R.mipmap.arbys));
        listRestaurants.add(new Restaurants("Chick-fil-A", R.mipmap.chick_fil_a));
        listRestaurants.add(new Restaurants("Chipotle", R.mipmap.chipotle));
        listRestaurants.add(new Restaurants("Imperial Palace", R.mipmap.imperial_palace));
        listRestaurants.add(new Restaurants("Panda", R.mipmap.panda_express));
        listRestaurants.add(new Restaurants("Starbucks", R.mipmap.starbucks));
        listRestaurants.add(new Restaurants("Subway", R.mipmap.subway_logo));
        listRestaurants.add(new Restaurants("Valentinos", R.mipmap.valentinos));
        listRestaurants.add(new Restaurants("Wendy's", R.mipmap.wendys));

        RecyclerView recycler_view = findViewById(R.id.listRestaurant);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter( this, listRestaurants);

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(adapter);

        //This handles th side bar
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        FirebaseUser user = mAuth.getCurrentUser();

        assert user != null;
        mRef.child("Users").child(user.getUid());

        final TextView navTxtUserName = headerView.findViewById(R.id.usrName);
        final TextView navTxtUserEmail = headerView.findViewById(R.id.usrEmail);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                navTxtUserName.setText(name);
                navTxtUserEmail.setText(mAuth.getCurrentUser().getEmail().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent profileIntent = new Intent(this, ProfileActivity.class);
            startActivity(profileIntent);
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            FirebaseAuth.getInstance().signOut();
            startActivity(loginIntent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
