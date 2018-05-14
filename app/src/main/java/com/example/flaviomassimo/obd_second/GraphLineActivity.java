package com.example.flaviomassimo.obd_second;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.*;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class GraphLineActivity extends AppCompatActivity {
    AppDatabase adb=AppDatabase.getAppDatabase();
    LinkedList<Rpm> rpmList=(LinkedList)adb.rpmDao.getAll();
    private LineChart mChart;
    public static LineDataSet dataset=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_line);
        mChart= findViewById(R.id.linechart);
        mChart.setBackgroundColor(Color.WHITE);
        ArrayList<Entry> entries= new ArrayList<>();
        int i=0,j=0;
        if(rpmList.isEmpty()) popolateGaph();
        for(Rpm r: rpmList){
            j++;
            i++;
            float val=Float.parseFloat(r.getRpmValue());
            if(j==5){
            entries.add(new Entry(i,val));
                j=0;
            }
        }


        dataset= new LineDataSet(entries,"RPM Graph values (one per second)");

        LineData data= new LineData(dataset);
        Description d= new Description();
        Rpm first= rpmList.getFirst();
        String fuelDB=first.getFuelType();
        d.setText(fuelDB);
        d.setTextSize(15);
        mChart.setData(data);
        mChart.setDescription(d);
        mChart.setPinchZoom(true);
        mChart.setDragEnabled(true);
        mChart.setDoubleTapToZoomEnabled(true);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        mChart.setDrawBorders(true);

    }

    public void homeButtonClick(View view){

        ImageButton homeButton=(ImageButton) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new homeBtnListener());
    }

    public class homeBtnListener implements ImageButton.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(GraphLineActivity.this, MainActivityScreen.class);
            startActivity(intent);
        }
    }

    public static void addValuetoGraph(Entry entry){
        if(!entry.equalTo(null)){
            dataset.addEntry(entry);

        }

    }

    private void popolateGaph(){

        Rpm val1= new Rpm();
        Rpm val2= new Rpm();
        Rpm val3= new Rpm();
        Rpm val4= new Rpm();
        Rpm val5= new Rpm();
        Rpm val6= new Rpm();
        Rpm val7= new Rpm();
        Rpm val8= new Rpm();
        String fuel;
        if(BluetoothSocketShare.getFuelType()!=null)
        fuel=BluetoothSocketShare.getFuelType();
        else fuel="NULL";
        val1.setFuelType(fuel);

        List<Rpm> l= new ArrayList<>();
        val1.setId(Calendar.getInstance().getTime().getTime());
        val1.setRpmValue("2500");

        l.add(val1);

        val2.setId(Calendar.getInstance().getTime().getTime()+1);
        val2.setRpmValue("3000");

        l.add(val2);

        val3.setId(Calendar.getInstance().getTime().getTime()+2);
        val3.setRpmValue("2000");
        l.add(val3);

        val4.setId(Calendar.getInstance().getTime().getTime()+3);
        val4.setRpmValue("4000");
        l.add(val4);

        val5.setId(Calendar.getInstance().getTime().getTime()+4);
        val5.setRpmValue("5000");
        l.add(val5);

        val6.setId(Calendar.getInstance().getTime().getTime()+5);
        val6.setRpmValue("2500");
        l.add(val6);

        val7.setId(Calendar.getInstance().getTime().getTime()+6);
        val7.setRpmValue("2500");
        l.add(val7);

        val8.setId(Calendar.getInstance().getTime().getTime()+7);
        val8.setRpmValue("1000");
        l.add(val8);

        adb.rpmDao.insertAll(l);

    }
}
