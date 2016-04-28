package com.boutline.sports.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.boutline.sports.helpers.ResideMenu;
import com.boutline.sports.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 6/29/2015.
 */
public class NewsFeedActivity extends Activity {
    Typeface defaultFont;
    Typeface defaultFontBold;

    @InjectView(R.id.leftMenu)
    ImageView leftMenu;
    @InjectView(R.id.rightMenu)
    ImageView rightMenu;
    @OnClick(R.id.leftMenu)
    void openLeftMenu() {
        resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
    }

    @OnClick(R.id.rightMenu)
    void openRightMenu() {
        resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
    }

    ResideMenu resideMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        ButterKnife.inject(this);

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.6f);

        //Set up fonts
        defaultFont = Typeface.createFromAsset(getAssets(), "fonts/avonixorp.ttf");
        defaultFontBold = Typeface.createFromAsset(getAssets(), "fonts/avonixorpbold.ttf");

        TextView newsToolbarTextView = (TextView)findViewById(R.id.newsToolbarTextView);
        TextView uname3 = (TextView)findViewById(R.id.uname3);
        TextView time3 = (TextView)findViewById(R.id.time3);
        TextView fname3 = (TextView)findViewById(R.id.fname3);
        newsToolbarTextView.setTypeface(defaultFont);
        uname3.setTypeface(defaultFont);
        time3.setTypeface(defaultFont);
        fname3.setTypeface(defaultFont);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
    }
}
