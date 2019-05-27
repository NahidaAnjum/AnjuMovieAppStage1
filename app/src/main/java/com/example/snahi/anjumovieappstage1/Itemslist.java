package com.example.snahi.anjumovieappstage1;

import android.os.Parcel;
import android.os.Parcelable;

class Itemslist implements Parcelable{
    
    String posterpath,id,title,date,rating,overview;
   

    public Itemslist(String posterpath, String id, String title, String date, String rating, String overview) {
        this.posterpath=posterpath;
        this.id=id;
        this.title=title;
        this.date=date;
        this.rating=rating;
        this.overview=overview;
    }

    protected Itemslist(Parcel in) {
        posterpath = in.readString();
        id = in.readString();
        title = in.readString();
        date = in.readString();
        rating = in.readString();
        overview = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterpath);
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(rating);
        dest.writeString(overview);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Itemslist> CREATOR = new Creator<Itemslist>() {
        @Override
        public Itemslist createFromParcel(Parcel in) {
            return new Itemslist(in);
        }

        @Override
        public Itemslist[] newArray(int size) {
            return new Itemslist[size];
        }
    };

    public String getPosterpath() {
        return "http://image.tmdb.org/t/p/w500/" +posterpath;
    }

    public void setPosterpath(String posterpath) {
        this.posterpath = posterpath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


}
