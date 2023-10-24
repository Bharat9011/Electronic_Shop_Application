package com.example.mobile_shop_ui;

import android.app.Fragment;
import android.os.Bundle;


import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HomeFragment extends Fragment {

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 3000;
    ImageView imgLogo;
    int silder_image_count = 1;
    RecyclerView RecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_fragmebt, container, false);

        imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
        RecyclerView = view.findViewById(R.id.RecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.setLayoutManager(layoutManager);

        String backEndUrl = "http://192.168.0.113/mobile_shop/code.php?cmd=getData";
        StringRequest mrequest = new StringRequest(Request.Method.POST, backEndUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                massage_list []massageLists = gson.fromJson(response, massage_list[].class);
                RecyclerView.setAdapter(new ui_control(massageLists, view.getContext()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(view.getContext(), error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        requestQueue.add(mrequest);

        return view;
    }
    public void onResume() {
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                imgLogo.animate()
                        .translationX(imgLogo.getWidth())
                        .setDuration(500)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                if (silder_image_count == 0)
                                    imgLogo.setImageResource(R.drawable.slider1);
                                if (silder_image_count == 1)
                                    imgLogo.setImageResource(R.drawable.slider2);
                                if (silder_image_count == 2)
                                    imgLogo.setImageResource(R.drawable.slider3);
                                if (silder_image_count == 3) {
                                    imgLogo.setImageResource(R.drawable.slider4);
                                    silder_image_count = -1;
                                }
                                silder_image_count++;
                                imgLogo.animate()
                                        .translationX(0)
                                        .setDuration(500);
                            }
                        })
                        .start();
            }
        }, delay);
        super.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}