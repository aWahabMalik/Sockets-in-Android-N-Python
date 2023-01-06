package com.example.sdas;

import androidx.appcompat.app.AppCompatActivity;

// For Senors
import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

//For Socket Programming
import android.os.AsyncTask;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import android.widget.Toast;

//import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    List<Sensor> sensors;

    TextView ac_x, ac_y, ac_z;
    TextView gy_x, gy_y, gy_z;
    TextView ma_x, ma_y, ma_z;
    TextView la_x, la_y, la_z;
    TextView gr_x, gr_y, gr_z;

    float ac_x1, ac_y1, ac_z1;
    float gy_x1, gy_y1, gy_z1;
    float ma_x1, ma_y1, ma_z1;
    float la_x1, la_y1, la_z1;
    float gr_x1, gr_y1, gr_z1;


    String message;
    boolean startClicked = false;
    //Socket s;
    //PrintWriter pw;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button startButton = (Button)findViewById(R.id.startButton);
        final Button stopButton = (Button)findViewById(R.id.stopButton);


        // Get a reference to the sensor manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //initialize the sensors with empty list
        sensors = new java.util.ArrayList<Sensor>();
        // Get sensors accelerometer, gyroscope, magnetometer, linear accelerometer, gravity in the list
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            sensors.add(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null){
            sensors.add(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
            sensors.add(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD));
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) != null){
            sensors.add(sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION));
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null){
            sensors.add(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY));
        }
        //show all sensors
        for (Sensor sensor : sensors) {
            System.out.println(sensor.getName());
        }
        // now register all sensors
        for (Sensor sensor : sensors) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        //initialize the textviews for accelerometer
        ac_x = findViewById(R.id.ac_x);
        ac_y = findViewById(R.id.ac_y);
        ac_z = findViewById(R.id.ac_z);
        //initialize the textviews for gyroscope
        gy_x = findViewById(R.id.gy_x);
        gy_y = findViewById(R.id.gy_y);
        gy_z = findViewById(R.id.gy_z);
        //initialize the textviews for magnetometer
        ma_x = findViewById(R.id.mg_x);
        ma_y = findViewById(R.id.mg_y);
        ma_z = findViewById(R.id.mg_z);
        //initialize the textviews for linear accelerometer
        la_x = findViewById(R.id.la_x);
        la_y = findViewById(R.id.la_y);
        la_z = findViewById(R.id.la_z);
        //initialize the textviews for gravity
        gr_x = findViewById(R.id.gr_x);
        gr_y = findViewById(R.id.gr_y);
        gr_z = findViewById(R.id.gr_z);

        //button Clicked
        startButton.setOnClickListener(view -> {
            startClicked = true;
            message = null;
            send encode1 = new send();

            encode1.execute();
        });
        stopButton.setOnClickListener(view -> {
            //message = null;
            startClicked = false;
        });

        //create on button click listener for start button
        findViewById(R.id.Connect_Watch).setOnClickListener(v -> {
            //call the function to connect to the watch
            //connectWatch();
        });


    }
