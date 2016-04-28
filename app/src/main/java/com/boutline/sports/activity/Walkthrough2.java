/**
 * Tests:
 *  Activity slides in from the right -
 *  Ensure activity loads within 3 seconds - 
 *  ActionBar does not exist -
 *  Activity in fullscreen mode
 *  Back button slide wt1 from the left -
 *  Take a screenshot of the activity with and without drawer  
 *  Fonts are defined and assigned -
 *  swiping left should bring next wtactivity3 from right -
 *  swiping right should bring prev wtactivity1 from left -
 */

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


public class Walkthrough2 extends Activity {

    public String fontPath = "fonts/avonixorp.ttf";
    public Typeface tf;
    public String boldFontPath = "fonts/avonixorpbold.ttf";
    public Typeface btf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Set up UI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough2);
        tf = Typeface.createFromAsset(getAssets(), fontPath);
        btf = Typeface.createFromAsset(getAssets(), boldFontPath);
        //getActionBar().hide();

        // Define the controls

        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);

        TextView lblWalkthrough2 = (TextView) findViewById(R.id.lblWalkthrough2);
        ImageView wireFrame2 = (ImageView)findViewById(R.id.wireFrame2);
        // Assign the font types


        lblWalkthrough2.setTypeface(btf);

        // Animations

        Animation walkthroughAnim1 = AnimationUtils.loadAnimation(this, R.anim.walkthroughanim);
        walkthroughAnim1.setDuration(500);
        walkthroughAnim1.setRepeatCount(1);
        walkthroughAnim1.setRepeatMode(1);
        wireFrame2.startAnimation(walkthroughAnim1);


        Animation walkthroughAnim = AnimationUtils.loadAnimation(this, R.anim.walkthroughanim);
        walkthroughAnim.setDuration(400);
        walkthroughAnim.setRepeatCount(1);
        walkthroughAnim.setRepeatMode(1);
        walkthroughAnim.setZAdjustment(1);


        Animation walkthroughAnim2 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        walkthroughAnim2.setDuration(1000);
        walkthroughAnim2.setRepeatCount(1);
        walkthroughAnim2.setRepeatMode(1);
        lblWalkthrough2.startAnimation(walkthroughAnim2);



        // Declare the function for swipe left action

        container.setOnTouchListener(new OnSwipeTouchListener(Walkthrough2.this) {
            @Override
            public void onSwipeLeft() {
                goToNext();

            }

            @Override
            public void onSwipeRight() {
                goToPrev();

            }
        });

    }

    protected void goToNext() {
        Intent mainIntent = new Intent(Walkthrough2.this, RegisterActivity.class);
        startActivity(mainIntent);
        finish();
        overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
    }

    protected void goToPrev() {
        Intent mainIntent = new Intent(Walkthrough2.this, Walkthrough1.class);
        startActivity(mainIntent);
        finish();
        overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToPrev();
    }

}
