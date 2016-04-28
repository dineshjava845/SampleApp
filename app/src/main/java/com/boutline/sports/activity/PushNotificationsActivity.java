package com.boutline.sports.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.boutline.sports.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class PushNotificationsActivity extends Activity {

    Typeface defaultFont;
    Typeface defaultFontBold;

    @InjectView(R.id.pushNotificationsToolbar)Toolbar                   pushNotificationsToolbar;
    @InjectView(R.id.pushNotificationsBackImageView)ImageView           pushNotificationsBackImageView;
    @InjectView(R.id.pushNotificationsToolbarTextView)TextView          pushNotificationsToolbarTextView;
    @InjectView(R.id.sendPushNotificationsTextView)TextView             sendPushNotificationsTextView;
    @InjectView(R.id.pushNotificationsAllActivityTextView)TextView      pushNotificationsAllActivityTextView;
    @InjectView(R.id.pushNotificationsChatTextView)TextView         pushNotificationsChatTextView;
    @InjectView(R.id.pushNotificationsFixturesTextView)TextView         pushNotificationsFixturesTextView;
    @InjectView(R.id.pushNotificationsNewsTextView)TextView         pushNotificationsNewsTextView;
    @InjectView(R.id.pushNotificationsNothingTextView)TextView          pushNotificationsNothingTextView;

    @OnClick(R.id.pushNotificationsBackImageView)
    void onBackClick() {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notifications);
        ButterKnife.inject(this);

        defaultFont = Typeface.createFromAsset(getAssets(), "fonts/avonixorp.ttf");
        defaultFontBold = Typeface.createFromAsset(getAssets(), "fonts/avonixorpbold.ttf");

        pushNotificationsToolbarTextView.setTypeface(defaultFontBold);
        sendPushNotificationsTextView.setTypeface(defaultFontBold);
        pushNotificationsAllActivityTextView.setTypeface(defaultFont);
        pushNotificationsChatTextView.setTypeface(defaultFont);
        pushNotificationsFixturesTextView.setTypeface(defaultFont);
        pushNotificationsNewsTextView.setTypeface(defaultFont);
        pushNotificationsNothingTextView.setTypeface(defaultFont);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_push_notifications, menu);
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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
    }
}
