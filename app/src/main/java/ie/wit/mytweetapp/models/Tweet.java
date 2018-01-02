package ie.wit.mytweetapp.models;

import android.content.Context;

import java.util.Date;
import java.util.Random;

public class Tweet {
    public Long date, id;
    public String text, contact;
    public User author;

    public Tweet(User author) {
        this.author = author;
        id = unsignedLong();
        date = new Date().getTime();
        contact = ": none";
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    static Long unsignedLong() {
        long rndVal;
        do {
            rndVal = new Random().nextLong();
        } while (rndVal <= 0);
        return rndVal;
    }

    public String getDateString() {
        String dateFormat = "EEE d MMM yyyy H:mm";
        return android.text.format.DateFormat.format(dateFormat, date).toString();
    }

    public String shareTweetString(Context context) {
        return "Tweet: " + text + ", Date: " + getDateString() + ", Author: "  + author.getFullName();

    }
}
