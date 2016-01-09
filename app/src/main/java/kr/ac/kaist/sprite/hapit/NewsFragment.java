package kr.ac.kaist.sprite.hapit;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by pika on 2015-12-08.
 */
public class NewsFragment extends Fragment {

    private ArrayList<Habit> mNewsList;
    private NewsAdapter mAdapter;
    private ListView mLvNews;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        mLvNews = (ListView) v.findViewById(R.id.newsList);
        mNewsList = new ArrayList<>();

//
//        mAdapter = new CustomAdapter(getActivity(), R.layout.newslist, mNewsList);
//        mLvNews.setAdapter(mAdapter);
////

        ParseQuery<ParseObject > query = ParseQuery.getQuery("Habit");
        query.whereEqualTo("privacy", "XXXXXXX");
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.d("habit", "Retrieved " + objects.size() + " habits");
                    for (ParseObject pu : objects) {

                        String title = pu.getString("title");
                        String name = pu.getString("username");

                        String[] DaysOfWeek = pu.getString("DaysOfWeek").split("/");
                        int index;

                        Calendar c = Calendar.getInstance();
                        int cDay = c.get(Calendar.DAY_OF_WEEK);
                        if (DaysOfWeek[(cDay+5)%7] == "0") {continue;}

                        Habit h = new Habit(name, title);
                        mNewsList.add(h);

                    }
                    mAdapter = new NewsAdapter(getActivity(), R.layout.newslist, mNewsList);
                    mLvNews.setAdapter(mAdapter);
                } else {
                    Log.d("user", "Error: " + e.getMessage());
                }
            }
        });


        return v;

    }

}