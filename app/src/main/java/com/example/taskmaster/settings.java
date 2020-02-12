package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.prefs.PreferenceChangeEvent;

public class settings extends AppCompatActivity {

    static final String TAG = "mnf.Settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userNameEditText = findViewById(R.id.userNameEditText);
                Log.d(TAG, "User has done something");

                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("username", userNameEditText.getText().toString());
                Log.d(TAG, userNameEditText.getText().toString());
                editor.apply();


                Intent goBackToHomePage = new Intent(settings.this, MainActivity.class);
                settings.this.startActivity(goBackToHomePage);


            }
        });
    }
}
