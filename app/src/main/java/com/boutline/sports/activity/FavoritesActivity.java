package com.boutline.sports.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.boutline.sports.R;
import com.boutline.sports.models.Tag;
import com.boutline.sports.models.TagView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FavoritesActivity extends Activity {

    Typeface defaultFont;
    Typeface defaultFontBold;

    @InjectView(R.id.favoritesToolbar) Toolbar toolbar;
    @InjectView(R.id.thingsYouLikeToolbarTextView)TextView      thingsYouLikeToolbarTextView;
    @InjectView(R.id.tellUsMoreTextView2)TextView               tellUsMoreTextView2;
    @InjectView(R.id.tellUsMoreTextView3)TextView               tellUsMoreTextView3;
    @InjectView(R.id.tagview)TagView                            tagview;
    @InjectView(R.id.searchtags)EditText searchtags;

    @OnClick(R.id.thingsYouLikeToolbarTextView)
    void onDone()
    {
        Intent i = new Intent(FavoritesActivity.this, MeActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        ButterKnife.inject(this);

        defaultFont = Typeface.createFromAsset(getAssets(), "fonts/avonixorp.ttf");
        defaultFontBold = Typeface.createFromAsset(getAssets(), "fonts/avonixorpbold.ttf");

        thingsYouLikeToolbarTextView.setTypeface(defaultFontBold);
        tellUsMoreTextView2.setTypeface(defaultFont);
        searchtags.setTypeface(defaultFont);
        tellUsMoreTextView3.setTypeface(defaultFont);

        tagview.setLineMargin(4f);//dp
        tagview.setTagMargin(4f);

        Tag tag = new Tag("Manchester United");
        tag.tagTextColor = Color.parseColor("#000000");
        tag.layoutColor =  Color.parseColor("#DDDDDD");
        tag.layoutColorPress = Color.parseColor("#555555");
        //or tag.background = this.getResources().getDrawable(R.drawable.custom_bg);
        tag.radius = 30f;
        tag.tagTextSize = 14f;
        tag.layoutBorderSize = 1f;
        tag.layoutBorderColor = Color.parseColor("#FFFFFF");
        tag.isDeletable = false;
        tagview.addTag(tag);

        Tag tag2 = new Tag("Chelsea");
        tag2.tagTextColor = Color.parseColor("#000000");
        tag2.layoutColor =  Color.parseColor("#DDDDDD");
        tag2.layoutColorPress = Color.parseColor("#555555");
        //or tag.background = this.getResources().getDrawable(R.drawable.custom_bg);
        tag2.radius = 30f;
        tag2.tagTextSize = 14f;
        tag2.layoutBorderSize = 1f;
        tag2.layoutBorderColor = Color.parseColor("#FFFFFF");
        tagview.addTag(tag2);

        Tag tag3 = new Tag("Arsenal");
        tag3.tagTextColor = Color.parseColor("#000000");
        tag3.layoutColor =  Color.parseColor("#DDDDDD");
        tag3.layoutColorPress = Color.parseColor("#555555");
        //or tag.background = this.getResources().getDrawable(R.drawable.custom_bg);
        tag3.radius = 30f;
        tag3.tagTextSize = 14f;
        tag3.layoutBorderSize = 1f;
        tag3.layoutBorderColor = Color.parseColor("#FFFFFF");
        tagview.addTag(tag3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_things_you_like, menu);
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
        Intent i = new Intent(FavoritesActivity.this, FavoriteSportsActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
    }
}


