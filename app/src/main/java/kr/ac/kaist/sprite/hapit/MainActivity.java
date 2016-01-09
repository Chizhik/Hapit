package kr.ac.kaist.sprite.hapit;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class MainActivity extends Activity {
    private TabHost mTabHost;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        ParseInstallation.getCurrentInstallation().put("username", ParseUser.getCurrentUser().getUsername());
        ParseInstallation.getCurrentInstallation().saveInBackground();

        //startActivity(new Intent(this, Login.class));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar
                .newTab()
                .setText("My Page")
                .setTabListener(
                        new TabListener<MypageFragment>(this, "tab1",
                                MypageFragment.class)));
        actionBar.addTab(actionBar
                .newTab()
                .setText("News")
                .setTabListener(
                        new TabListener<NewsFragment>(this, "tab2",
                                NewsFragment.class)));
        actionBar.addTab(actionBar
                .newTab()
                .setText("Friends")
                .setTabListener(
                        new TabListener<FriendsFragment>(this, "tab3",
                                FriendsFragment.class)));

        if (savedInstanceState != null) {
            actionBar.setSelectedNavigationItem(savedInstanceState.getInt(
                    "selectedTab", 0));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectedTab", getActionBar()
                .getSelectedNavigationIndex());
    }
}