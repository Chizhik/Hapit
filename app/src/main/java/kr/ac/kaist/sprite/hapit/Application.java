package kr.ac.kaist.sprite.hapit;

import android.content.Intent;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

/**
 * Created by Alisher on 12/20/2015.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "dUPKh15MIOFvXCGRXpS7dzFxvF4nqQsE9eqac76I", "x8gRjSP5nfwOy6iOK1v8uD6iZSExmrIsgsNLOukB");
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}

//        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
//        installation.put("username", ParseUser.getCurrentUser().getUsername());
//        installation.saveInBackground();
//
//        ParsePush parsePush = new ParsePush();
//        ParseQuery pQuery = ParseInstallation.getQuery();
//        pQuery.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
//        parsePush.sendMessageInBackground(ParseUser.getCurrentUser().getString("username") + " reminds you about your habit", pQuery);
