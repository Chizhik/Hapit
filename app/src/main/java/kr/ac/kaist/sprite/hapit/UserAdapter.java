package kr.ac.kaist.sprite.hapit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pika on 2015-12-20.
 */
public class UserAdapter extends ArrayAdapter<Habit> {

    private Context mContext;
    private int mResource;
    private ArrayList<Habit> mList;
    private LayoutInflater mInflater;



    /*
     * @param context
     * @param layoutResource
     * @param objects
     */

    public UserAdapter(Context context, int layoutResource, ArrayList<Habit> objects) {
        super(context, layoutResource, objects);
        this.mContext = context;
        this.mResource = layoutResource;
        this.mList = objects;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        Habit habit = mList.get(position);

        if (convertView == null) {
            convertView = mInflater.inflate(mResource, null);
        }

        if (habit != null) {
            TextView tvName = (TextView) convertView.findViewById(R.id.friend_username);
            tvName.setText(habit.getName());

            ImageButton ibVisit = (ImageButton) convertView.findViewById(R.id.bVisit);
            ibVisit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext, MainActivity.class);
                    mContext.startActivity(intent);
                }
            });




        }
        return convertView;
    }
}