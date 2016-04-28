package com.boutline.sports.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.boutline.sports.R;
import com.boutline.sports.helpers.BoXmppConnection;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnCheckedChanged;

public class RegisterActivity extends Activity {

    Typeface defaultFont;
    Typeface defaultFontBold;


    @InjectView(R.id.registerToolbar) Toolbar toolbar;
    @InjectView(R.id.registerToolbarTextView) TextView registerToolbarTextView;
    @InjectView(R.id.createAccountButton) Button createAccountButton;
    @InjectView(R.id.fullNameEditText) EditText fullNameEditText;
    @InjectView(R.id.userNameEditText) EditText userNameEditText;
    @InjectView(R.id.emailEditText) EditText emailEditText;
    @InjectView(R.id.fullNameLabel)TextView  fullNameLabelvar;
    @InjectView(R.id.userNameLabel)TextView  userNameLabelvar;
    @InjectView(R.id.passwordLabel)TextView  passwordLabelvar;
    @InjectView(R.id.emailLabel)TextView     emailLabelvar;
    @InjectView(R.id.passwordEditText) EditText passwordEditText;
    @InjectView(R.id.showPasswordCheckBox) CheckBox showPasswordCheckBox;
    @InjectView(R.id.signInTextView) TextView signInTextView;
    @InjectView(R.id.takes30) TextView takes30;

    @OnClick(R.id.signInTextView)
    void signMeIn()
    {
        Intent startLoginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(startLoginActivity);
        finish();
        overridePendingTransition(R.anim.pushupin, R.anim.pushupout);
    }

