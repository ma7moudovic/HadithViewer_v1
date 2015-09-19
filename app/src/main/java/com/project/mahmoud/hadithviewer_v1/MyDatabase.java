package com.project.mahmoud.hadithviewer_v1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Mahmoud on 9/19/2015.
 */
public class MyDatabase extends SQLiteAssetHelper {

    private static final String TABLE_Authors = "Authors";
    private static final String TABLE_Books = "Books";
    private static final String TABLE_Hadith = "Hadith";

    private static final String DATABASE_NAME = "DB.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public int getHadithCount (){

        String countQuery = "SELECT * FROM "+TABLE_Hadith;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);

        return cursor.getCount();

    }
    public Cursor getHadith(){

        String selectQuery = "SELECT Hadith FROM "+TABLE_Hadith;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c!=null) {

            c.moveToFirst();
        }else
        {
            c.close();
        }
        return c ;
    }

}
