package ie.wit.mytweetapp.models;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static ie.wit.mytweetapp.models.Tweet.unsignedLong;

public class User {
    Long id;
    public String firstName, lastName;
    String email, password;

    private static final String JSON_ID = "id";
    private static final String JSON_FIRST_NAME = "firstName";
    private static final String JSON_LAST_NAME = "larstName";
    private static final String JSON_EMAIL = "email";
    private static final String JSON_PASSWORD = "password";

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.id = unsignedLong();
    }

    public User(JSONObject jsonObject) throws JSONException {
        id          = jsonObject.getLong(JSON_ID);
        firstName   = jsonObject.getString(JSON_FIRST_NAME);
        lastName    = jsonObject.getString(JSON_LAST_NAME);
        email       = jsonObject.getString(JSON_EMAIL);
        password    = jsonObject.getString(JSON_PASSWORD);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSON_ID, Long.toString(id));
        jsonObject.put(JSON_FIRST_NAME, firstName);
        jsonObject.put(JSON_LAST_NAME, lastName);
        jsonObject.put(JSON_EMAIL, email);
        jsonObject.put(JSON_PASSWORD, password);
        return jsonObject;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
