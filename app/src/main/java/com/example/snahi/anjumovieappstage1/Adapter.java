package com.example.snahi.anjumovieappstage1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;


public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {
    Context ct;
    ArrayList<Itemslist> list1;

    public Adapter(Context ct, ArrayList<Itemslist> list) {
        this.ct = ct;
        this.list1 = list;
    }

    @NonNull
    @Override
    public Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(ct).inflate(R.layout.items, viewGroup, false);
        Viewholder holder = new Viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.Viewholder viewholder, int i) {
/*Itemslist list=list1.get(i);
String imagelink;
imagelink=list.getPosterpath();
        Log.i("link",imagelink.toString());*/

        Picasso.with(ct).load(list1.get(i).getPosterpath()).placeholder(R.mipmap.ic_launcher_round).into(viewholder.iv);
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView iv;

        public Viewholder(@NonNull View itemView) {

            super(itemView);
            iv = itemView.findViewById(R.id.im);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(ct, DetailsActivity.class);
                    intent.putExtra("poster_path", list1.get(pos).getPosterpath());
                    intent.putExtra("key", list1.get(pos).getId());
                    intent.putExtra("title", list1.get(pos).getTitle());
                    intent.putExtra("date", list1.get(pos).getDate());
                    intent.putExtra("rating", list1.get(pos).getRating());
                    intent.putExtra("overview", list1.get(pos).getOverview());
                    ct.startActivity(intent);
                }
            });
        }
    }
}
