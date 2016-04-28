package com.boutline.sports.models;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Administrator on 7/7/2015.
 */
public class Group {

    public static final String TABLE_NAME = "`" + "Group" + "`";

    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_PLAYERCOUNT = "player_count";
    public static final String COL_PLAYERPOSITION = "player_position";
    public static final String COL_ADMINID = "admin_id";
    public static final String COL_TOKEN = "token";

    public static final String[] FIELDS = {COL_ID, COL_NAME, COL_PLAYERCOUNT, COL_PLAYERPOSITION, COL_ADMINID, COL_TOKEN};

    public static final String CREATE_TABLE =
            "CREATE TABLE "
                    + TABLE_NAME +
                    " ("
                    + COL_ID + " INTEGER PRIMARY KEY, "
                    + COL_NAME + " TEXT , "
                    + COL_PLAYERCOUNT + " INTEGER , "
                    + COL_PLAYERPOSITION + " INTEGER , "
                    + COL_ADMINID + " INTEGER , "
                    + COL_TOKEN + " TEXT " +
                    ")";

    public  static final String DROP_TABLE = "DROP TABLE boutlinedb." + TABLE_NAME;

    public static final String DELETE_TABLE_VALUES = "DELETE FROM " + TABLE_NAME;

    public long id = -1;
    public String name = "";
    public long player_count;
    public long player_position;
    public long admin_id;
    public String token = "";

    public Group(){}

    public Group(Cursor cursor)
    {
        this.id = cursor.getLong(0);
        this.name = cursor.getString(1);
        this.player_count = cursor.getLong(9);
        this.player_position = cursor.getLong(10);
        this.admin_id = cursor.getLong(11);
        this.token = cursor.getString(12);
    }

    public ContentValues getContent()
    {
        final ContentValues values = new ContentValues();

        values.put(COL_NAME, name);
        values.put(COL_ID, id);
        values.put(COL_PLAYERCOUNT, player_count);
        values.put(COL_PLAYERPOSITION, player_position);
        values.put(COL_ADMINID, admin_id);
        values.put(COL_TOKEN, token);

        return values;
    }
}