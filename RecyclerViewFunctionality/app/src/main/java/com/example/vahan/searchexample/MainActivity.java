package com.example.vahan.searchexample;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyAdapter adapter;
    public List<User> users;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        users = new ArrayList<>();

        initUsersList(users);

        final RecyclerView recyclerView = findViewById(R.id.myRecView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);

        adapter = new MyAdapter(users, this);
        recyclerView.setAdapter(adapter);
    }

    private void initUsersList(List<User> users) {
        for (int i = 0; i < 3; ++i) {
            users.add(new User(getString(R.string.vahan), getString(R.string.Vahan_name), getString(R.string.mail), getString(R.string.number_phone), getString(R.string.Baelish)));
            users.add(new User(getString(R.string.astghik), getString(R.string.Astghik_name), getString(R.string.mail), getString(R.string.number_phone), getString(R.string.Baelish)));
            users.add(new User(getString(R.string.astghik), getString(R.string.Astghik_name), getString(R.string.mail), getString(R.string.number_phone), getString(R.string.Baelish)));
            users.add(new User(getString(R.string.astghik), getString(R.string.Astghik_name), getString(R.string.mail), getString(R.string.number_phone), getString(R.string.Baelish)));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}