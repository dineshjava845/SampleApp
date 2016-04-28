package com.boutline.sports.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.boutline.sports.R;
import com.boutline.sports.models.Group;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 5/15/2015.
 */
public class LeftMenuCursorAdapter extends SimpleCursorAdapter {

    Context mContext;
    Cursor cursor;
    Typeface defaultFont, defaultFontBold;
    private int layout;
    private LayoutInflater mLayoutInflater;

    public LeftMenuCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);

        this.mContext = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        if (getCursor() == null)
            return 0;
        return getCursor().getCount();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    public int getGroupId(int position)
    {
        cursor = getCursor();
        cursor.moveToPosition(position);
        return cursor.getInt(cursor.getColumnIndex(Group.COL_ID));

    }
    public String getGroupName(int position)
    {
        cursor = getCursor();
        cursor.moveToPosition(position);
        return cursor.getString(cursor.getColumnIndex(Group.COL_NAME));
    }
    @Override
    public View getView(int position, View view, final ViewGroup parent) {

        defaultFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/avonixorp.ttf");
        defaultFontBold = Typeface.createFromAsset(mContext.getAssets(), "fonts/avonixorpbold.ttf");

        ViewHolder holder = new ViewHolder();
        //Get the data item for this position
        cursor = getCursor();
        //Check if an existing view is being reused, otherwise inflate the view

        if (cursor.moveToPosition(position)) {

            if (view == null) {
                mLayoutInflater = LayoutInflater.from(mContext);
                view = mLayoutInflater.inflate(R.layout.left_menu_item, parent, false);

                holder.groupNameTextView = (TextView)view.findViewById(R.id.leftMenuItemTextView);
                holder.groupNameTextView.setTypeface(defaultFontBold);
                view.setTag(holder);
            }
            else {
                // the getTag returns the viewHolder object set as a tag to the view
                holder = (ViewHolder) view.getTag();
            }

            holder.groupNameTextView.setText(cursor.getString(cursor.getColumnIndex(Group.COL_NAME)));

        }

        return view;
    }

    static class ViewHolder
    {
        public TextView groupNameTextView;
    }
}
