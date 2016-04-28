package com.boutline.sports.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.boutline.sports.R;
import com.boutline.sports.adapters.InviteMembersAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class InviteMembersActivity extends Activity {

    Typeface defaultFont;
    Typeface defaultFontBold;

    @InjectView(R.id.inviteMembersToolbar)Toolbar                           inviteMembersToolbar;
    @InjectView(R.id.inviteMembersBackImageView)ImageView                   inviteMembersBackImageView;
    @InjectView(R.id.inviteMembersToolbarTextView)TextView                  inviteMembersToolbarTextView;
    @InjectView(R.id.inviteMembersSendTextView)TextView                     inviteMembersTextView;
    @InjectView(R.id.inviteMembersAutoCompleteTextView)AutoCompleteTextView inviteMembersAutoCompleteTextView;
    @InjectView(R.id.inviteMembersCustomInviteLinkTextView)TextView         inviteMembersCustomInviteLinkTextView;
    @InjectView(R.id.inviteMembersLinkTextView)TextView                     inviteMembersLinkTextView;
    @InjectView(R.id.inviteMembersCopyButton)Button                         inviteMembersCopyButton;
    @InjectView(R.id.inviteMembersListView)ListView                         inviteMembersListView;

    @OnClick(R.id.inviteMembersBackImageView)
    void onBackClick() {
        onBackPressed();
    }

    InviteMembersAdapter inviteMembersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_members);
        ButterKnife.inject(this);

        defaultFont = Typeface.createFromAsset(getAssets(), "fonts/avonixorp.ttf");
        defaultFontBold = Typeface.createFromAsset(getAssets(), "fonts/avonixorpbold.ttf");

        inviteMembersToolbarTextView.setTypeface(defaultFontBold);
        inviteMembersTextView.setTypeface(defaultFontBold);
        inviteMembersAutoCompleteTextView.setTypeface(defaultFont);
        inviteMembersCustomInviteLinkTextView.setTypeface(defaultFont);
        inviteMembersLinkTextView.setTypeface(defaultFont);
        inviteMembersCopyButton.setTypeface(defaultFontBold);


        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i=0;i<10;i++)
        {
            stringArrayList.add(i+"");
        }

        inviteMembersAdapter = new InviteMembersAdapter(getApplicationContext(), R.layout.item_invite_members, stringArrayList);
        inviteMembersListView.setAdapter(inviteMembersAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_invite_members, menu);
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
