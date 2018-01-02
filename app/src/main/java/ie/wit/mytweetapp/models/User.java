package ie.wit.mytweetapp.models;


import java.util.ArrayList;

import static ie.wit.mytweetapp.models.Tweet.unsignedLong;

public class User {
    Long id;
    public String firstName;
    public String lastName;
    String email;
    String password;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.id = unsignedLong();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
