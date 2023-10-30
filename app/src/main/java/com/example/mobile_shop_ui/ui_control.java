package com.example.mobile_shop_ui;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class ui_control extends RecyclerView.Adapter<ui_control.ControlviewHolder> {
    massage_list[] massageList;
    Context context;
    public ui_control(massage_list[] massageList, Context context) {
        this.massageList = massageList;
        this.context = context;
    }
    @NonNull
    @Override
    public ControlviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.show_catcategory_ui,parent,false);
        return new ControlviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ControlviewHolder holder, int position) {
        massage_list massage = massageList[position];
        Glide.with(holder.show_cat_image.getContext()).load("http://192.168.0.105/mobile_shop/"+massage.getFile()).into(holder.show_cat_image);
        holder.show_cat_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), itemVIew.class);
                context.getApplicationContext().startActivity(intent);
            }
        });
        holder.category_name.setText(massage.getCategory_name());
    }

    @Override
    public int getItemCount() {
        return massageList.length;
    }
    public static class ControlviewHolder extends RecyclerView.ViewHolder {

        ImageView show_cat_image;
        TextView category_name;

        public ControlviewHolder(@NonNull View itemView) {
            super(itemView);
            show_cat_image = itemView.findViewById(R.id.show_cat_image);
            category_name = itemView.findViewById(R.id.category_name);
        }
    }
}