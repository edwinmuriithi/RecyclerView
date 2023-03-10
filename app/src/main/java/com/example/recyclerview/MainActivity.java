 package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.recyclerview.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<String> moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        moviesList = new ArrayList<>();
        recyclerAdapter = new RecyclerAdapter(moviesList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        binding.recyclerView.addItemDecoration(dividerItemDecoration);

        moviesList.add("1. Iron Man (2008)");
        moviesList.add("2. The Incredible Hulk (2008)");
        moviesList.add("3. Iron Man 2 (2010)");
        moviesList.add("4. Thor (2011)");
        moviesList.add("5. Captain America: The First Avenger (2011)");
        moviesList.add("6. The Avengers (2012)");
        moviesList.add("7. Iron Man 3 (2013)");
        moviesList.add("8. Thor: The Dark World (2013)");
        moviesList.add("9. Captain America: The Winter Soldier (2014)");
        moviesList.add("10. Guardians of the Galaxy (2014)");

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviesList.add("11. Avengers: Age of Ultron");
                moviesList.add("12. Ant-man");
                moviesList.add("13. Captain America: Civil War");
                moviesList.add("14. Doctor Strange");
                recyclerAdapter.notifyDataSetChanged();
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.main_menu, menu);
         MenuItem item = menu.findItem(R.id.action_search);
         SearchView searchView = (SearchView) item.getActionView();
         searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String query) {
                 return false;
             }

             @Override
             public boolean onQueryTextChange(String newText) {
                 recyclerAdapter.getFilter().filter(newText);
                 return false;
             }
         });

        return super.onCreateOptionsMenu(menu);
     }
 }