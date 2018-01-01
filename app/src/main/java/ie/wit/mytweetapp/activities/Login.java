package ie.wit.mytweetapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ie.wit.mytweetapp.R;

public class Login extends AppCompatActivity {

    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);
    }

    public void loginUser(View view) {

        String emailInput = email.getText().toString();
        String passwordInput = password.getText().toString();
            Log.v("MyTweet", "Login pressed for " + emailInput);
            Toast.makeText(this, "Login pressed for " + emailInput, Toast.LENGTH_SHORT).show();

    }

    public void signUpButtonPressed(View view) {
        Log.v("MyTweet", "Sign up pressed");
        startActivity(new Intent(this, Register.class));
    }
}

