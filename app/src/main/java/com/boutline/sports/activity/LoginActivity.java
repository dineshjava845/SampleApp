package com.boutline.sports.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.boutline.sports.R;
import com.boutline.sports.helpers.BoXmppConnection;
import com.boutline.sports.helpers.LoginAsyncResponse;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class LoginActivity extends Activity implements LoginAsyncResponse{

    Typeface defaultFont;
    Typeface defaultFontBold;

    //XMPPTCPConnection connection;
    boolean isConnected;

    @InjectView(R.id.loginToolbar) Toolbar                  toolbar;
    @InjectView(R.id.loginToolbarTextView) TextView         loginToolbarTextView;
    @InjectView(R.id.signInButton)Button                    signInButton;
    @InjectView(R.id.emailLabel)TextView                    emailLabelvar;
    @InjectView(R.id.passwordLabel)TextView                    passwordLabelvar;
    @InjectView(R.id.createAccountTextView)TextView         createAccountTextView;
    @InjectView(R.id.forgotPasswordTextView)TextView        forgotPasswordTextView;
    @InjectView(R.id.emailUsernameEditText)TextView         emailUsernameEditText;
    @InjectView(R.id.passwordEditText)TextView              passwordEditText;

    @OnClick(R.id.forgotPasswordTextView)
    void forgotPassword()
    {
        Intent startLoginHelpActivity = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
        startActivity(startLoginHelpActivity);
        overridePendingTransition(R.anim.pushupin, R.anim.pushupout);
    }
    @OnClick(R.id.createAccountTextView)
    void createNewAccount()
    {
        Intent startRegisterActivity = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(startRegisterActivity);
        overridePendingTransition(R.anim.pushdownin, R.anim.pushdownout);
    }
    @OnClick(R.id.signInButton)
    void signIn()
    {
        signInButton.setText("Signing In...");
        if (emailUsernameEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()) {
            YoYo.with(Techniques.Shake).playOn(findViewById(R.id.linearLayoutLoginActivity));
            Toast.makeText(getApplicationContext(), "All fields are compulsory", Toast.LENGTH_SHORT).show();
            if(emailUsernameEditText.getText().toString().isEmpty())
                emailUsernameEditText.requestFocus();
            else
                passwordEditText.requestFocus();
            signInButton.setText("Sign In");
        } else {

            ConnectToXMPPServerInBackground task = new ConnectToXMPPServerInBackground();
            task.delegate = this;
            task.execute();

            /*XMPP xmpp = new XMPP("192.168.10.69", emailUsernameEditText.getText().toString().trim()
                    , passwordEditText.getText().toString().trim());
            xmpp.connect();*/

            /*connectToXMPPServerInBackground task = new connectToXMPPServerInBackground();
            task.delegate = this;
            task.execute();*/


            /*Intent i = new Intent(getBaseContext(), ChannelSettingsActivity.class);
            startActivity(i);
            finish();
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);*/
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        defaultFont = Typeface.createFromAsset(getAssets(), "fonts/avonixorp.ttf");
        defaultFontBold = Typeface.createFromAsset(getAssets(), "fonts/avonixorpbold.ttf");

        loginToolbarTextView.setTypeface(defaultFontBold);
        passwordLabelvar.setTypeface(defaultFont);
        emailLabelvar.setTypeface(defaultFont);
        signInButton.setTypeface(defaultFontBold);
        createAccountTextView.setTypeface(defaultFontBold);
        forgotPasswordTextView.setTypeface(defaultFont);
        emailUsernameEditText.setTypeface(defaultFont);
        passwordEditText.setTypeface(defaultFont);

        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(getApplicationContext()).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;
                emailUsernameEditText.setText(possibleEmail);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getBaseContext(), RegisterActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.pushdownin, R.anim.pushdownout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    @Override
    public void processFinish(boolean isLoggedIn) {
        if(isLoggedIn) {
            Intent i = new Intent(getBaseContext(), MeActivity.class);
            startActivity(i);
            finish();
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        }
    }

    private class ConnectToXMPPServerInBackground extends AsyncTask<Void, Void, Boolean>
    {
        public LoginAsyncResponse delegate = null;

        @Override
        protected Boolean doInBackground(Void... params) {

            BoXmppConnection boXmppConnection = BoXmppConnection.getInstance();

            isConnected = boXmppConnection.loginToXmpp(emailUsernameEditText.getEditableText().toString(),passwordEditText.getEditableText().toString());


            return isConnected;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            delegate.processFinish(aBoolean);
            Toast.makeText(LoginActivity.this,"logged in",Toast.LENGTH_SHORT).show();
        }
    }
}
