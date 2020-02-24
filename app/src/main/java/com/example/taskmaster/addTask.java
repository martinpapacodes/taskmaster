package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addTask extends AppCompatActivity {

    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        db =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks")
                .allowMainThreadQueries().build();

        final Context context = getApplicationContext();

        Button addATaskButton = findViewById(R.id.addATask);
        addATaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText taskTitle = findViewById(R.id.taskTitle);
                EditText taskDescription = findViewById(R.id.taskDescription);

                TextView submitted = findViewById(R.id.submitted);

                submitted.setText("Submitted!");
                submitted.setVisibility(View.VISIBLE);


                Task addTask = new Task(taskTitle.getText().toString(), taskDescription.getText().toString(), "New");
                Log.d("MARTINPAPA", addTask.getTitle());
                db.taskDao().addTask(addTask);

                taskTitle.setText("");
                taskDescription.setText("");

                Intent goBackToMain = new Intent(context, MainActivity.class);
                context.startActivity(goBackToMain);

            }
        });


    }
}
