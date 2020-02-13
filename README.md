# taskmaster

## Lab 26: Beginning TaskMaster

### Feature Tasks

#### Problem Domain

Today, you’ll start building an Android app that will be a main focus of the second half of the course: TaskMaster. While you’ll start small today, over time this will grow to be a fully-featured application.

* Homepage

    * The main page should be built out to match the wireframe. In particular, it should have a heading at the top of the page, an image to mock the “my tasks” view, and buttons at the bottom of the page to allow going to the “add tasks” and “all tasks” page.

* Add a Task

    * On the “Add a Task” page, allow users to type in details about a new task, specifically a title and a body. When users click the “submit” button, show a “submitted!” label on the page.
    
* All Tasks

    * The all tasks page should just be an image with a back button; it needs no functionality. 
      
##### Daily Change Log

* Added homepage, task page, and all tasks page
* When user clicked on clicks on add task button, the app prompts, "Submitted."

###### Screenshot

###### Homepage
![homepage](screenshots/homePage.png){:height="500" width="300"}

###### Add Task Page
![add task](screenshots/addTask.png){:height="500" width="300"}

## Lab 27: Adding Data to TaskMaster

### Feature Tasks

* Task Detail Page

    * Create a Task Detail page. It should have a title at the top of the page, and a Lorem Ipsum description.
    
* Settings Page

    * Create a Settings page. It should allow users to enter their username and hit save.
    
*  Homepage

    * The main page should be modified to contain three different buttons with hardcoded task titles. When a user taps one of the titles, it should go to the Task Detail page, and the title at the top of the page should match the task title that was tapped on the previous page.
      
      The homepage should also contain a button to visit the Settings page, and once the user has entered their username, it should display “{username}’s tasks” above the three task buttons.

#### Problem Domain

Today, you’ll add the ability to send data among different activities in your application using SharedPreferences and Intents.


      
##### Daily Change Log

* Added settings page. User now is able to type in their username on the setting page and display the username back to the homepage on top of the task buttons.
* Replaced the image with hardcoded task buttons. When one of the buttons is clicked, the app will take you to the task detail's page.

##### New Homepage
![new home page](screenshots/newHomePage.png){:height="500" width="300"}


##### Task Details Page

![task details page](screenshots/taskDetailPage.png){:height="500" width="300"}
