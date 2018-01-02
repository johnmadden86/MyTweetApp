package ie.wit.mytweetapp.activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ie.wit.mytweetapp.R;
import ie.wit.mytweetapp.main.MyTweetApp;
import ie.wit.mytweetapp.models.Tweet;
import ie.wit.mytweetapp.models.TweetCollection;
import ie.wit.mytweetapp.models.User;

import static ie.wit.mytweetapp.android.helpers.ContactHelper.getContact;
import static ie.wit.mytweetapp.android.helpers.ContactHelper.getEmail;
import static ie.wit.mytweetapp.android.helpers.ContactHelper.sendEmail;
import static ie.wit.mytweetapp.android.helpers.IntentHelper.navigateUp;
import static ie.wit.mytweetapp.main.MyTweetApp.getApp;
import static java.lang.String.valueOf;

public  class SettingsFragment
        extends     Fragment implements OnClickListener {

    private MyTweetApp app;
    private User user;
    private EditText firstName, lastName, email, password;
    private Button saveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        app = getApp();
        user = app.loggedInUser;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater,  parent, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_settings, parent, false);
        firstName = (EditText) v.findViewById(R.id.firstName);
        firstName.setText(user.firstName);
        lastName = (EditText) v.findViewById(R.id.lastName);
        lastName.setText(user.lastName);
        email = (EditText) v.findViewById(R.id.Email);
        email.setText(user.email);
        password = (EditText) v.findViewById(R.id.Password);
        password.setText(user.password);
        saveButton = (Button) v.findViewById(R.id.save_settings_button);
        saveButton.setOnClickListener(this);
        return v;
    }

    public void saveSettings() {
        user.firstName = firstName.getText().toString();
        user.lastName = lastName.getText().toString();
        user.email = email.getText().toString();
        user.password = password.getText().toString();

        Toast.makeText(getActivity(), "Account updated " + user.firstName, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(), TimelineActivity.class));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_settings_button:
                saveSettings();
                break;
    }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.settings_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                navigateUp(getActivity());
                break;
            case R.id.menuTimeline:
                startActivity(new Intent(getActivity(), TimelineActivity.class));
                break;
            case R.id.newTweet:
                startActivity(new Intent(getActivity(), TweetActivity.class));
                break;
            case R.id.menuLogout:
                startActivity(new Intent(getActivity(), Login.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        app.save();
    }
}
