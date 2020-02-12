package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class taskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);


        String taskDetail = getIntent().getStringExtra("taskDetail");
        TextView taskDetailTitle = findViewById(R.id.taskDetailTitle);
        taskDetailTitle.setText(taskDetail);



    }
}
