package ie.wit.mytweetapp.models;

import android.util.Log;

import java.util.ArrayList;

public class UserCollection {
    public ArrayList<User> users;

    public UserCollection() {
        this.users = new ArrayList<>();
    }

    public void newUser(User user) {
        users.add(user);
    }

    public User getUser(Long id) {
        for (User user : users) {
            if (id.equals(user.id)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByEmail(String email) {
        for (User user : users) {
            if (email.equals(user.email)) {
                return user;
            }
        }
        return null;
    }

    public boolean validUser (String email, String password) {
        Log.v("MyTweet", "finding user");
        for (User user : users) {
            if (user.email.equals(email) && user.password.equals(password)) {
                Log.v("MyTweet", "user found");
                return true;
            }
        }
        Log.v("MyTweet", "user not found");
        return false;
    }
}