/*
    //function to connect to the wear os watch
    private void connectWatch() {
        //show all the available wear os devices in popup
        Wearable.getCapabilityClient(this).getCapability("sensors", CapabilityClient.FILTER_REACHABLE).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                CapabilityInfo capabilityInfo = task.getResult();
                if (capabilityInfo != null) {
                    //get the node id of the watch
                    String nodeId = capabilityInfo.getNodes().iterator().next().getId();
                    //send the message to the watch
                    Wearable.getMessageClient(this).sendMessage(nodeId, "sensors", "start".getBytes());
                }
            }
        });
        //create a new thread to connect to the watch

    }*/

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            ac_x.setText(String.valueOf(sensorEvent.values[0]));

            ac_y.setText(String.valueOf(sensorEvent.values[1]));

            ac_z.setText(String.valueOf(sensorEvent.values[2]));
            ac_x1 = sensorEvent.values[0];
            ac_y1 = sensorEvent.values[1];
            ac_z1 = sensorEvent.values[2];
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gy_x.setText(String.valueOf(sensorEvent.values[0]));
            gy_y.setText(String.valueOf(sensorEvent.values[1]));
            gy_z.setText(String.valueOf(sensorEvent.values[2]));
            gy_x1 = sensorEvent.values[0];
            gy_y1 = sensorEvent.values[1];
            gy_z1 = sensorEvent.values[2];
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            ma_x.setText(String.valueOf(sensorEvent.values[0]));
            ma_y.setText(String.valueOf(sensorEvent.values[1]));
            ma_z.setText(String.valueOf(sensorEvent.values[2]));
            ma_x1 = sensorEvent.values[0];
            ma_y1 = sensorEvent.values[1];
            ma_z1 = sensorEvent.values[2];
        }
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            la_x.setText(String.valueOf(sensorEvent.values[0]));
            la_y.setText(String.valueOf(sensorEvent.values[1]));
            la_z.setText(String.valueOf(sensorEvent.values[2]));
            la_x1 = sensorEvent.values[0];
            la_y1 = sensorEvent.values[1];
            la_z1 = sensorEvent.values[2];
        }
        if(sensorEvent.sensor.getType() == Sensor.TYPE_GRAVITY) {
            gr_x.setText(String.valueOf(sensorEvent.values[0]));
            gr_y.setText(String.valueOf(sensorEvent.values[1]));
            gr_z.setText(String.valueOf(sensorEvent.values[2]));
            gr_x1 = sensorEvent.values[0];
            gr_y1 = sensorEvent.values[1];
            gr_z1 = sensorEvent.values[2];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    //Send
    class send extends AsyncTask<Void, Void, Void>{
        Socket s;
        PrintWriter pw;
        @Override
        protected Void doInBackground(Void...params){
            //while(startClicked) {
            try {
                //s  = new Socket("LAPTOP-M2DI982O",8000);
                s = new Socket("192.168.43.176", 8000);
                pw = new PrintWriter(s.getOutputStream());
                while (startClicked){
                    sendData(s,pw);
                    System.out.println("Inside");
                }
                System.out.println("outside");
                //message = null;
                pw.close();
                s.close();
            } catch (UnknownHostException e) {
                System.out.println("fail");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Fail");
                e.printStackTrace();
            }
            //}

            return null;
        }
        protected Void sendData(Socket s, PrintWriter pw){

            /*message = ((TextView)findViewById(R.id.ac_x)).getText().toString() + " " + ((TextView)findViewById(R.id.ac_y)).getText().toString() + " " + ((TextView)findViewById(R.id.ac_z)).getText().toString() + " " +
                      ((TextView)findViewById(R.id.gy_x)).getText().toString() + " " + ((TextView)findViewById(R.id.gy_y)).getText().toString() + " " + ((TextView)findViewById(R.id.gy_z)).getText().toString() + " " +
                      ((TextView)findViewById(R.id.mg_x)).getText().toString() + " " + ((TextView)findViewById(R.id.mg_y)).getText().toString() + " " + ((TextView)findViewById(R.id.mg_z)).getText().toString() + " " +
                      ((TextView)findViewById(R.id.la_x)).getText().toString() + " " + ((TextView)findViewById(R.id.la_y)).getText().toString() + " " + ((TextView)findViewById(R.id.la_z)).getText().toString() + " " +
                      ((TextView)findViewById(R.id.gr_x)).getText().toString() + " " + ((TextView)findViewById(R.id.gr_y)).getText().toString() + " " + ((TextView)findViewById(R.id.gr_z)).getText().toString() + " ";*/
            //message = null;
            message = ac_x1 + " " + ac_y1 + " " + ac_z1 + " " +
                      gy_x1 + " " + gy_y1 + " " + gy_z1 + " " +
                      ma_x1 + " " + ma_y1 + " " + ma_z1 + " " +
                      la_x1 + " " + la_y1 + " " + la_z1 + " " +
                      gr_x1 + " " + gr_y1 + " " + gr_z1 + " " ;

            pw.write(message);
            pw.flush();
            return null;
        }
    }
}