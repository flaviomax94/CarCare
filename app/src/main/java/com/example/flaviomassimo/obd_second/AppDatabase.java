package com.example.flaviomassimo.obd_second;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Flavio Massimo on 12/04/2018.
 */

@Database(entities = {Rpm.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public RpmDao rpmDao=new RpmDaoClass();

    private static DBThread database;
    private static Thread thread_db;
    static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase() {

        INSTANCE =database.getDB();
        return INSTANCE;

    }
    public static void setupDatabase(Context context){
        if (INSTANCE == null) {
            database=new DBThread(context);
            thread_db= new Thread(database);
            thread_db.start();}

    }

    public static void destroyInstance() {
        INSTANCE = null;
        thread_db.interrupt();

    }

}
