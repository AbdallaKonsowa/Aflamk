package com.example.aflamk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexvasilkov.gestures.Settings;
import com.alexvasilkov.gestures.views.GestureImageView;
import com.example.aflamk.Database_Calss.Movie;
import com.example.aflamk.Database_Calss.MovieDB;
import com.example.aflamk.R;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    TextView tvName, Genre, OverView, MovieKind2;
    GestureImageView thePoster;
    boolean takeAllTheScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        //////////////////////////// hide The Header \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();
        //\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\\
        tvName = findViewById(R.id.tvName);
        Genre = findViewById(R.id.tvGenre);
        OverView = findViewById(R.id.tvOverView);
        thePoster = findViewById(R.id.posterImage);
        MovieKind2 = findViewById(R.id.movie_kind2);

        thePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        thePoster.getController().getSettings()
                .setMaxZoom(2f)
                .setDoubleTapZoom(-1f) // Falls back to max zoom level
                .setPanEnabled(true)
                .setZoomEnabled(true)
                .setDoubleTapEnabled(true)
                .setRotationEnabled(true)
                .setRestrictRotation(true)
                .setOverscrollDistance(0f, 0f)
                .setOverzoomFactor(2f)
                .setFillViewport(true)
                .setFitMethod(Settings.Fit.INSIDE)
                .setGravity(Gravity.CENTER);

        String name = getIntent().getStringExtra("name");
        Movie movie = MovieDB.getInstance(this).movieDAO().selectMovieByName(name);

        tvName.setText(movie.name);
        Genre.setText(movie.genre1);
        MovieKind2.setText(movie.kind);
        if (movie.genre2 != null)
            Genre.append(" " + movie.genre2);
        OverView.setText(movie.overview);

        Picasso.get().load(movie.poster).placeholder(R.drawable.logo2).into(thePoster);


    }

    public void goBack(View view) {
        finish();
    }
}