package com.example.socket_programming;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    public String message;
    public SensorManager sManager;
    public boolean startClicked = false;
    public  boolean stopClicked = false;
    public Socket s;
    public PrintWriter pw;
    //Buttons and Text

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button startButton = (Button)findViewById(R.id.b1);
        final Button stopButton = (Button)findViewById(R.id.b2);
        final TextView txtView = (TextView)findViewById(R.id.set1);
        //Sensors
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        //Checking if Sensors Exists
        if(sManager != null){
            Sensor accSensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            if(accSensor != null){
                sManager.registerListener(this,accSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
        else{
            Toast.makeText(this, "Sensors not avlable", Toast.LENGTH_SHORT).show();
        }


        //when start button hits
        startButton.setOnClickListener(view -> {
            startClicked = true;
            send encode1 = new send();
            //message = inputText.getText().toString();
            encode1.execute();
        });
        stopButton.setOnClickListener(view -> {
            startClicked = false;
        });

        //when stop button hits
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            ((TextView)findViewById(R.id.set1)).setText("X: " + sensorEvent.values[0] + " Y: " + sensorEvent.values[1]+ " Z: " + sensorEvent.values[2]);
            message = "X: " + sensorEvent.values[0] + " Y: " + sensorEvent.values[1]+ " Z: " + sensorEvent.values[2];
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    class send extends AsyncTask<Void, Void, Void>{
        Socket s;
        PrintWriter pw;
        @Override
        protected Void doInBackground(Void...params){
            while(startClicked) {
                try {
                    //s = new Socket("LAPTOP-M2DI982O",8000);
                    s = new Socket("192.168.1.130", 8000);
                    pw = new PrintWriter(s.getOutputStream());
                    pw.write(message);
                    pw.flush();
                    pw.close();
                    s.close();
                } catch (UnknownHostException e) {
                    System.out.println("fail");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("Fail");
                    e.printStackTrace();
                }
            }

            return null;
        }

    }
}