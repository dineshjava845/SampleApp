package com.boutline.sports.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.boutline.sports.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ForgotPasswordActivity extends Activity {

    @InjectView(R.id.forgotPasswordToolbar) Toolbar             toolbar;
    @InjectView(R.id.forgotPasswordToolbarTextView) TextView    forgotPasswordToolbarTextView;
    @InjectView(R.id.helperEmailLabel) TextView                 helperEmailLabelvar;
    @InjectView(R.id.resetPasswordButton)Button                 resetPasswordButton;
    @InjectView(R.id.helperEmailEditText)EditText               helperEmailEditText;
    @InjectView(R.id.rememberPasswordTextView)TextView          rememberPasswordTextView;
    @InjectView(R.id.backButton)ImageView                       backButton;

    @OnClick(R.id.rememberPasswordTextView)
            void iRememberedMyPassword()
    {
        Intent startLoginActivity = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
        startActivity(startLoginActivity);
        overridePendingTransition(R.anim.pushdownin, R.anim.pushdownout);
    }

    @OnClick(R.id.resetPasswordButton)
            void cantRememberSoReset()
    {
        if (helperEmailEditText.getText().toString().isEmpty()) {
            YoYo.with(Techniques.Shake).playOn(findViewById(R.id.helperEmailEditText));
            Toast.makeText(getApplicationContext(), "Enter a valid email id", Toast.LENGTH_SHORT).show();
        } else {
            resetPasswordButton.setText("Sending...");
            Toast.makeText(getApplicationContext(), "Check your mail for password reset instructions.", Toast.LENGTH_LONG).show();
            Intent startLoginActivity = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            startActivity(startLoginActivity);
            overridePendingTransition(R.anim.pushdownin, R.anim.pushdownout);
        }
    }

    Typeface defaultFont;
    Typeface defaultFontBold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.inject(this);

        defaultFont = Typeface.createFromAsset(getAssets(), "fonts/avonixorp.ttf");
        defaultFontBold = Typeface.createFromAsset(getAssets(), "fonts/avonixorpbold.ttf");

        forgotPasswordToolbarTextView.setTypeface(defaultFontBold);
        resetPasswordButton.setTypeface(defaultFontBold);
        helperEmailEditText.setTypeface(defaultFont);
        helperEmailLabelvar.setTypeface(defaultFont);
        rememberPasswordTextView.setTypeface(defaultFontBold);

        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(getApplicationContext()).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;
                helperEmailEditText.setText(possibleEmail);
            }
        }
        backButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.pushdownin, R.anim.pushdownout);
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pushdownin, R.anim.pushdownout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forgot_password, menu);
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
