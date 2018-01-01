package ie.wit.mytweetapp.models;

import java.util.Date;
import java.util.Random;

public class Tweet {
    public Long date, id;
    public String text;
    public User author;

    public Tweet(User author) {
        this.author = author;
        this.id = unsignedLong();
        date = new Date().getTime();
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
}
