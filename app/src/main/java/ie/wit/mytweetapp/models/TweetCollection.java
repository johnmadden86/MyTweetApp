package ie.wit.mytweetapp.models;

import android.util.Log;

import java.util.ArrayList;

public class TweetCollection {
    public ArrayList<Tweet> tweets;
    private TweetSerializer serializer;

    public TweetCollection(TweetSerializer serializer) {
        this.serializer = serializer;
        try {
            tweets = serializer.loadTweets();
        } catch (Exception e) {
            Log.v("MyTweet","Error loading residences: " + e.getMessage());
            tweets = new ArrayList<>();
        };
    }

    public void newTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public void deleteTweet(Tweet tweet) {
        tweets.remove(tweet);
        saveTweets();
    }

    public Tweet getTweet(Long id) {
        for (Tweet tweet : tweets) {
            if (id.equals(tweet.id)) {
                return tweet;
            }
        }
        return null;
    }

    public boolean saveTweets() {
        try {
            serializer.saveTweets(tweets);
            Log.v("MyTweet", "Tweets saved to file");
            return true;
        } catch (Exception e) {
            Log.v("MyTweet","Error saving tweets: " + e.getMessage());
            return false;
        }
    }
}
