package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.amazonaws.amplify.generated.graphql.ListTasksQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nonnull;

public class MainActivity extends AppCompatActivity implements MyTaskRecyclerViewAdapter.OnTaskListener {

    AppDatabase db;

    private AWSAppSyncClient mAWSAppSyncClient;
    List<Task> listOfTasks;
    MyTaskRecyclerViewAdapter adapter;
    static String TAG = "mnf.main";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();

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

        // ViewAdapter has the job of telling the RecyclerView what to display at each row
        Button allTaskButton = findViewById(R.id.allTasksButton);
        allTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAllTasksPage = new Intent(MainActivity.this, allTasks.class);
                startActivity(goToAllTasksPage);
            }
        });

//        db =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks")
//                .allowMainThreadQueries().build();
//        listOfTasks = db.taskDao().getAllTasks();

        RecyclerView recyclerView = findViewById(R.id.fragment2);


            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks")
                .allowMainThreadQueries().build();


        listOfTasks = new LinkedList<>();
        adapter = (new MyTaskRecyclerViewAdapter(listOfTasks, null));
        recyclerView.setAdapter(adapter);


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

        RecyclerView recyclerView = findViewById(R.id.fragment2);

//        db =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks")
//                .allowMainThreadQueries().build();
//
//        listOfTasks = db.taskDao().getAllTasks();
//        adapter.notifyDataSetChanged();

        runQuery();




    }


    @Override
    public void onTaskClick(Task task) {
        Intent takeMeToTaskDetail = new Intent(this, taskDetail.class);

        startActivity(takeMeToTaskDetail);
    }

    public void runQuery(){
        mAWSAppSyncClient.query(ListTasksQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(taskCallback);
    }

    private GraphQLCall.Callback<ListTasksQuery.Data> taskCallback = new GraphQLCall.Callback<ListTasksQuery.Data>() {
        @Override
        public void onResponse(@Nonnull Response<ListTasksQuery.Data> response) {
            Log.i("Results", response.data().listTasks().items().toString());


            listOfTasks.clear();

            for(ListTasksQuery.Item task: response.data().listTasks().items()) {
                listOfTasks.add(new Task(task.title(), task.body(), task.state()));
            }

            Handler handlerForMainThread = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message inputMessage) {
                    adapter.notifyDataSetChanged();
                }
            };

            handlerForMainThread.obtainMessage().sendToTarget();






        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("ERROR", e.toString());
        }
    };

}
