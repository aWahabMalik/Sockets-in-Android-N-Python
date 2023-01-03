package com.example.sdas_wear_app;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sdas_wear_app.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private List<Sensor> sensors;

    private TextView ac_x, ac_y, ac_z;
    private TextView gy_x, gy_y, gy_z;
    private TextView ma_x, ma_y, ma_z;
    private TextView la_x, la_y, la_z;
    private TextView gr_x, gr_y, gr_z;
    
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //mTextView = binding.text;
        // Get a reference to the sensor manager
                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //initialize the sensors with empty list
        sensors = new java.util.ArrayList<Sensor>();
        // Get sensors accelerometer, gyroscope, magnetometer, linear accelerometer, gravity in the list
        sensors.add(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        sensors.add(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
        sensors.add(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD));
        sensors.add(sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION));
        sensors.add(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY));
        //show all sensors
        for (Sensor sensor : sensors) {
            System.out.println(sensor.getName());
        }
        // now register all sensors
        for (Sensor sensor : sensors) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        //initialize the textviews for accelerometer
        ac_x = findViewById(R.id.Ac_x);
        ac_y = findViewById(R.id.Ac_y);
        ac_z = findViewById(R.id.Ac_z);
        //initialize the textviews for gyroscope
        gy_x = findViewById(R.id.Gy_x);
        gy_y = findViewById(R.id.Gy_y);
        gy_z = findViewById(R.id.Gy_z);
        //initialize the textviews for magnetometer
        ma_x = findViewById(R.id.Mg_x);
        ma_y = findViewById(R.id.Mg_y);
        ma_z = findViewById(R.id.Mg_z);
        //initialize the textviews for linear accelerometer
        la_x = findViewById(R.id.La_x);
        la_y = findViewById(R.id.La_y);
        la_z = findViewById(R.id.La_z);
        //initialize the textviews for gravity
        gr_x = findViewById(R.id.Gr_x);
        gr_y = findViewById(R.id.Gr_y);
        gr_z = findViewById(R.id.Gr_z);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            ac_x.setText(String.valueOf(sensorEvent.values[0]));
            ac_y.setText(String.valueOf(sensorEvent.values[1]));
            ac_z.setText(String.valueOf(sensorEvent.values[2]));
        }
        else if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gy_x.setText(String.valueOf(sensorEvent.values[0]));
            gy_y.setText(String.valueOf(sensorEvent.values[1]));
            gy_z.setText(String.valueOf(sensorEvent.values[2]));
        }
        else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            ma_x.setText(String.valueOf(sensorEvent.values[0]));
            ma_y.setText(String.valueOf(sensorEvent.values[1]));
            ma_z.setText(String.valueOf(sensorEvent.values[2]));
        }
        else if(sensorEvent.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            la_x.setText(String.valueOf(sensorEvent.values[0]));
            la_y.setText(String.valueOf(sensorEvent.values[1]));
            la_z.setText(String.valueOf(sensorEvent.values[2]));
        }
        else if(sensorEvent.sensor.getType() == Sensor.TYPE_GRAVITY) {
            gr_x.setText(String.valueOf(sensorEvent.values[0]));
            gr_y.setText(String.valueOf(sensorEvent.values[1]));
            gr_z.setText(String.valueOf(sensorEvent.values[2]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}