package com.example.aflamk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.aflamk.Database_Calss.MovieDB;
import com.example.aflamk.R;

import java.util.List;

public class Select_Movie_by_Genre extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    Spinner genreSpinner;
    ListView moveList;
    List<String> genre1;
    List<String> moviesName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_movie_by_genre);
        genreSpinner = findViewById(R.id.genreSpinner);
        //////////////////////////// hide The Header \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();
        //\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\\
        genre1 = MovieDB.getInstance(this).movieDAO().getGenre1();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, genre1);
        genreSpinner.setAdapter(adapter);
        genreSpinner.setOnItemSelectedListener(this);
        moveList = findViewById(R.id.LvMoviesName);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String Genre;
        Genre = genre1.get(position);
        //  moviesName.add("Please Select Genre");
        moviesName = MovieDB.getInstance(this).movieDAO().getMovieByGenre(Genre);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, moviesName);
        moveList.setAdapter(adapter);

        moveList.setOnItemClickListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String MovieName = moviesName.get(position);
        Intent intent = new Intent(Select_Movie_by_Genre.this, MovieDetailActivity.class);
        intent.putExtra("name", MovieName);
        startActivity(intent);
    }

    public void AllMovies(MenuItem item) {
        Intent intent = new Intent(Select_Movie_by_Genre.this, MainActivity.class);

        startActivity(intent);
    }
}