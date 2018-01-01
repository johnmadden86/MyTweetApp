package ie.wit.mytweetapp.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ie.wit.mytweetapp.R;
import ie.wit.mytweetapp.main.MyTweetApp;
import ie.wit.mytweetapp.models.Tweet;
import ie.wit.mytweetapp.models.TweetCollection;

import static ie.wit.mytweetapp.main.MyTweetApp.getApp;

public  class   TimelineFragment
        extends ListFragment {

    private MyTweetApp app;
    private TweetCollection tweetCollection;
    private TweetAdapter adapter;
    private ArrayList<Tweet> tweets;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.app_name);

        app = getApp();
        tweetCollection = app.tweetCollection;
        tweets = tweetCollection.tweets;

        adapter = new TweetAdapter(getActivity(), tweets);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.timeline_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.composeTweet:
                startActivity(new Intent(getActivity(), TweetActivity.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(getActivity(), "Settings Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete_all:
                Toast.makeText(getActivity(), "Delete Selected", Toast.LENGTH_SHORT).show();
            case R.id.menuLogout:
                Toast.makeText(getActivity(), "Logout Selected", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((TweetAdapter) getListAdapter()).notifyDataSetChanged();
    }

    class TweetAdapter extends ArrayAdapter<Tweet> {
        private Context context;

        public TweetAdapter(Context context, ArrayList<Tweet> tweets) {
            super(context, 0, tweets);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.row_layout, null);
            }
            Tweet tweet = getItem(position);
            TextView dateView = (TextView) convertView.findViewById(R.id.row_date);
            TextView textView = (TextView) convertView.findViewById(R.id.row_text);

            dateView.setText(tweet.getDateString());
            textView.setText(tweet.text);

            return convertView;
        }
    }

}
