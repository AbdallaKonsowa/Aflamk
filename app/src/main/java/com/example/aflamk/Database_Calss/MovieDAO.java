package com.example.aflamk.Database_Calss;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDAO {

    //DAO ==> Data access object

    @Query("SELECT DISTINCT genre1 from Movies where genre1<>'null' ORDER BY genre1")
    List<String> getGenre1();

    @Query("SELECT DISTINCT name from Movies where genre1=:Genre ORDER BY name")
    List<String> getMovieByGenre(String Genre);

    @Query("select * from Movies where name=:name")
    Movie selectMovieByName(String name);


    @Query("SELECT * from Movies")
    List<Movie> selectAllMovies();

    @Query("SELECT * from Movies order by name")
    List<Movie> selectAllMoviesOrderByName();

    @Query("SELECT * from Movies order by genre1")
    List<Movie> selectAllMoviesOrderByGenre();

    @Query("SELECT * from Movies order by kind")
    List<Movie> selectAllMoviesOrderByKind();


}
