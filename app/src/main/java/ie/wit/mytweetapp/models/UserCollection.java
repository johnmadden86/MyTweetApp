package ie.wit.mytweetapp.models;

import android.util.Log;

import java.util.ArrayList;

public class UserCollection {
    public ArrayList<User> users;
    private UserSerializer serializer;

    public UserCollection(UserSerializer serializer) {
        this.serializer = serializer;
        try {
            users = serializer.loadUsers();
        } catch (Exception e) {
            Log.v("MyTweet","Error loading users: " + e.getMessage());
            users = new ArrayList<>();
        };
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

    public boolean saveUsers() {
        try {
            serializer.saveUsers(users);
            Log.v("MyTweet", "Users saved to file");
            return true;
        } catch (Exception e) {
            Log.v("MyTweet","Error saving users: " + e.getMessage());
            return false;
        }
    }
}

