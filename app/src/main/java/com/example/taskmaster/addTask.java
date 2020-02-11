package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button addATaskButton = findViewById(R.id.addATask);
        addATaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText taskTitle = findViewById(R.id.taskTitle);
                EditText taskDescription = findViewById(R.id.taskDescription);

                TextView submitted = findViewById(R.id.submitted);

                submitted.setText("Submitted!");
                submitted.setVisibility(View.VISIBLE);
                taskTitle.setText("");
                taskDescription.setText("");

            }
        });


    }
}
