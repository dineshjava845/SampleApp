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
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 5/8/2015.
 */
public class MembersAdapter extends ArrayAdapter<String> {

    Context mContext;
    int layout;
    ArrayList<String> stringArrayList;

    Typeface defaultFont;
    Typeface defaultFontBold;


    public MembersAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.layout = resource;
        this.stringArrayList = objects;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder viewHolder;

        if(view != null){
            viewHolder = (ViewHolder) view.getTag();
        }
        else
        {
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            view = mLayoutInflater.inflate(R.layout.item_members, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

            defaultFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/avonixorp.ttf");
            defaultFontBold = Typeface.createFromAsset(mContext.getAssets(), "fonts/avonixorpbold.ttf");

            viewHolder.membersUserName.setTypeface(defaultFontBold);
            viewHolder.membersUserFullName.setTypeface(defaultFont);
        }

        return view;
    }

    static class ViewHolder
    {
        @InjectView(R.id.membersProfilePic)ImageView      membersProfilePic;
        @InjectView(R.id.membersUserName)TextView         membersUserName;
        @InjectView(R.id.membersUserFullName)TextView     membersUserFullName;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
