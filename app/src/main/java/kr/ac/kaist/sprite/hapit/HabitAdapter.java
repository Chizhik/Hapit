package kr.ac.kaist.sprite.hapit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pika on 2015-12-20.
 */
public class HabitAdapter extends ArrayAdapter<Habit> {

    private Context mContext;
    private int mResource;
    private ArrayList<Habit> mList;
    private LayoutInflater mInflater;



    /*
     * @param context
     * @param layoutResource
     * @param objects
     */

    public HabitAdapter(Context context, int layoutResource, ArrayList<Habit> objects) {
        super(context, layoutResource, objects);
        this.mContext = context;
        this.mResource = layoutResource;
        this.mList = objects;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        final Habit habit = mList.get(position);

        if (convertView == null) {
            convertView = mInflater.inflate(mResource, null);
        }

        if (habit != null) {
            TextView tvTitle = (TextView) convertView.findViewById(R.id.habit_title);
            tvTitle.setText(habit.getTitle());
        }
        return convertView;
    }
}