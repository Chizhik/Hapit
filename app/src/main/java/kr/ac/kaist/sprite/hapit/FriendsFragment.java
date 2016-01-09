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
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pika on 2015-12-08.
 */
public class FriendsFragment extends Fragment {
    private ArrayList<Habit> mUserList;
    private UserAdapter mAdapter;
    private ListView mLvUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_friends, container, false);

        mLvUser = (ListView) v.findViewById(R.id.userList);
        mUserList = new ArrayList<>();


        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> objects, ParseException e) {
                if (e==null) {
                    Log.d("user", "Retrieved " + objects.size() + " users");
                    for (ParseUser pu : objects) {

                        String name = pu.getString("username");
                        Habit h = new Habit (name, "");
                        mUserList.add(h);


                    }
                    mAdapter = new UserAdapter(getActivity(), R.layout.friendslist, mUserList);
                    mLvUser.setAdapter(mAdapter);
                } else {
                    Log.d("user", "Error: " + e.getMessage());
                }
            }
        });

//        Log.d("ArrayListSize", Integer.toString(mUserList.size()));


        return v;


    }
}