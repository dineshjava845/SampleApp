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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 5/19/2015.
 */
public class ChatListAdapter extends ArrayAdapter<String> {

    ArrayList<String> mStringArrayList;
    Context mContext;
    int layout;

    Typeface defaultFont;
    Typeface defaultFontBold;

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm");

    public ChatListAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mStringArrayList = objects;
        this.layout = resource;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        String message = mStringArrayList.get(position);

        ViewHolder viewHolder;

        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            view = mLayoutInflater.inflate(R.layout.chat_user_layout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

            defaultFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/avonixorp.ttf");
            defaultFontBold = Typeface.createFromAsset(mContext.getAssets(), "fonts/avonixorpbold.ttf");

            viewHolder.uname1.setTypeface(defaultFontBold);
            viewHolder.time1.setTypeface(defaultFont);
            viewHolder.fname1.setTypeface(defaultFont);
        }

        viewHolder.time1.setText(SIMPLE_DATE_FORMAT.format(System.currentTimeMillis()/1000));
        viewHolder.uname1.setText("anandsatyan");
        viewHolder.fname1.setText(message);

        return view;
    }


    public static class ViewHolder
    {
        @InjectView(R.id.pic1) ImageView pic1;
        @InjectView(R.id.uname1)TextView uname1;
        @InjectView(R.id.time1)TextView time1;
        @InjectView(R.id.fname1)TextView fname1;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }

    }
}
