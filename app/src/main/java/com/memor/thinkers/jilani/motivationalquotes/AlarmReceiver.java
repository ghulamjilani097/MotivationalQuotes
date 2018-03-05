package com.memor.thinkers.jilani.motivationalquotes;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.memor.thinkers.jilani.motivationalquotes.Sqlite.COLUMN_AUTHOR;
import static com.memor.thinkers.jilani.motivationalquotes.Sqlite.COLUMN_QUOTE;

public class AlarmReceiver extends BroadcastReceiver {

    Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    static QuotesStructure quotesStructure;
    long when = System.currentTimeMillis();
    private static final int Unique = 4562;
    Random randGen = new Random();
    Activity activity;
    String abc, jsd;

    @Override
    public void onReceive(Context context, Intent intent) {

        Sqlite mDbHelper = new Sqlite(context);
        mDbHelper.abcd();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM quotes_table", null);
        Log.d("dorami123", COLUMN_QUOTE + "-" + COLUMN_AUTHOR);
        while (res.moveToNext()) {
            abc = res.getString(res.getColumnIndex(COLUMN_QUOTE));
            jsd = res.getString(res.getColumnIndex(COLUMN_AUTHOR));
            Toast.makeText(context, "" + jsd, Toast.LENGTH_SHORT).show();
            Log.d("dorami1234", abc + "-" + jsd);
        }
    }
}