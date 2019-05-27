package com.example.snahi.anjumovieappstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
ImageView iv;
TextView tv1,tv2,tv3,tv4;
String poster,title,date,rating,overview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        iv=findViewById(R.id.poster);
        tv1=findViewById(R.id.title);
        tv2=findViewById(R.id.date);
        tv3=findViewById(R.id.rating);
        tv4=findViewById(R.id.overview);

        Intent intent=getIntent();
        poster=intent.getStringExtra("poster_path");
        title=intent.getStringExtra("title");
        date=intent.getStringExtra("date");
        rating=intent.getStringExtra("rating");
        overview=intent.getStringExtra("overview");
         this.setTitle(title);
        tv1.setText(title);
        tv2.setText(date);
        tv3.setText(rating);
        tv4.setText(overview);
        Picasso.with(DetailsActivity.this).load(poster).placeholder(R.mipmap.ic_launcher_round).into(iv);
    }
    }
