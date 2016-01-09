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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Register extends Activity implements View.OnClickListener {

    Button bRegister;
    EditText rgUsername, rgPassword;
    //EditText rgEmail;
//    TextView rgProceedAnyway;
    ParseUser user = new ParseUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //rgEmail = (EditText) findViewById(R.id.rgEmail);
        rgUsername = (EditText) findViewById(R.id.rgUsername);
        rgPassword = (EditText) findViewById(R.id.rgPassword);
        bRegister = (Button) findViewById(R.id.bRegister);
//        rgProceedAnyway = (TextView) findViewById(R.id.rgProceedAnyway);

        bRegister.setOnClickListener(this);
//        rgProceedAnyway.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()){
            case R.id.bRegister:
                user.setUsername(rgUsername.getText().toString());
                user.setPassword(rgPassword.getText().toString());
                //user.setEmail(rgEmail.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
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
        }
    }
}
