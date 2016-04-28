package com.boutline.sports.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.boutline.sports.R;
import com.boutline.sports.adapters.MembersAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MembersActivity extends Activity {

    Typeface defaultFont;
    Typeface defaultFontBold;

    MembersAdapter membersAdapter;

    @InjectView(R.id.membersToolbar)Toolbar                 membersToolbar;
    @InjectView(R.id.membersBackImageView)ImageView         membersBackImageView;
    @InjectView(R.id.membersToolbarTextView)TextView        membersToolbarTextView;
    @InjectView(R.id.membersInviteToolbarTextView)TextView  membersInviteToolbarTextView;
    @InjectView(R.id.membersTextView)TextView               membersTextView;
    @InjectView(R.id.membersUserName)TextView               membersUserName;
    @InjectView(R.id.membersUserFullName)TextView           membersUserFullName;
    //@InjectView(R.id.membersListView)ListView             membersListView;

    @OnClick(R.id.membersBackImageView)
    void onBackClick() {
        onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        ButterKnife.inject(this);

        defaultFont = Typeface.createFromAsset(getAssets(), "fonts/avonixorp.ttf");
        defaultFontBold = Typeface.createFromAsset(getAssets(), "fonts/avonixorpbold.ttf");

        membersToolbarTextView.setTypeface(defaultFontBold);
        membersInviteToolbarTextView.setTypeface(defaultFontBold);
        membersTextView.setTypeface(defaultFontBold);
        membersUserName.setTypeface(defaultFont);
        membersUserFullName.setTypeface(defaultFont);

        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i=0;i<10;i++)
        {
            stringArrayList.add(i+"");
        }

        membersAdapter = new MembersAdapter(getApplicationContext(), R.layout.item_members, stringArrayList);
        //membersListView.setAdapter(membersAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_members, menu);
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
