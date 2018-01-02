package ie.wit.mytweetapp.activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
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

import static ie.wit.mytweetapp.android.helpers.ContactHelper.getContact;
import static ie.wit.mytweetapp.android.helpers.ContactHelper.getEmail;
import static ie.wit.mytweetapp.android.helpers.ContactHelper.sendEmail;
import static ie.wit.mytweetapp.android.helpers.IntentHelper.navigateUp;
import static ie.wit.mytweetapp.main.MyTweetApp.getApp;
import static java.lang.String.valueOf;

public  class       TweetFragment
        extends     Fragment
        implements  OnClickListener,
                    TextWatcher,
                    OnDateSetListener {

    private MyTweetApp app;
    private TweetCollection tweetCollection;
    private Tweet tweet;
    private boolean newTweet = false;

    private EditText textInput;
    private TextView charCount;
    private Button sendTweetButton, editDateButton, selectContactButton, emailButton;
    private static final int REQUEST_CONTACT = 1;
    private String emailAddress;
    private Intent data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        app = getApp();
        tweetCollection = app.tweetCollection;
        Long tweetId = (Long) getActivity().getIntent().getSerializableExtra("TWEET_ID");
        if (tweetId != null) {
            tweet = tweetCollection.getTweet(tweetId);
        }
        if (tweet == null) {
            tweet = new Tweet(app.loggedInUser);
            newTweet = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater,  parent, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_new_tweet, parent, false);

        addListeners(v);
        updateControls(tweet);

        return v;
    }


    private void addListeners(View v) {
        textInput = (EditText) v.findViewById(R.id.tweet_input);
        textInput.addTextChangedListener(this);

        charCount = (TextView) v.findViewById(R.id.char_count);

        sendTweetButton = (Button) v.findViewById(R.id.send_tweet_button);
        sendTweetButton.setOnClickListener(this);

        editDateButton = (Button) v.findViewById(R.id.edit_date_button);
        editDateButton.setOnClickListener(this);

        selectContactButton = (Button) v.findViewById(R.id.select_contact_button);
        selectContactButton.setOnClickListener(this);

        emailButton = (Button) v.findViewById(R.id.email_button);
        emailButton.setOnClickListener(this);
        // emailButton.setEnabled(emailAddress != null);

        if (!newTweet) {
            textInput.setEnabled(false);
            editDateButton.setEnabled(false);
        }
    }

    public void updateControls(Tweet tweet) {
        textInput.setText(tweet.text);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tweet_compose_menu, menu);
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
            case R.id.menuSettings:
                Toast.makeText(getActivity(), "Settings Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuLogout:
                Toast.makeText(getActivity(), "Logout Selected", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable editable) {
        int charLimit = 140;
        int tweetLength = textInput.length();
        int charCount = charLimit - tweetLength;
        this.charCount.setText(valueOf(charCount));
        this.charCount.setTextColor(charCount >= 0 ? Color.BLACK : Color.RED);
        sendTweetButton.setEnabled(charCount >= 0 && charCount < charLimit && newTweet);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_tweet_button:
                sendTweet();
                break;
            case R.id.edit_date_button:
                Calendar c = Calendar.getInstance();
                DatePickerDialog dpd = new DatePickerDialog
                        (
                                getActivity(),
                                this,
                                c.get(Calendar.YEAR),
                                c.get(Calendar.MONTH),
                                c.get(Calendar.DAY_OF_MONTH)
                        );
                dpd.show();
                break;
            case R.id.select_contact_button:
                //selectContact(getActivity(), REQUEST_CONTACT);
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i, REQUEST_CONTACT);
                break;
            case R.id.email_button:
                sendEmail(
                        getActivity(),
                        emailAddress,
                        getString(R.string.share_tweet_subject),
                        tweet.shareTweetString(getActivity()));
                break;

        }}

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Date date = new GregorianCalendar(year, month, dayOfMonth).getTime();
            tweet.setDate(date.getTime());
            editDateButton.setText(tweet.getDateString());
        }

    public void sendTweet() {
        tweet.setText(textInput.getText().toString());
        Log.v("MyTweet", tweet.text + " length: " + textInput.length());
        Toast.makeText(getActivity(), "Tweet Sent! ", Toast.LENGTH_SHORT).show();
        textInput.setText("");
        if (newTweet) {
            tweetCollection.newTweet(tweet);
        }
        startActivity(new Intent(getActivity(), TimelineActivity.class));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CONTACT:
                this.data = data;
                checkContactsReadPermission();
                break;
        }
    }

    private void checkContactsReadPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CONTACT);
        }
        else {
            readContact();
        }
    }

    @Override
    public void onRequestPermissionsResult
            (int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CONTACT: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContact();
                }
            }
        }
    }

    private void readContact() {
        String name = getContact(getActivity(), data);
        emailAddress = getEmail(getActivity(), data);
        selectContactButton.setText(name + " : " + emailAddress);
        tweet.contact = name;
    }
}
