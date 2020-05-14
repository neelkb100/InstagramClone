package com.example.instagramc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseInstallation.getCurrentInstallation().saveInBackground();

    }


    public void saveDataToServer(View V){

        ParseObject kickBoxer = new ParseObject("KickBoxer");
        kickBoxer.put("name","Neel");
        kickBoxer.put("kick_speed",150);
        kickBoxer.put("kick_power",100);
        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if(e == null){

                    Toast.makeText(MainActivity.this,"Kick Boxer Successfully Saved",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
