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
        TextView taskDetailBody = findViewById(R.id.taskDetailBody);
        TextView taskDetailState = findViewById(R.id.taskDetailState);
        taskDetailTitle.setText(taskDetail);


        String taskTitle = getIntent().getStringExtra("title");
        String taskBody = getIntent().getStringExtra("body");
        String taskState = getIntent().getStringExtra("state");
        taskDetailTitle.setText(taskTitle);
        taskDetailBody.setText(taskBody);
        taskDetailState.setText(taskState);




    }

//    @Override
//    public void onTaskClick(int position) {
//        Intent i = new Intent(this,)
//
//    }
}
