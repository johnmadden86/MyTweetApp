package ie.wit.mytweetapp.main;

import android.app.ActionBar;
import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

import ie.wit.mytweetapp.models.Tweet;
import ie.wit.mytweetapp.models.TweetCollection;
import ie.wit.mytweetapp.models.TweetSerializer;
import ie.wit.mytweetapp.models.User;
import ie.wit.mytweetapp.models.UserCollection;
import ie.wit.mytweetapp.models.UserSerializer;

public  class   MyTweetApp
        extends Application {

    protected static MyTweetApp app;
    public User loggedInUser;
    public UserCollection userCollection;
    public TweetCollection tweetCollection;
    private static final String USER_FILE = "user_collection.json";
    private static final String TWEET_FILE = "tweet_collection.json";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("MyTweet", "MyTweet App Started");
        app = this;
        UserSerializer userSerializer = new UserSerializer(this, USER_FILE );
        userCollection = new UserCollection(userSerializer);
        TweetSerializer tweetSerializer = new TweetSerializer(this, TWEET_FILE);
        tweetCollection = new TweetCollection(tweetSerializer);
    }

    public static MyTweetApp getApp() {
        return app;
    }

    public void save() {
        tweetCollection.saveTweets();
        userCollection.saveUsers();
    }

}
