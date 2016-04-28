package com.boutline.sports.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.boutline.sports.R;
import com.boutline.sports.models.Tag;
import com.boutline.sports.models.TagView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 7/7/2015.
 */
public class GroupSettingsActivity extends Activity {
    Typeface defaultFont, defaultFontBold;
    @InjectView(R.id.groupSettingsToolbarTextView)TextView groupSettingsToolbarTextView;
    @InjectView(R.id.tagview)TagView tagview;
    @InjectView(R.id.groupTagsLabelTextView)TextView groupTagsLabelTextView;
    @InjectView(R.id.editGroupTagsTextView)TextView editGroupTagsTextView;
    @InjectView(R.id.membersTextView)TextView membersTextView;
    @InjectView(R.id.inviteOthersTextView)TextView inviteOthersTextView;
    @InjectView(R.id.notificationSettingsTextView)TextView notificationSettingsTextView;
    @InjectView(R.id.creatorLabelTextView)TextView creatorLabelTextView;
    @InjectView(R.id.leaveGroupTextView)TextView leaveGroupTextView;
    @InjectView(R.id.renameGroupTextView)TextView renameGroupTextView;
    @InjectView(R.id.leftMenu) ImageView leftMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_settings);
        ButterKnife.inject(this);

        defaultFont = Typeface.createFromAsset(getAssets(), "fonts/avonixorp.ttf");
        defaultFontBold = Typeface.createFromAsset(getAssets(), "fonts/avonixorpbold.ttf");
        groupSettingsToolbarTextView.setTypeface(defaultFontBold);
        groupTagsLabelTextView.setTypeface(defaultFont);
        editGroupTagsTextView.setTypeface(defaultFont);
        leaveGroupTextView.setTypeface(defaultFont);
        creatorLabelTextView.setTypeface(defaultFont);
        notificationSettingsTextView.setTypeface(defaultFont);
        inviteOthersTextView.setTypeface(defaultFont);
        membersTextView.setTypeface(defaultFont);
        renameGroupTextView.setTypeface(defaultFont);


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
    @OnClick(R.id.leftMenu)
    void send() {
        Intent i = new Intent(getBaseContext(), GroupActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
    }
    @OnClick(R.id.editGroupTagsTextView)
    void goToEditGroupTags() {
        Intent i = new Intent(getBaseContext(), GroupTagsActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
    }
    @OnClick(R.id.membersTextView)
    void goToMembers() {
        Intent i = new Intent(getBaseContext(), MembersActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
    }
}
