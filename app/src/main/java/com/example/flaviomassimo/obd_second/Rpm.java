package com.example.flaviomassimo.obd_second;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Flavio Massimo on 12/04/2018.
 */
@Entity(tableName = "RPM")
public class Rpm {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "rpm_value")
    private String rpmValue;

    @ColumnInfo(name = "fuel_type")
    private String fuelType;

    @ColumnInfo(name = "speed")
    private String speed;

    @ColumnInfo(name = "position")
    private String position;

    @ColumnInfo(name = "date")
    private String date;



    public long getId(){ return id;}

    public void setId(long val){
        this.id=val;
    }




    public String getRpmValue(){
        return rpmValue;
    }

    public void setRpmValue(String val){
        this.rpmValue=val;
    }




    public String getFuelType(){
        return fuelType;
    }

    public void setFuelType(String val){
        this.fuelType=val;
    }





    public String getSpeed(){
        return speed;
    }

    public void setSpeed(String val){
        this.speed=val;
    }




    public String getDate(){
        return date;
    }

    public void setDate(String d){
        this.date=d;
    }



    public String getPosition(){
        return position;
    }

    public void setPosition(String pos){
        this.position=pos;
    }












}
