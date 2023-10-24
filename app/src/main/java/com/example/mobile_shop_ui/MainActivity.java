package com.example.mobile_shop_ui;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    BottomNavigationView Bottom_navigation;
    ImageView imgBtnlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.included);
        Bottom_navigation = findViewById(R.id.Bottom_navigation);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        imgBtnlogo = (ImageView) findViewById(R.id.imgBtnlogo);

        FragmentLoding(new HomeFragment());

        Bottom_navigation.setOnNavigationItemSelectedListener(nav);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener nav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.Home) {
                FragmentLoding(new HomeFragment());
            } else if(item.getItemId() == R.id.Search){
                FragmentLoding(new SearchFragment());
            } else if(item.getItemId() == R.id.Category){
                FragmentLoding(new CategoryFragment());
            } else if(item.getItemId() == R.id.Profile){
                FragmentLoding(new ProfileFragment());
            }
            return true;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarlist, menu);

        MenuItem searchItemView = menu.findItem(R.id.search);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItemView);
        searchView.setQueryHint("spare Parts");

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.findViewById(R.id.imgBtnlogo).setVisibility(View.GONE);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                toolbar.findViewById(R.id.imgBtnlogo).setVisibility(View.VISIBLE);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int optionItem = item.getItemId();

        if (optionItem == R.id.search) {
            FragmentLoding(new SearchFragment());
        }

        if (optionItem == R.id.notification) {
//            imgBtnlogo.setVisibility(View.GONE);
            Toast.makeText(this, "notification", Toast.LENGTH_SHORT).show();
        }

        return true;
    }



    void FragmentLoding(Fragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.LoadingFrag,fragment);
        ft.commit();
    }
}