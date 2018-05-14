package com.example.flaviomassimo.obd_second;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.*;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.*;

public class MainActivityScreen extends AppCompatActivity {
    BluetoothSocket socket = BluetoothSocketShare.getBluetoothSocket();
    GPSListener locationListener;
    LocationManager locationManager;
    Intent intent;
    Button bluetoothDisconnect;
    Button bluetoothConnect ;
    ImageButton carButton ;
    ImageButton linegraphButton;
    TextView details,lineChart;
    ImageView carImage;
    View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        AppDatabase.setupDatabase(getApplicationContext());

        //starting db thread,creation of Database and file
        locationListener=new GPSListener();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        carImage=(ImageView) findViewById(R.id.ImageCar);
        details=(TextView) findViewById(R.id.textView6);
        lineChart=(TextView) findViewById(R.id.textView7);
        linegraphButton = (ImageButton) findViewById(R.id.lineButton);
        carButton = (ImageButton) findViewById(R.id.carButton);
        bluetoothConnect = (Button) findViewById(R.id.buttonConnect);
        bluetoothDisconnect=(Button)findViewById(R.id.buttonDisconnect);

        if(socket==null){

            bluetoothConnect.setEnabled(true);
            bluetoothConnect.setVisibility(View.VISIBLE);
            intent = new Intent(MainActivityScreen.this, BluetoothActivity.class);

            details.setVisibility(View.INVISIBLE);
            lineChart.setVisibility(View.INVISIBLE);
            carImage.setVisibility(View.VISIBLE);
            bluetoothDisconnect.setEnabled(false);
            bluetoothDisconnect.setVisibility(View.INVISIBLE);
            carButton.setEnabled(false);
            carButton.setVisibility(View.INVISIBLE);

            linegraphButton.setEnabled(false);
            linegraphButton.setVisibility(View.INVISIBLE);
        }

        else if(!socket.isConnected()){

            bluetoothConnect.setEnabled(true);
            bluetoothConnect.setVisibility(View.VISIBLE);
            intent = new Intent(MainActivityScreen.this, BluetoothActivity.class);
            bluetoothDisconnect.setEnabled(false);
            bluetoothDisconnect.setVisibility(View.INVISIBLE);
            carImage.setVisibility(View.VISIBLE);
            details.setVisibility(View.INVISIBLE);
            lineChart.setVisibility(View.INVISIBLE);

            carButton.setEnabled(false);
            carButton.setVisibility(View.INVISIBLE);

            linegraphButton.setEnabled(false);
            linegraphButton.setVisibility(View.INVISIBLE);
        }

        else if(socket.isConnected()){

            carImage.setVisibility(View.INVISIBLE);
            bluetoothConnect.setEnabled(false);
            bluetoothConnect.setVisibility(View.INVISIBLE);
            bluetoothDisconnect.setEnabled(true);
            bluetoothDisconnect.setVisibility(View.VISIBLE);
            carButton.setEnabled(true);
            carButton.setVisibility(View.VISIBLE);
            details.setVisibility(View.VISIBLE);
            lineChart.setVisibility(View.VISIBLE);
            linegraphButton.setEnabled(true);
            linegraphButton.setVisibility(View.VISIBLE);
        }

    }


    public void bluetoothDisconnectClick(View v){

        bluetoothDisconnect.setOnClickListener(new listenerBluetoothDisconnect());


    }

    public class listenerBluetoothDisconnect implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            BluetoothSocketShare.close();
            Intent i= new Intent(MainActivityScreen.this,MainActivityScreen.class);
            startActivity(i);
        }
    }


    public void bluetoothConnectClick(View v) {

        bluetoothConnect.setOnClickListener(new listenerBluetoothConnect());

    }

    public class listenerBluetoothConnect implements Button.OnClickListener {

        public void onClick(View v) {

            if ( locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
                startActivity(intent);
            }
            else {
                buildAlertMessageNoGps();

            }

        }
    }

    public void buttonClickCar(View v) {
        carButton.setOnClickListener(new listenerCar());

        if(socket==null){
            carButton.setEnabled(false);
        }
        else if(!socket.isConnected()){
            carButton.setEnabled(false);
        }
        else carButton.setEnabled(true);
    }

    public class listenerCar implements ImageButton.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivityScreen.this, CarActivity.class);
            startActivity(intent);
        }
    }

    public void buttonClickGraph(View v) {

        linegraphButton.setOnClickListener(new listenerGraph());

        if(socket==null){
            linegraphButton.setEnabled(false);
        }
        else if(!socket.isConnected()){
            linegraphButton.setEnabled(false);
        }
        else linegraphButton.setEnabled(true);
    }


    public class listenerGraph implements ImageButton.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivityScreen.this, GraphLineActivity.class);
            startActivity(intent);
        }
    }


    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogStyle);

        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                        Intent intent = new Intent(MainActivityScreen.this, BluetoothActivity.class);
                        startActivity(intent);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

}