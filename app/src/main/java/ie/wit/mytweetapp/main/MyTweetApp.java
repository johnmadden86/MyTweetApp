package ie.wit.mytweetapp.main;

import android.app.ActionBar;
import android.app.Application;
import android.util.Log;

import ie.wit.mytweetapp.models.TweetCollection;

public  class   MyTweetApp
        extends Application {

    protected static MyTweetApp app;
    public TweetCollection tweetCollection;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("MyTweet", "MyTweet App Started");
        app = this;
        tweetCollection = new TweetCollection();
    }

    public static MyTweetApp getApp() {
        return app;
    }

}
