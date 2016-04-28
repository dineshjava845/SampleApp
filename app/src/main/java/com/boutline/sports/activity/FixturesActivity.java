package com.boutline.sports.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.boutline.sports.R;
import com.boutline.sports.helpers.ResideMenu;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 6/30/2015.
 */
public class FixturesActivity extends Activity {
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
    Typeface defaultFont;
    Typeface defaultFontBold;

    @InjectView(R.id.gameTitle)TextView gameTitle;
    @InjectView(R.id.gameDesc)TextView gameDesc;
    @InjectView(R.id.gameTour)TextView gameTour;
    @InjectView(R.id.gameTitle2)TextView gameTitle2;
    @InjectView(R.id.gameDesc2)TextView gameDesc2;
    @InjectView(R.id.gameTour2)TextView gameTour2;
    @InjectView(R.id.searchtags)EditText searchEditText;
    @InjectView(R.id.matchDate)TextView matchDate;
    @InjectView(R.id.matchMonth)TextView matchMonth;
    @InjectView(R.id.fixturesToolbarTextView)TextView fixturesToolbarTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);
        ButterKnife.inject(this);

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.5f);

        defaultFont = Typeface.createFromAsset(getAssets(), "fonts/avonixorp.ttf");
        defaultFontBold = Typeface.createFromAsset(getAssets(), "fonts/avonixorpbold.ttf");

        fixturesToolbarTextView.setTypeface(defaultFontBold);
        matchDate.setTypeface(defaultFontBold);
        matchMonth.setTypeface(defaultFontBold);
        gameTitle.setTypeface(defaultFontBold);
        gameDesc.setTypeface(defaultFont);
        gameTour.setTypeface(defaultFont);
        gameTitle2.setTypeface(defaultFontBold);
        gameDesc2.setTypeface(defaultFont);
        gameTour2.setTypeface(defaultFont);
        searchEditText.setTypeface(defaultFont);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
    }
}
