package kr.ac.kaist.sprite.hapit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class Login extends Activity implements View.OnClickListener {

    Button bLogin;
    EditText lgUsername, lgPassword;
    TextView RegisterLink;
    static Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent loginIntent = new Intent(this, MainActivity.class);
            loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(loginIntent);
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        c = this;

        lgUsername = (EditText) findViewById(R.id.lgUsername);
        lgPassword = (EditText) findViewById(R.id.lgPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        RegisterLink = (TextView) findViewById(R.id.RegisterLink);

        bLogin.setOnClickListener(this);
        RegisterLink.setOnClickListener(this);
    }


    @Override
    public void onClick(final View v) {
        switch(v.getId()){
            case R.id.bLogin:
                ParseUser.logInInBackground(lgUsername.getText().toString(),
                        lgPassword.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (e == null) {
                                    //startApp(v);
                                    Intent loginIntent = new Intent(Login.c, MainActivity.class);
                                    loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(loginIntent);
                                    finish();
                                } else {
                                    Log.d("ParseException", e.toString());
                                }
                            }
                        });

                break;
            case R.id.RegisterLink:

                startActivity(new Intent(this, Register.class));
                finish();

                break;
        }
    }
}
