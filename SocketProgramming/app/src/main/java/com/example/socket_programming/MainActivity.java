package com.example.socket_programming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    public String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText inputText = (EditText)findViewById(R.id.txt1);
        final Button clickable = (Button)findViewById(R.id.b1);
        final TextView txtView = (TextView)findViewById(R.id.set1);
        clickable.setOnClickListener(view -> {
            send encode1 = new send();
            //message = inputText.getText().toString();
            message = txtView.getText().toString();
            encode1.execute();
        });
    }
    class send extends AsyncTask<Void, Void, Void>{
        Socket s;
        PrintWriter pw;
        @Override
        protected Void doInBackground(Void...params){
            try {
                //s = new Socket("LAPTOP-M2DI982O",8000);
                System.out.println("Inside");
                s = new Socket("192.168.1.130",8000);
                pw = new PrintWriter(s.getOutputStream());
                pw.write(message);
                pw.flush();
                pw.close();
                s.close();
            }
            catch (UnknownHostException e) {
                System.out.println("fail");
                e.printStackTrace();
            }
            catch(IOException e) {
                System.out.println("Fail");
                e.printStackTrace();
            }
            return null;
        }

    }
}