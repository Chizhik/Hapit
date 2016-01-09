package kr.ac.kaist.sprite.hapit;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
public class MypageFragment extends Fragment {


    private ArrayList<Habit> mHabitList;
    private HabitAdapter mAdapter;
    private ListView mLvHabit;
    private TextView mTvName;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mypage, container, false);
        mLvHabit = (ListView) v.findViewById(R.id.habitListView);
        mTvName = (TextView) v.findViewById(R.id.profile_username);
        mHabitList = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Habit");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    //                    Log.d("habit", "Retrieved " + objects.size() + " habits");
                    mTvName.setText(ParseUser.getCurrentUser().getUsername());
                    for (ParseObject pu : objects) {

                        String title = pu.getString("title");
                        String name = pu.getString("username");

                        String[] DaysOfWeek = pu.getString("DaysOfWeek").split("/");

                        Calendar c = Calendar.getInstance();
                        int cDay = c.get(Calendar.DAY_OF_WEEK);
                        if (DaysOfWeek[(cDay+5)%7] == "0") {continue;}

                        Habit h = new Habit(name, title);
                        mHabitList.add(h);

                    }
                    mAdapter = new HabitAdapter(getActivity(), R.layout.habitlist, mHabitList);
                    mLvHabit.setAdapter(mAdapter);
                } else {
                    Log.d("user", "Error: " + e.getMessage());
                }
            }
        });




        //        Jump to HabitSet Activity
        Button mAddHabitButton = (Button) v.findViewById(R.id.addHabitButton);
        Button bLogout = (Button) v.findViewById(R.id.bLogout);
        mAddHabitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HabitSet.class);
                getActivity().startActivity(intent);

            }
        });


        bLogout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Intent intent = new Intent(getActivity(), Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();
            }
        });



        return v;
    }

}