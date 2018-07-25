package com.example.vahan.movie5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.activity_main);

        mAdapter = new MovieAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        initMovieData();
        like();
    }

    private void like() {

    }

    private void initMovieData() {
        for (int i = 0; i < 20; ++i) {
            Movie movie = new Movie("Game of Thrones", "Fire and blood", R.drawable.got, 3.0f, true, "https://en.wikipedia.org/wiki/Game_of_Thrones");

            movieList.add(movie);
            mAdapter.notifyDataSetChanged();
        }
    }
}