package com.memor.thinkers.jilani.motivationalquotes;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Sqlite extends SQLiteOpenHelper {

    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_QUOTE = "quote";
    public static final String DATABASE_NAME = "QuotesSQ";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "quotes_table";
    Context mContext;
    Activity activity;
    static QuotesStructure quotesStructure;
    Random randGen = new Random();
    static String mnc,jcd;

    public Sqlite(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE quotes_table(_id INTEGER PRIMARY KEY, quote TEXT, author TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    void jilani(Activity activity, List<QuotesStructure> listItems)
    {
        this.activity=activity;
        final int rando = randGen.nextInt(listItems.size());
        quotesStructure=listItems.get(rando);
        mnc=quotesStructure.getQuotes();
        jcd=quotesStructure.getAuthor();
        abcd();
    }

    public void abcd()
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_QUOTE, mnc);
        contentValues.put(COLUMN_AUTHOR, jcd);
        db.insert("quotes_table", null, contentValues);
        db.close();
    }
}