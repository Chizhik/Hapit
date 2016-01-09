package kr.ac.kaist.sprite.hapit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class HabitSet extends Activity {
    private int i;
    private String title;
    private ArrayList<ToggleButton> buttons;
    private boolean[] checks = new boolean[7];
    private int category = 0;
    private int privacy = 0; //0 - public, 1 - private


    View.OnClickListener newClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int index = buttons.indexOf((ToggleButton) v);
            checks[index] = !checks[index];
            //((ToggleButton) v).setChecked(checks[index]);
        }
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitset);

//        Parse.enableLocalDatastore(getApplicationContext());
//        Parse.initialize(getApplicationContext(), "dUPKh15MIOFvXCGRXpS7dzFxvF4nqQsE9eqac76I", "x8gRjSP5nfwOy6iOK1v8uD6iZSExmrIsgsNLOukB");

        //ParseObject testObject = new ParseObject("Habit");
        //testObject.put("title", "gjlfxglkfj");
        //testObject.saveEventually();


        //ParseQuery<ParseObject> query = ParseQuery.getQuery("Habit");
        //query.whereEqualTo("user", "pie");
        //query.findInBackground(new FindCallback<ParseObject>() {
        //    public void done(List<ParseObject> habits, ParseException e) {
        //        if (e == null) {
        //            for (ParseObject h : habits)
        //                Log.d("fjksd", (String) h.get("title"));
        //            Log.d("score", "Retrieved " + habits.size() + " scores");
        //        } else {
        //            Log.d("score", "Error: " + e.getMessage());
        //        }
        //    }
        //});


        ToggleButton mo_b = (ToggleButton) findViewById(R.id.mo_b);
        ToggleButton tu_b = (ToggleButton) findViewById(R.id.tu_b);
        ToggleButton we_b = (ToggleButton) findViewById(R.id.we_b);
        ToggleButton th_b = (ToggleButton) findViewById(R.id.th_b);
        ToggleButton fr_b = (ToggleButton) findViewById(R.id.fr_b);
        ToggleButton sa_b = (ToggleButton) findViewById(R.id.sa_b);
        ToggleButton su_b = (ToggleButton) findViewById(R.id.su_b);
        buttons = new ArrayList<ToggleButton>();
        buttons.add(mo_b);
        mo_b.setOnClickListener(newClickListener);
        buttons.add(tu_b);
        tu_b.setOnClickListener(newClickListener);
        buttons.add(we_b);
        we_b.setOnClickListener(newClickListener);
        buttons.add(th_b);
        th_b.setOnClickListener(newClickListener);
        buttons.add(fr_b);
        fr_b.setOnClickListener(newClickListener);
        buttons.add(sa_b);
        sa_b.setOnClickListener(newClickListener);
        buttons.add(su_b);
        su_b.setOnClickListener(newClickListener);

        final ToggleButton private_b = (ToggleButton) findViewById(R.id.private_b);
        Button save_b = (Button) findViewById(R.id.save_b);
        final EditText title_t = (EditText) findViewById(R.id.edit_text);

        private_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (privacy == 0)
                    privacy = 1;
                else
                    privacy = 0;
            }
        });

        save_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelephonyManager mngr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                String ime = "000000000000000";


                String days_of_week;
                if (checks[0])
                    days_of_week = "1";
                else
                    days_of_week = "0";
                for (int j = 1; j < 7; j++) {
                    if (checks[j])
                        days_of_week = days_of_week.concat("/1");
                    else
                        days_of_week = days_of_week.concat("/0");
                }
                title = title_t.getText().toString();
                ParseObject testObject = new ParseObject("Habit");
                testObject.put("user", ParseUser.getCurrentUser());
                testObject.put("username", ParseUser.getCurrentUser().getUsername());
                testObject.put("title", title);
                // TODO: implement category
                //testObject.put("category", category);
                testObject.put("DaysOfWeek", days_of_week);
                //testObject.addAllUnique("DaysOfWeek", Arrays.asList(checks));
                if (privacy == 1)
                    testObject.put("privacy", ime);
                else
                    testObject.put("privacy", "XXXXXXX");
                testObject.saveEventually();

                Toast.makeText(HabitSet.this, R.string.save_toast, Toast.LENGTH_SHORT)
                        .show();

//                Intent intent = new Intent(HabitSet.this, MainActivity.class);
//                startActivity(intent);
                finish();


            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "HabitSet Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://kr.ac.kaist.sprite.hapit/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "HabitSet Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://kr.ac.kaist.sprite.hapit/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
//    }
}