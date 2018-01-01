package ie.wit.mytweetapp.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ie.wit.mytweetapp.R;
import ie.wit.mytweetapp.main.MyTweetApp;
import ie.wit.mytweetapp.models.User;

import static ie.wit.mytweetapp.main.MyTweetApp.getApp;

public class Register extends AppCompatActivity {

    private MyTweetApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        app = getApp();
        Log.v("MyTweet", "Sign up page loaded");
    }

    public void registerUser(View view) {
        EditText firstName = (EditText) findViewById(R.id.firstName);
        EditText lastName = (EditText) findViewById(R.id.lastName);
        EditText email = (EditText) findViewById(R.id.Email);
        EditText password = (EditText) findViewById(R.id.Password);

        User user = new User(
                firstName.getText().toString(),
                lastName.getText().toString(),
                email.getText().toString(),
                password.getText().toString()
        );

        app.userCollection.newUser(user);
        Log.v("MyTweet", "Registering " + firstName.getText() + " " + lastName.getText());
        app.loggedInUser = user;
        Toast.makeText(this, "Welcome " + user.firstName, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, TimelineActivity.class));
    }

    public void loginButtonPressed(View view) {
        Log.v("MyTweet", "Login pressed");
        startActivity(new Intent(this, Login.class));
    }
}

