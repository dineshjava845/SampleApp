package com.boutline.sports.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.boutline.sports.R;
import com.boutline.sports.helpers.ResideMenu;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 7/1/2015.
 */
public class PredictionsActivity extends Activity {
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

    @InjectView(R.id.predictionsActivityToolbar)Toolbar profileSettingsToolbar;
    @InjectView(R.id.predictionsToolbarTextView)TextView predictionsToolbarTextview;
    @InjectView(R.id.predictionA)TextView predictionA;
    @InjectView(R.id.predictionB)TextView predictionB;
    @InjectView(R.id.uname4)TextView uname4;
    @InjectView(R.id.time4)TextView time4;
    @InjectView(R.id.or)TextView or;
    @InjectView(R.id.desc)TextView desc;
    @InjectView(R.id.forTeamA)TextView forTeamA;
    @InjectView(R.id.forTeamB)TextView forTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictions);
        ButterKnife.inject(this);

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.5f);

        defaultFont = Typeface.createFromAsset(getAssets(), "fonts/avonixorp.ttf");
        defaultFontBold = Typeface.createFromAsset(getAssets(), "fonts/avonixorpbold.ttf");

        predictionA.setTypeface(defaultFontBold);
        predictionsToolbarTextview.setTypeface(defaultFont);
        or.setTypeface(defaultFont);
        predictionB.setTypeface(defaultFontBold);
        time4.setTypeface(defaultFont);
        desc.setTypeface(defaultFont);
        uname4.setTypeface(defaultFont);
        forTeamA.setTypeface(defaultFont);
        forTeamB.setTypeface(defaultFont);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
    }
}
