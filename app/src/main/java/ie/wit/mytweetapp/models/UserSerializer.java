package ie.wit.mytweetapp.models;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import ie.wit.mytweetapp.models.User;

public class UserSerializer {
    private Context mContext;
    private String mFilename;

    public UserSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    public void saveUsers(ArrayList<User> users) throws JSONException, IOException {
        // build an array in JSON
        JSONArray array = new JSONArray();
        for (User c : users)
            array.put(c.toJSON());

        // write the file to disk
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public ArrayList<User> loadUsers() throws IOException, JSONException {
        ArrayList<User> users = new ArrayList<User>();
        BufferedReader reader = null;
        try {
            // open and read the file into a StringBuilder
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                // line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            // parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            // build the array of users from JSONObjects
            for (int i = 0; i < array.length(); i++) {
                users.add(new User(array.getJSONObject(i)));
            }
        }
        catch (FileNotFoundException e) {
            // we will ignore this one, since it happens when we start fresh
        }
        finally {
            if (reader != null)
                reader.close();
        }
        return users;
    }
}