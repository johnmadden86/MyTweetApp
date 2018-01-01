package ie.wit.mytweetapp.models;

import java.util.ArrayList;

public class TweetCollection {
    public ArrayList<Tweet> tweets;

    public TweetCollection() {
        this.tweets = new ArrayList<>();
    }

    public void newTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public Tweet getTweet(Long id) {
        for (Tweet tweet : tweets) {
            if (id.equals(tweet.id)) {
                return tweet;
            }
        }
        return null;
    }
}
