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

public class Login extends AppCompatActivity {

    private MyTweetApp app;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        app = getApp();
        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);
    }

    public void loginUser(View view) {
        String emailInput = email.getText().toString();
        String passwordInput = password.getText().toString();
        if (app.userCollection.validUser(emailInput, passwordInput)) {
            User user = app.userCollection.getUserByEmail(emailInput);
            app.loggedInUser = user;
            Toast.makeText(this, "Welcome " + user.firstName, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, TimelineActivity.class));
        } else {
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void signUpButtonPressed(View view) {
        Log.v("MyTweet", "Sign up pressed");
        startActivity(new Intent(this, Register.class));
    }
}

