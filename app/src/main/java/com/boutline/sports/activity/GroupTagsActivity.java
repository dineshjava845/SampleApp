package com.boutline.sports.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.boutline.sports.R;
import com.boutline.sports.models.Tag;
import com.boutline.sports.models.TagView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class GroupTagsActivity extends Activity {

    Typeface defaultFont;
    Typeface defaultFontBold;

    @InjectView(R.id.channelTagsToolbar)Toolbar                                     channelTagsToolbar;
    @InjectView(R.id.channelTagsBackImageView)ImageView                             channelTagsBackImageView;
    @InjectView(R.id.channelTagsToolbarTextView)TextView                            channelTagsToolbarTextView;
    @InjectView(R.id.channelTagsChannelNameTextView)TextView                        channelTagsChannelNameTextView;
    @InjectView(R.id.channelTagsTagsTextView)TextView                               channelTagsTagsTextView;
    @InjectView(R.id.channelTagsAddTagsTextView)TextView                            channelTagsAddTagsTextView;
    @InjectView(R.id.tagview)TagView tagview;
    @InjectView(R.id.tagview2)TagView                            tagview2;

    @OnClick(R.id.channelTagsBackImageView)
    void goBack()
    {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_tags);
        ButterKnife.inject(this);

        defaultFont = Typeface.createFromAsset(getAssets(), "fonts/avonixorp.ttf");
        defaultFontBold = Typeface.createFromAsset(getAssets(), "fonts/avonixorpbold.ttf");

        channelTagsToolbarTextView.setTypeface(defaultFontBold);
        channelTagsChannelNameTextView.setTypeface(defaultFontBold);
        channelTagsTagsTextView.setTypeface(defaultFont);
        channelTagsAddTagsTextView.setTypeface(defaultFont);

        tagview.setLineMargin(4f);//dp
        tagview.setTagMargin(4f);
        tagview2.setLineMargin(4f);//dp
        tagview2.setTagMargin(4f);

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
        tagview2.addTag(tag);

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
        tagview2.addTag(tag2);

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
        tagview2.addTag(tag3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_channel_tags, menu);
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
