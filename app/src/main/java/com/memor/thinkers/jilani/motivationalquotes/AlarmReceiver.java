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

/**
 * Created by Jilani on 29-01-2018.
 */

public class AlarmReceiver extends BroadcastReceiver {

    Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    static QuotesStructure quotesStructure;
    private static final int Unique=4562;
    Random randGen = new Random();
    Activity activity;
    String abc,jsd;


//    void jilani(Activity activity,List<QuotesStructure> listItems)
//    {
////    Collections.shuffle(listItems);
//
////    int pos=listItems.size();
//
//        this.activity=activity;
//
//        final int rando = randGen.nextInt(listItems.size());
//        quotesStructure=listItems.get(rando);
//
//
////        Log.d("notif", ""+quotesStructure.getQuotes()+"\n"+"-"+quotesStructure.getAuthor());
////        Toast.makeText(activity, ""+quotesStructure.getQuotes()+"\n"+"-"+quotesStructure.getAuthor(), Toast.LENGTH_SHORT).show();
//    }



    @Override
    public void onReceive(Context context, Intent intent) {

        Sqlite mDbHelper = new Sqlite(context);
        mDbHelper.abcd();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM quotes_table", null);
        Log.d("dorami123", COLUMN_QUOTE+"-"+COLUMN_AUTHOR);
        while (res.moveToNext()) {


            abc=res.getString(res.getColumnIndex(COLUMN_QUOTE));
            jsd=res.getString(res.getColumnIndex(COLUMN_AUTHOR));


            Toast.makeText(context, ""+jsd, Toast.LENGTH_SHORT).show();
            Log.d("dorami1234", abc+"-"+jsd);
        }


        NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.splashlogo)
                            .setContentTitle("Motivational Quote")
                            .setSound(alarmSound)
                            .setAutoCancel(true)
                            .setOnlyAlertOnce(true)
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .setStyle(new NotificationCompat.BigTextStyle().bigText(abc+"\n"+"-"+jsd));
            Intent resultIntent = new Intent(context, MainActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(Unique, mBuilder.build());

//        Log.d("notif1", ""+quotesStructure.getQuotes()+"\n"+"-"+quotesStructure.getAuthor());
//        Toast.makeText(context, ""+quotesStructure.getQuotes()+"\n"+"-"+quotesStructure.getAuthor(), Toast.LENGTH_SHORT).show();

//    }
    }



}
