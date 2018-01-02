package ie.wit.mytweetapp.main;

import android.app.ActionBar;
import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

import ie.wit.mytweetapp.models.Tweet;
import ie.wit.mytweetapp.models.TweetCollection;
import ie.wit.mytweetapp.models.User;
import ie.wit.mytweetapp.models.UserCollection;

public  class   MyTweetApp
        extends Application {

    protected static MyTweetApp app;
    public User loggedInUser;
    public UserCollection userCollection;
    public TweetCollection tweetCollection;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("MyTweet", "MyTweet App Started");
        app = this;
        userCollection = new UserCollection();
        tweetCollection = new TweetCollection();
    }

    public static MyTweetApp getApp() {
        return app;
    }

}
