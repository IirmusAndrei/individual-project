package com.example.project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";

    private SensorManager sensorManager;
    Sensor gyroscope, magnetometer, accelerometer;

    TextView xAcc, yAcc, zAcc, xGyr, yGyr, zGyr, xMag, yMag, zMag;
    TextView magErr;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xGyr= findViewById(R.id.xGyr);
        yGyr = findViewById(R.id.yGyr);
        zGyr = findViewById(R.id.zGyr);

        xMag = findViewById(R.id.xMag);
        yMag = findViewById(R.id.yMag);
        zMag = findViewById(R.id.zMag);
        magErr = findViewById(R.id.magErr);

        xAcc = findViewById(R.id.xAcc);
        yAcc = findViewById(R.id.yAcc);
        zAcc = findViewById(R.id.zAcc);

        Log.d(TAG, "onCreate: Initialize Sensors");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyroscope != null) {
            sensorManager.registerListener(MainActivity.this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Register listener for gyroscope");
        } else {
            xGyr.setText("Gyroscope is not supported by this phone.");
        }

        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (accelerometer != null) {
            sensorManager.registerListener(MainActivity.this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Register listener for magnetometer");
        } else {
            magErr.setText("Magnetometer is not supported by this phone.");
            magErr.setTextColor(Color.RED);
        }

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Register listener for accelerometer");
        } else {
            xAcc.setText("Accelerometer is not supported by this phone.");
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            Log.d(TAG, "onSensorChanged: xGyr: " + event.values[0] + " yGyr: " + event.values[1] + " zGyr: " + event.values[2]);

            xGyr.setText("Gyroscope x: " + event.values[0]);
            yGyr.setText("Gyroscope y: " + event.values[1]);
            zGyr.setText("Gyroscope z: " + event.values[2]);
        } else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            Log.d(TAG, "onSensorChanged: xMag: " + event.values[0] + " yMag: " + event.values[1] + " zMag: " + event.values[2]);

            xMag.setText("Magnetometer x: " + event.values[0]);
            yMag.setText("Magnetometer y: " + event.values[1]);
            zMag.setText("Magnetometer z: " + event.values[2]);
        } else if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d(TAG, "onSensorChanged: xAcc: " + event.values[0] + " yAcc: " + event.values[1] + " zAcc: " + event.values[2]);

            xAcc.setText("Accelerometer x: " + event.values[0]);
            yAcc.setText("Accelerometer y: " + event.values[1]);
            zAcc.setText("Accelerometer z: " + event.values[2]);
        }
}

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}