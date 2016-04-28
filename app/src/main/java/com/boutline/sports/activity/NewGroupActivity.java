package com.boutline.sports.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.boutline.sports.R;
import com.boutline.sports.models.Tag;
import com.boutline.sports.models.TagView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 7/7/2015.
 */
public class NewGroupActivity extends Activity {
    @InjectView(R.id.newGroupToolbarTextView)TextView groupToolbarTextView;
    @InjectView(R.id.groupNameLabel)TextView groupNameLabel;
    @InjectView(R.id.groupNameEditText)TextView groupNameEditText;
    @InjectView(R.id.groupTagsLabel)TextView groupTagsLabel;
    @InjectView(R.id.tagview)TagView tagview;
    @InjectView(R.id.searchtags)EditText searchtags;
    @InjectView(R.id.donebutton)TextView donebutton;

    Typeface defaultFont, defaultFontBold;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        ButterKnife.inject(this);

        defaultFont = Typeface.createFromAsset(getAssets(), "fonts/avonixorp.ttf");
        defaultFontBold = Typeface.createFromAsset(getAssets(), "fonts/avonixorpbold.ttf");
        groupToolbarTextView.setTypeface(defaultFontBold);
        groupNameLabel.setTypeface(defaultFont);
        groupNameEditText.setTypeface(defaultFont);
        groupTagsLabel.setTypeface(defaultFont);
        searchtags.setTypeface(defaultFont);
        donebutton.setTypeface(defaultFontBold);


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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
    }
}
