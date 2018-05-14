package com.example.flaviomassimo.obd_second;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.Date;
import java.util.List;

/**
 * Created by Flavio Massimo on 15/04/2018.
 */
@Dao
public interface RpmDao {

    @Query("SELECT * FROM RPM")
    List<Rpm> getAll();

    @Query("SELECT * FROM RPM WHERE id LIKE :d LIMIT 1")
    Rpm findByDate(long d);
    @Insert
    void insertAll(List<Rpm> rpm_values);

    @Update
    void update(Rpm rpm);

    @Delete
    void delete(Rpm rpm);

    void insert(Rpm rpm);
}
