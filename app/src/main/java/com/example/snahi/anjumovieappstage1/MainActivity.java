package com.example.snahi.anjumovieappstage1;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Itemslist> list;
    int orientation;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.recycler);
        sharedPreferences = getSharedPreferences("Mydatastore", MODE_PRIVATE);
        String sort = sharedPreferences.getString("key", "pop");
        if (savedInstanceState != null) {
            list = savedInstanceState.getParcelableArrayList("lists");
            rv.setAdapter(new Adapter(MainActivity.this, list));
            orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                rv.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
            } else {
                rv.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
            }
        } else {
            if (sort.equals("pop")) {
                Popular popular = new Popular();
                popular.execute();

            } else if (sort.equals("top")) {
                Toprated toprated = new Toprated();
                toprated.execute();

            }
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popular:
                Popular popular = new Popular();
                popular.execute();
                editor = sharedPreferences.edit();
                editor.putString("key", "pop");
                editor.apply();
                break;
            case R.id.toprated:
                Toprated toprated = new Toprated();
                toprated.execute();
                editor = sharedPreferences.edit();
                editor.putString("key", "top");
                editor.apply();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public class Popular extends AsyncTask<String, Void, String> {
        String myurl = "https://api.themoviedb.org/3/movie/popular?api_key=b1cc69d776ede063d70e6b50949a6c3a";


        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(myurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = " ";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append("\n" + line);
                }
                return stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            list = new ArrayList<>();
            //Toast.makeText(MainActivity.this, "" + s, Toast.LENGTH_SHORT).show();
            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("results");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object1 = array.getJSONObject(i);
                    String posterpath = object1.optString("poster_path");
                    String id = object1.optString("id");
                    String title = object1.optString("title");
                    String date = object1.optString("release_date");
                    String rating = object1.optString("vote_average");
                    String overview = object1.optString("overview");
                    Toast.makeText(MainActivity.this, "" + posterpath, Toast.LENGTH_SHORT).show();
                    Itemslist items = new Itemslist(posterpath, id, title, date, rating, overview);
                    list.add(items);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            rv.setAdapter(new Adapter(MainActivity.this, list));
            int ore = getResources().getConfiguration().orientation;
            if (ore == Configuration.ORIENTATION_LANDSCAPE) {
                rv.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
            } else {
                rv.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
            }

            super.onPostExecute(s);
        }
    }

    public class Toprated extends AsyncTask<String, Void, String> {
        String myurl = "https://api.themoviedb.org/3/movie/top_rated?api_key=b1cc69d776ede063d70e6b50949a6c3a";

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(myurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append("\n" + line);
                }
                return stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            list = new ArrayList<>();
            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("results");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object1 = array.getJSONObject(i);
                    String posterpath = object1.optString("poster_path");
                    String id = object1.optString("id");
                    String title = object1.optString("title");
                    String date = object1.optString("release_date");
                    String rating = object1.optString("vote_average");
                    String overview = object1.optString("overview");
                    Itemslist items = new Itemslist(posterpath, id, title, date, rating, overview);
                    list.add(items);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            rv.setAdapter(new Adapter(MainActivity.this, list));
            int ore = getResources().getConfiguration().orientation;
            if (ore == Configuration.ORIENTATION_LANDSCAPE) {
                rv.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
            } else {
                rv.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
            }

            super.onPostExecute(s);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("lists", list);
    }
}



