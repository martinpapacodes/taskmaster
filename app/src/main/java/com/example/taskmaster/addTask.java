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

import com.amazonaws.amplify.generated.graphql.CreateTaskMutation;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import javax.annotation.Nonnull;

import type.CreateTaskInput;

public class addTask extends AppCompatActivity {

    AppDatabase db;
    private AWSAppSyncClient mAWSAppSyncClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();

        db =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks")
                .allowMainThreadQueries().build();

        final Context context = getApplicationContext();

        Button addATaskButton = findViewById(R.id.addATask);
        addATaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText taskTitle = findViewById(R.id.taskTitle);
                EditText taskDescription = findViewById(R.id.taskDescription);
                CreateTaskInput createTaskInput = CreateTaskInput.builder().
                        title(taskTitle.getText().toString()).
                        body(taskDescription.getText().toString()).
                        state("New").
                        build();

                mAWSAppSyncClient.mutate(CreateTaskMutation.builder().input(createTaskInput).build())
                        .enqueue(mutationCallback);



                TextView submitted = findViewById(R.id.submitted);

                submitted.setText("Submitted!");
                submitted.setVisibility(View.VISIBLE);


                Task addTask = new Task(taskTitle.getText().toString(), taskDescription.getText().toString(), "New");
                Log.d("MARTINPAPA", addTask.getTitle());
                db.taskDao().addTask(addTask);

                taskTitle.setText("");
                taskDescription.setText("");


            }
        });






    }

    private GraphQLCall.Callback<CreateTaskMutation.Data> mutationCallback = new GraphQLCall.Callback<CreateTaskMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<CreateTaskMutation.Data> response) {
            Log.i("Results", "Added Todo");

            Intent goBackToMain = new Intent(addTask.this, MainActivity.class);
            addTask.this.startActivity(goBackToMain);
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }
    };
}
