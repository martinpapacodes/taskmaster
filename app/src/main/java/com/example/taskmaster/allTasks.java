package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import java.util.List;

public class allTasks extends AppCompatActivity {

    AppDatabase db;
    List<Task> listOfTasks;
    AllTaskRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);


        RecyclerView recyclerView = findViewById(R.id.allTaskRecyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks")
                .allowMainThreadQueries().build();

        listOfTasks =  db.taskDao().getAllTasks();

        adapter = (new AllTaskRecyclerViewAdapter(listOfTasks, null));
        recyclerView.setAdapter(adapter);
    }
}
