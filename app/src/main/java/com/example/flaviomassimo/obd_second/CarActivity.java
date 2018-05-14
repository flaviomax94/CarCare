package com.example.flaviomassimo.obd_second;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.*;

public class CarActivity extends AppCompatActivity {

    private Thread changingValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        AppDatabase adb =AppDatabase.getAppDatabase();


        BluetoothSocket socket=BluetoothSocketShare.getBluetoothSocket();
        TextView RPMtext = (TextView) findViewById(R.id.insertRPM);
        TextView SPEEDtext = (TextView) findViewById(R.id.insertKM);

        if(socket!=null){
         changingValues= new Thread((new ChangingValuesThread(RPMtext,SPEEDtext)));
         changingValues.start();
        }
        else{
            RPMtext.setText("------");
            SPEEDtext.setText("------km/h");

        }
    }

    public void btnGraphClick(View view){
        Button btnGraph= (Button) findViewById(R.id.buttonGraph);
        btnGraph.setOnClickListener(new graphListener());

    }

    private class graphListener implements Button.OnClickListener{

        @Override
        public void onClick(View view) {
            if(changingValues!=null) {
                if(changingValues.isAlive())
                changingValues.interrupt();
            }

            Intent intent = new Intent(CarActivity.this, GraphLineActivity.class);
            startActivity(intent);
            System.out.println("GraphActivity in partenza");
        }
    }
    public void homeButtonClick(View view){

        ImageButton homeButton=(ImageButton) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new homeBtnListener());
    }

    public class homeBtnListener implements ImageButton.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(CarActivity.this, MainActivityScreen.class);
            startActivity(intent);
        }
    }
}
