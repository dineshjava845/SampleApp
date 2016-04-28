package com.boutline.sports.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.boutline.sports.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 5/8/2015.
 */
public class InviteMembersAdapter extends ArrayAdapter<String> {

    Context mContext;
    int layout;
    ArrayList<String> stringArrayList;

    Typeface defaultFont;
    Typeface defaultFontBold;


    public InviteMembersAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.layout = resource;
        this.stringArrayList = objects;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder viewHolder;

        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            view = mLayoutInflater.inflate(R.layout.item_invite_members, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

            defaultFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/avonixorp.ttf");
            defaultFontBold = Typeface.createFromAsset(mContext.getAssets(), "fonts/avonixorpbold.ttf");

            viewHolder.inviteMembersUserName.setTypeface(defaultFontBold);
            viewHolder.inviteMembersUserFullName.setTypeface(defaultFont);
        }

        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.inviteMembersProfilePic)ImageView inviteMembersProfilePic;
        @InjectView(R.id.inviteMembersUserName)TextView inviteMembersUserName;
        @InjectView(R.id.inviteMembersUserFullName)TextView inviteMembersUserFullName;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}