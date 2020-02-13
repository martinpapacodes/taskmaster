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

import java.util.ArrayList;
import java.util.List;

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

        final Button walkDog = findViewById(R.id.walkDogButton);
        walkDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToTaskDetailsPage = new Intent(MainActivity.this, taskDetail.class);
                goToTaskDetailsPage.putExtra("taskDetail", "Walk The Effin Dog");
                Log.d(TAG, walkDog.getText().toString());

                MainActivity.this.startActivity(goToTaskDetailsPage);
            }
        });


        final Button feedDog = findViewById(R.id.feedDogButton);
        feedDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToTaskDetailsPage = new Intent(MainActivity.this, taskDetail.class);
                goToTaskDetailsPage.putExtra("taskDetail", feedDog.getText().toString());
                Log.d(TAG, feedDog.getText().toString());

                MainActivity.this.startActivity(goToTaskDetailsPage);
            }
        });

        final Button petDog = findViewById(R.id.petDogButton);
        petDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToTaskDetailsPage = new Intent(MainActivity.this, taskDetail.class);
                goToTaskDetailsPage.putExtra("taskDetail", petDog.getText().toString());
                Log.d(TAG, petDog.getText().toString());

                MainActivity.this.startActivity(goToTaskDetailsPage);
            }
        });
        // ViewAdapter has the job of telling the RecyclerView what to display at each row
        Button allTaskButton = findViewById(R.id.allTasksButton);
        allTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAllTasksPage = new Intent(MainActivity.this, allTasks.class);
                startActivity(goToAllTasksPage);
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
