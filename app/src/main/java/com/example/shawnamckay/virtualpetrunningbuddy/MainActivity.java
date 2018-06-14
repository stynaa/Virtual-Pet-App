package com.example.shawnamckay.virtualpetrunningbuddy;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener, com.example.christinalo.myapplication.StepListener {
    private TextView TvSteps;
    private com.example.christinalo.myapplication.StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Pet cat = new Pet();

        mainMenu();

    }


    public void mainMenu(){
        //activity main buttons
        ImageButton petButton = (ImageButton)this.findViewById(R.id.catPullUpButton);
        Button settingsButton = (Button)this.findViewById(R.id.settingsButton);
        Button startRunButton = (Button)this.findViewById(R.id.startRunButton);



            //When the user clicks the pet button
            petButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setContentView(R.layout.pet_stats);
                    petStatsMenu();
                }
            });

            //When the user clicks the settings button
            settingsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setContentView(R.layout.settings);
                    settingsMenu();
                }
            });


        startRunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.step);
                runMenu();
            }
        });



    }


    public void petStatsMenu(){
        //Pet Stats Buttons
        Button feedPetButton = (Button)this.findViewById(R.id.feedPetButton);
        Button petStatBackButton = (Button)this.findViewById(R.id.petStatsBackButton);

        //When user clicks feed pet button
        feedPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To be filled in
            }
        });

        //When user clicks back in the pet stats menu
        petStatBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                mainMenu();
            }
        });
    }


    public void settingsMenu(){

        //settings buttons
        Button adjustHeightButton = (Button)this.findViewById(R.id.heightButton);
        Button renamePetButton = (Button)this.findViewById(R.id.renamePetButton);
        Button adjustFitnessButton = (Button)this.findViewById(R.id.fitnessGoalsButton);
        Button settingsBackButton = (Button)this.findViewById(R.id.SettingsBackButton);


        //When user clicks back in the settings menu
        settingsBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                mainMenu();
            }
        });

    }

    public void runMenu(){

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new com.example.christinalo.myapplication.StepDetector();
        simpleStepDetector.registerListener(this);

        TvSteps = findViewById(R.id.tv_steps);
        Button BtnStart = findViewById(R.id.btn_start);
        Button BtnStop = findViewById(R.id.btn_stop);



        BtnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                numSteps = 0;
                sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

            }
        });


        BtnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                sensorManager.unregisterListener(MainActivity.this);

            }
        });


    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);
    }

}
