package mobile.computing.myloginapp.loginsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView usernameTV, passwordTV;
    EditText usernameET, passwordET;
    CheckBox saveLoginCHB;
    Button loginBT;
    public static boolean isChecked = false;
    public static boolean quickLogin = false;
    public static final String prefKey = "loginUsername";
    public static final String usernameKey = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameTV = (TextView) findViewById(R.id.usernameTextView);
        passwordTV = (TextView) findViewById(R.id.passwordTextView);
        usernameET = (EditText) findViewById(R.id.usernameEditText);
        passwordET = (EditText) findViewById(R.id.passwordEditText);
        saveLoginCHB = (CheckBox) findViewById(R.id.saveLoginCheckBox);
        loginBT = (Button) findViewById(R.id.loginButton);

        get();

        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = usernameET.getText().toString();
                String password = passwordET.getText().toString();

                /**
                 * If the user only clicks on the login button without filling anything!
                 */
                if (userName.isEmpty() && password.isEmpty() || userName.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "All fields must be filled!", Toast.LENGTH_SHORT).show();
                }

//                if (userName.isEmpty() || password.isEmpty())
//                {
//                    Toast.makeText(getApplicationContext(), "All fields must be filled!", Toast.LENGTH_SHORT).show();
//                }

                /**
                 * If the user clicks on the login button, without clicking on save login button
                 */
                if (!userName.isEmpty() && !password.isEmpty() && !saveLoginCHB.isChecked())
                {
                    isChecked = false;
                    Intent secondAct = new Intent(getApplicationContext(), welcomeSecondActivity.class);
                    secondAct.putExtra("usernameAct1", userName);
                    startActivity(secondAct);
                    resetForm();
                }

                /**
                 * if the user clicks on the login button and the ckeckbox 'save login' is checked
                 */
                if (!userName.isEmpty() && !password.isEmpty() && saveLoginCHB.isChecked())
                {
                    isChecked = true;
                    SharedPreferences preference_Act1 = getSharedPreferences(prefKey, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor_Act1 = preference_Act1.edit();

                    editor_Act1.putString(usernameKey, userName);
                    editor_Act1.commit();
                }

                if (isChecked == true)
                {
                    Intent secondActivity = new Intent(getApplicationContext(), welcomeSecondActivity.class);
                    startActivity(secondActivity);
                    finish();
                }

            }
        });

    }


    private void resetForm()
    {
        usernameET.setText(null);
        passwordET.setText(null);
    }

    private void get()
    {
        SharedPreferences preference_Act1 = getSharedPreferences(prefKey, Context.MODE_PRIVATE);
        if (preference_Act1.contains(usernameKey))
        {
            quickLogin = true;
            Intent secondAct = new Intent(getApplicationContext(), welcomeSecondActivity.class);
            startActivity(secondAct);
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_SHORT).show();
        }
    }
}