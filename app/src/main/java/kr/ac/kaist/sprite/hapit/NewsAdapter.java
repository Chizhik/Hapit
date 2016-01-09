package kr.ac.kaist.sprite.hapit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * Created by pika on 2015-12-20.
 */
public class NewsAdapter extends ArrayAdapter<Habit> {

    private Context mContext;
    private int mResource;
    private ArrayList<Habit> mList;
    private LayoutInflater mInflater;



    /*
     * @param context
     * @param layoutResource
     * @param objects
     */

    public NewsAdapter(Context context, int layoutResource, ArrayList<Habit> objects) {
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
            TextView tvName = (TextView) convertView.findViewById(R.id.feed_username);
            TextView tvContent = (TextView) convertView.findViewById(R.id.feed_content);
            ImageButton ibReminder = (ImageButton) convertView.findViewById(R.id.remindBtn);
            ibReminder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ParsePush parsePush = new ParsePush();
                    ParseQuery pQuery = ParseInstallation.getQuery();
                    pQuery.whereEqualTo("username", habit.getName());
                    parsePush.sendMessageInBackground(ParseUser.getCurrentUser().getUsername() + " reminds you about " + habit.getTitle(), pQuery);

                    Toast.makeText(mContext, R.string.send_reminder, Toast.LENGTH_SHORT)
                            .show();
                }
            });
            tvName.setText(habit.getName());
            tvContent.setText(habit.getTitle());

        }
        return convertView;
    }
}