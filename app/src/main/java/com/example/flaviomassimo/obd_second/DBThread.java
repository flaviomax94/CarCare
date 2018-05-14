package com.example.flaviomassimo.obd_second;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;

import java.io.IOException;

/**
 * Created by Flavio Massimo on 15/04/2018.
 */

public class DBThread implements Runnable {
    private Context context;
    private AppDatabase database;

    public AppDatabase getDB(){

        if(database==null) {
            System.out.println("ATTENZIONEEEE DB NON CREATO");return null;}
            else return database;

    }


    public DBThread(Context c) {
        if (c != null) {
            context = c;
        } else throw new IllegalArgumentException("Application Context is null");

    }

    public void run() {
        System.out.println("Database in partenza");
        database = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "OBD_DB").build();
        System.out.println(database);
        System.out.println("Database partito");
    }

}