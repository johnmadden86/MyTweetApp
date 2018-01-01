package ie.wit.mytweetapp.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ie.wit.mytweetapp.R;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Log.v("MyTweet", "Sign up page loaded");
    }

    public void registerUser(View view) {
        EditText firstName = (EditText) findViewById(R.id.firstName);
        EditText lastName = (EditText) findViewById(R.id.lastName);
        EditText email = (EditText) findViewById(R.id.Email);
        EditText password = (EditText) findViewById(R.id.Password);

        Log.v("MyTweet", "Registering " + firstName.getText() + " " + lastName.getText());
        Toast.makeText(this, "Registering " + firstName.getText() + " " + lastName.getText(), Toast.LENGTH_SHORT).show();
    }

    public void loginButtonPressed(View view) {
        Log.v("MyTweet", "Login pressed");
        startActivity(new Intent(this, Login.class));
    }
}

