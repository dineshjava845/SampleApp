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
 * Created by Administrator on 5/15/2015.
 */
public class LeftMenuAdapter extends ArrayAdapter<String> {

    ArrayList<String> mMenuItems;
    Context mContext;
    int layout;

    Typeface defaultFont;
    Typeface defaultFontBold;


    public LeftMenuAdapter(Context context, int resource, ArrayList<String> menuItems) {
        super(context, resource, menuItems);
        this.mContext = context;
        this.layout = resource;
        this.mMenuItems = menuItems;
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
            view = mLayoutInflater.inflate(R.layout.left_menu_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

            defaultFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/avonixorp.ttf");
            defaultFontBold = Typeface.createFromAsset(mContext.getAssets(), "fonts/avonixorpbold.ttf");

            viewHolder.leftMenuItemTextView.setTypeface(defaultFont);

        }

        viewHolder.leftMenuItemTextView.setText(mMenuItems.get(position));


        return view;
    }

    static class ViewHolder
    {

        @InjectView(R.id.leftMenuItemTextView) TextView leftMenuItemTextView;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
