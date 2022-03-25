package com.example.aflamk.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aflamk.Database_Calss.Movie;
import com.example.aflamk.Database_Calss.MovieDB;
import com.example.aflamk.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView list;
    List<Movie> allMovies;
    Movie SelectedMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list);

        allMovies = MovieDB.getInstance(this).movieDAO().selectAllMovies();
        AllMovieAdapter adapter = new AllMovieAdapter(this, allMovies);
        list.setAdapter(adapter);

        list.setOnItemClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SelectedMovie = allMovies.get(position);
        // Toast.makeText(this, SelectedMovie.name+"", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("name", SelectedMovie.name);
        startActivity(intent);

        //   Toast.makeText(this, "his id is: "+id, Toast.LENGTH_SHORT).show();
    }

    public void MenuClick(MenuItem item) {
        //
        if (item.getTitle().equals("Sort by Name")) {
            allMovies.clear();
            allMovies = MovieDB.getInstance(this).movieDAO().selectAllMoviesOrderByName();
            AllMovieAdapter adapter = new AllMovieAdapter(this, allMovies);
            list.setAdapter(adapter);

        } else if (item.getTitle().equals("Sort by Genre")) {
            allMovies.clear();
            allMovies = MovieDB.getInstance(this).movieDAO().selectAllMoviesOrderByGenre();
            AllMovieAdapter adapter = new AllMovieAdapter(this, allMovies);
            list.setAdapter(adapter);
        } else if (item.getTitle().equals("Shuffle Sort")) {
            //allMovies.clear();
            //allMovies = MovieDB.getInstance(this).movieDAO().selectAllMovies();
            Collections.shuffle(allMovies);
            AllMovieAdapter adapter = new AllMovieAdapter(this, allMovies);
            list.setAdapter(adapter);
        } else if (item.getTitle().equals("Sort by Kind")) {
            allMovies.clear();
            allMovies = MovieDB.getInstance(this).movieDAO().selectAllMoviesOrderByKind();
            AllMovieAdapter adapter = new AllMovieAdapter(this, allMovies);
            list.setAdapter(adapter);
        } else if (item.getTitle().equals("Search by Name")) {
            Intent intent = new Intent(this, NameSearch.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, Select_Movie_by_Genre.class);
            startActivity(intent);
        }
    }


    class AllMovieAdapter extends ArrayAdapter<Movie> {

        public AllMovieAdapter(@NonNull Context context, List<Movie> allMovies) {
            super(context, 0, allMovies);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // 1 inflation
            ViewHolder holder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.custome_all_movies, parent, false);
                holder = new ViewHolder(convertView);

                convertView.setTag(holder);
            } else {

                holder = (ViewHolder) convertView.getTag();


            }
            // 2 fill data
            holder.CV_MovieName.setText(getItem(position).name);
            holder.CV_MovieKind.setText(getItem(position).kind);
            if (getItem(position).genre2 != null)
                holder.CV_MovieGenre.setText(getItem(position).genre1 + getItem(position).genre2);
            else
                holder.CV_MovieGenre.setText(getItem(position).genre1);

//            holder.CV_MovieGenre.append(getItem(position).genre2);

            Picasso.get().load(getItem(position).poster).placeholder(R.drawable.logo2).into(holder.CV_MoviePoster);

            return convertView;

        }

        class ViewHolder {
            TextView CV_MovieName, CV_MovieGenre, CV_MovieKind;
            ImageView CV_MoviePoster;

            public ViewHolder(View convertView) {
                CV_MovieName = convertView.findViewById(R.id.CV_MovieName);
                CV_MovieGenre = convertView.findViewById(R.id.CV_MovieGenre);
                CV_MoviePoster = convertView.findViewById(R.id.CV_Poster);
                CV_MovieKind = convertView.findViewById(R.id.CV_kind);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return true;
    }
}


