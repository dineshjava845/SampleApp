package com.boutline.sports.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boutline.sports.R;
import com.boutline.sports.helpers.OnSwipeTouchListener;

public class Walkthrough0 extends Activity {

    public String fontPath = "fonts/avonixorp.ttf";
    public Typeface tf;
    public String boldFontPath = "fonts/avonixorpbold.ttf";
    public Typeface btf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough0);


        //Set up fonts
        tf = Typeface.createFromAsset(getAssets(), fontPath);
        btf = Typeface.createFromAsset(getAssets(), boldFontPath);

        //Define the controls
        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);

        TextView swiper = (TextView) findViewById(R.id.swiper);
        TextView lblWalkthrough0 = (TextView) findViewById(R.id.lblWalkthrough0);
        ImageView wireFrame0 = (ImageView)findViewById(R.id.wireFrame0);

        //Assign the font types
        swiper.setTypeface(tf);

        lblWalkthrough0.setTypeface(btf);

        //Animations
        Animation walkthroughAnim1 = AnimationUtils.loadAnimation(this, R.anim.walkthroughanim);
        walkthroughAnim1.setDuration(500);
        walkthroughAnim1.setRepeatCount(1);
        walkthroughAnim1.setRepeatMode(1);
        wireFrame0.startAnimation(walkthroughAnim1);

        Animation walkthroughAnim2 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        walkthroughAnim2.setDuration(1000);
        walkthroughAnim2.setRepeatCount(1);
        walkthroughAnim2.setRepeatMode(1);
        lblWalkthrough0.startAnimation(walkthroughAnim2);

        Animation medalAnim = AnimationUtils.loadAnimation(this, R.anim.fadein);
        medalAnim.setDuration(2500);
        medalAnim.setRepeatCount(1);
        medalAnim.setRepeatMode(1);

        // Declare the function for gestures

        container.setOnTouchListener(new OnSwipeTouchListener(Walkthrough0.this) {
            @Override
            public void onSwipeLeft() {
                goToNext();
            }

            @Override
            public void onSwipeRight() {
            }
        });
    }

    protected void goToNext() {
        Intent mainIntent = new Intent(Walkthrough0.this, Walkthrough1.class);
        startActivity(mainIntent);
        finish();
        overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
