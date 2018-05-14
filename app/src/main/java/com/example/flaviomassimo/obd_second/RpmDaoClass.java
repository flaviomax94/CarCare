package com.example.flaviomassimo.obd_second;

import java.util.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Flavio Massimo on 15/04/2018.
 */

public class RpmDaoClass implements RpmDao {
    private List<Rpm> RPM_LIST;
    public RpmDaoClass(){

        RPM_LIST= new LinkedList<Rpm>();
    }
    @Override
    public List<Rpm> getAll() {
        return RPM_LIST;
    }

    @Override
    public Rpm findByDate(long d) {

        Iterator<Rpm> it= RPM_LIST.iterator();
        while(it.hasNext()){
            Rpm val=it.next();
            if(val.getId()==d){
                 return val;
            }

        }
        return null;
    }

    @Override
    public void insertAll(List<Rpm> rpm_values) {
        Iterator<Rpm> it= rpm_values.iterator();
        while(it.hasNext()){
            Rpm val=it.next();
            RPM_LIST.add(val);
    }
    }

    @Override
    public void update(Rpm rpm) {
        Iterator<Rpm> it= RPM_LIST.iterator();
        while(it.hasNext()){
            Rpm val=it.next();
            if(val.getId()==rpm.getId()){
                val.setRpmValue(rpm.getRpmValue());
            }
        }
    }

    @Override
    public void delete(Rpm rpm) {
        Iterator<Rpm> it= RPM_LIST.iterator();
        while(it.hasNext()){
            Rpm val=it.next();
            if(val==rpm){
                RPM_LIST.remove(val);
            }
        }
    }

    @Override
    public void insert(Rpm rpm) {
        RPM_LIST.add(rpm);
    }
}
