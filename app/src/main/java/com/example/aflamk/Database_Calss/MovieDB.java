package com.example.aflamk.Database_Calss;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.w3c.dom.Entity;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDB extends RoomDatabase {

    public abstract MovieDAO movieDAO();

    private static MovieDB ourInstance;

    public static MovieDB getInstance(Context context) {

        if (ourInstance == null) {

            ourInstance = Room.databaseBuilder(context,

                    MovieDB.class, "Movies.db")
                    .createFromAsset("dataBase/Movies.db")
                    .allowMainThreadQueries()
                    .build();
        }

        return ourInstance;

    }
}