    @OnCheckedChanged(R.id.showPasswordCheckBox)
    void dontKnowWhatITyped(CompoundButton buttonView, boolean isChecked)
    {
        if (!isChecked) {
            passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }

    private boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    private boolean validUsername(String username) {
        String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        return pattern.matcher(username).matches();
    }

    @OnClick(R.id.createAccountButton)
    void interestedInBoutline()
    {
        createAccountButton.setText("Creating...");
        if (fullNameEditText.getText().toString().isEmpty()) {
            YoYo.with(Techniques.Shake).playOn(findViewById(R.id.fullNameEditText));
            Toast.makeText(getApplicationContext(), "Enter your full name.", Toast.LENGTH_SHORT).show();
            createAccountButton.setText("Create Account");
            fullNameEditText.requestFocus();
        }
        else if (fullNameEditText.getText().toString().length()<4) {
            YoYo.with(Techniques.Shake).playOn(findViewById(R.id.fullNameEditText));
            Toast.makeText(getApplicationContext(), "Full name is too short.", Toast.LENGTH_SHORT).show();
            createAccountButton.setText("Create Account");
            fullNameEditText.requestFocus();
        }
        else if (!validUsername(userNameEditText.getText().toString())) {
            YoYo.with(Techniques.Shake).playOn(findViewById(R.id.userNameEditText));
            Toast.makeText(getApplicationContext(), "Enter a username.", Toast.LENGTH_SHORT).show();
            createAccountButton.setText("Create Account");
            userNameEditText.requestFocus();
        }
        else if (userNameEditText.getText().toString().isEmpty()) {
            YoYo.with(Techniques.Shake).playOn(findViewById(R.id.userNameEditText));
            Toast.makeText(getApplicationContext(), "Username cannot have spaces and special characters.", Toast.LENGTH_SHORT).show();
            createAccountButton.setText("Create Account");
            userNameEditText.requestFocus();
        }
        else if (userNameEditText.getText().toString().length()<3) {
            YoYo.with(Techniques.Shake).playOn(findViewById(R.id.userNameEditText));
            Toast.makeText(getApplicationContext(), "Username is too short.", Toast.LENGTH_SHORT).show();
            createAccountButton.setText("Create Account");
            userNameEditText.requestFocus();
        }
        else if (emailEditText.getText().toString().isEmpty()) {
            YoYo.with(Techniques.Shake).playOn(findViewById(R.id.emailEditText));
            Toast.makeText(getApplicationContext(), "Enter your email id.", Toast.LENGTH_SHORT).show();
            createAccountButton.setText("Create Account");
            emailEditText.requestFocus();
        }
        else if (!validEmail(emailEditText.getText().toString())) {
            YoYo.with(Techniques.Shake).playOn(findViewById(R.id.emailEditText));
            Toast.makeText(getApplicationContext(), "Enter a valid email id.", Toast.LENGTH_SHORT).show();
            createAccountButton.setText("Create Account");
            emailEditText.requestFocus();
        }
        else if (emailEditText.getText().toString().length()<6) {
            YoYo.with(Techniques.Shake).playOn(findViewById(R.id.emailEditText));
            Toast.makeText(getApplicationContext(), "Email id is too short.", Toast.LENGTH_SHORT).show();
            createAccountButton.setText("Create Account");
            emailEditText.requestFocus();
        }
        else if (passwordEditText.getText().toString().isEmpty()) {
            YoYo.with(Techniques.Shake).playOn(findViewById(R.id.passwordEditText));
            Toast.makeText(getApplicationContext(), "Enter a password", Toast.LENGTH_SHORT).show();
            createAccountButton.setText("Create Account");
            passwordEditText.requestFocus();
        }
        else if (passwordEditText.getText().toString().length()<5) {
            YoYo.with(Techniques.Shake).playOn(findViewById(R.id.passwordEditText));
            Toast.makeText(getApplicationContext(), "Password is too short.", Toast.LENGTH_SHORT).show();
            createAccountButton.setText("Create Account");
            passwordEditText.requestFocus();
        }
        else
        {

            BoXmppConnection boXmppConnection = BoXmppConnection.getInstance();
            boXmppConnection.USERNAME = userNameEditText.getEditableText().toString();
            boXmppConnection.PASSWORD = passwordEditText.getEditableText().toString();
            boXmppConnection.NAME = fullNameEditText.getEditableText().toString();
            boXmppConnection.EMAIL = emailEditText.getEditableText().toString();

            boXmppConnection.connect();
           /* Intent i = new Intent(getBaseContext(), FavoriteSportsActivity.class);
            startActivity(i);
            createAccountButton.setText("Creating...");
            finish();
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);*/
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);

        defaultFont = Typeface.createFromAsset(getAssets(), "fonts/avonixorp.ttf");
        defaultFontBold = Typeface.createFromAsset(getAssets(), "fonts/avonixorpbold.ttf");

        registerToolbarTextView.setTypeface(defaultFontBold);
        createAccountButton.setTypeface(defaultFontBold);
        fullNameEditText.setTypeface(defaultFont);
        userNameEditText.setTypeface(defaultFont);
        emailEditText.setTypeface(defaultFont);
        passwordEditText.setTypeface(defaultFont);
        userNameLabelvar.setTypeface(defaultFont);
        emailLabelvar.setTypeface(defaultFont);
        fullNameLabelvar.setTypeface(defaultFont);
        passwordLabelvar.setTypeface(defaultFont);
        showPasswordCheckBox.setTypeface(defaultFont);
        signInTextView.setTypeface(defaultFontBold);
        takes30.setTypeface(defaultFont);

        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        String possibleUserName = getUsername();
        Account[] accounts = AccountManager.get(getApplicationContext()).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;
                userNameEditText.setText(possibleUserName);
                emailEditText.setText(possibleEmail);
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pushdownin, R.anim.pushdownout);
    }

    public String getUsername() {
        AccountManager manager = AccountManager.get(this);
        Account[] accounts = manager.getAccountsByType("com.google");
        List<String> possibleEmails = new LinkedList<String>();

        for (Account account : accounts) {
            possibleEmails.add(account.name);
        }
        if (!possibleEmails.isEmpty() && possibleEmails.get(0) != null) {
            String email = possibleEmails.get(0);
            String[] parts = email.split("@");
            if (parts.length > 0 && parts[0] != null)
                return parts[0];
            else
                return null;
        } else
            return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
