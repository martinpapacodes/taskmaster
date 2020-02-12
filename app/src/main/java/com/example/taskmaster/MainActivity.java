package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static String TAG = "mnf.main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addATaskButton = findViewById(R.id.addATask);
        addATaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddATaskPage = new Intent(MainActivity.this, addTask.class);
                MainActivity.this.startActivity(goToAddATaskPage);
            }
        });


        Button settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettingsPage = new Intent(MainActivity.this, settings.class);
                MainActivity.this.startActivity(goToSettingsPage);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Activity Resumed");

        TextView usernameTitle = findViewById(R.id.usernameTitle);
        SharedPreferences  sharedPref =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sharedPref.getString("username", usernameTitle.getText().toString());
        Log.d(TAG, username);
        usernameTitle.setText(username + "'s tasks");
    }




}
