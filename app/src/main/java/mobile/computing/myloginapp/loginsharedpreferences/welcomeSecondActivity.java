package mobile.computing.myloginapp.loginsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class welcomeSecondActivity extends AppCompatActivity {

    TextView welcomeTV, usernameTV;
    boolean myPref = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_second);

        welcomeTV = (TextView) findViewById(R.id.welcomeTextView);
        usernameTV = (TextView) findViewById(R.id.usernameTextViewSecondAct);

        if (MainActivity.isChecked == true)
        {
            /**
             * SharedPreferences form main_Activity
             */
            SharedPreferences preference = getSharedPreferences(MainActivity.prefKey, MODE_PRIVATE);

            String user_name = preference.getString(MainActivity.usernameKey, null);
            usernameTV.setText(user_name);
        }

        else if (MainActivity.quickLogin == true)
        {
            SharedPreferences preference = getSharedPreferences(MainActivity.prefKey, MODE_PRIVATE);

            String user_name = preference.getString(MainActivity.usernameKey, null);
            usernameTV.setText(user_name);
        }

        else if (MainActivity.isChecked == false)
        {
            Bundle bundle = getIntent().getExtras();
            String username = bundle.getString("usernameAct1");

            if (bundle != null)
            {
                usernameTV.setText(username);
                myPref = true;
            }
        }
    }
}