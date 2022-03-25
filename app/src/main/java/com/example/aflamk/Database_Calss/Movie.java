package com.example.aflamk.Database_Calss;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Movies")
public class Movie {

    @PrimaryKey(autoGenerate = true)
    public int id;


    public String name, genre1, genre2, poster, overview, kind;


}
