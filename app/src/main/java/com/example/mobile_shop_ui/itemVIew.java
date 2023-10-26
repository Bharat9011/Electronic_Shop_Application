package com.example.mobile_shop_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

public class itemVIew extends AppCompatActivity {

    TextView orignalVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        orignalVal = findViewById(R.id.orignalVal);

//        SpannableString content = new SpannableString("Content");
//        content.setSpan(new UnderlineSpan(),0,content.length(),0);
//        orignalVal.setText("20,200");
        orignalVal.setPaintFlags(orignalVal.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);

    }
}