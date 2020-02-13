package com.example.taskmaster;

import java.util.ArrayList;

public class Task {
    String title;
    String body;
    String state;

    public Task(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


  private ArrayList<Task> initTask() {
        ArrayList<Task> listOfTask = new ArrayList<>();


      listOfTask.add(new Task("Feed Dog", "Feed Biggie around 4 pm.", "new"));
      listOfTask.add(new Task("Walk Dog", "Walk Biggie at 7 am before heading to work.", "complete"));
      listOfTask.add(new Task("Pet Dog", "Pet Biggie all the time.", "in progress"));

      return listOfTask;
  }
}
