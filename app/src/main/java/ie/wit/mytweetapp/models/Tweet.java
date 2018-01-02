package ie.wit.mytweetapp.models;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Random;

public class Tweet {
    public Long date, id;
    public String text, contact, author;


    private static final String JSON_ID = "id";
    private static final String JSON_DATE = "date";
    private static final String JSON_TEXT = "text";
    private static final String JSON_AUTHOR = "author";

    public Tweet(User author) {
        id = unsignedLong();
        date = new Date().getTime();
        this.author = author.getFullName();
        contact = ": none";
    }

    public Tweet(JSONObject jsonObject) throws JSONException {
        id          = jsonObject.getLong(JSON_ID);
        date        = jsonObject.getLong(JSON_DATE);
        text        = jsonObject.getString(JSON_TEXT);
        author      = jsonObject.getString(JSON_AUTHOR);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSON_ID, Long.toString(id));
        jsonObject.put(JSON_DATE, date);
        jsonObject.put(JSON_TEXT, text);
        jsonObject.put(JSON_AUTHOR, author);
        return jsonObject;
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
        return "Tweet: " + text + ", Date: " + getDateString() + ", Author: "  + author;

    }
}
