package com.boutline.sports.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.boutline.sports.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 5/18/2015.
 */
public class RightMenuAdapter extends ArrayAdapter<String> {

    ArrayList<String> mMenuItems;
    Context mContext;
    int layout;

    Typeface defaultFont;
    Typeface defaultFontBold;

    public RightMenuAdapter(Context context, int resource, ArrayList<String> menuItems) {
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
        else {

            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            view = mLayoutInflater.inflate(R.layout.right_menu_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

            defaultFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/avonixorp.ttf");
            defaultFontBold = Typeface.createFromAsset(mContext.getAssets(), "fonts/avonixorpbold.ttf");

            viewHolder.rightMenuItemTextView.setTypeface(defaultFont);

        }

        viewHolder.rightMenuItemTextView.setText(mMenuItems.get(position));

        return view;
    }

    static class ViewHolder
    {

        @InjectView(R.id.rightMenuItemTextView)
        TextView rightMenuItemTextView;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
