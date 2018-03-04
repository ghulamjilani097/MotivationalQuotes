package com.memor.thinkers.jilani.motivationalquotes;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.LauncherActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by Jilani on 13-01-2018.
 */

public class ViewPagerAdapter extends PagerAdapter  {
    Activity activity;
    LayoutInflater inflater;
    List<QuotesStructure> listItems;
    ImageView share,theme;
    static ViewGroup container2=null;
    private boolean isNightModeEnabled = false;
    static int i;
    static boolean mode,language;
//    NotificationCompat.Builder notification;
//    private static final int Unique=4562;



    public ViewPagerAdapter(List<QuotesStructure> listItems, Activity activity,boolean mode, int i, boolean language) {
        this.listItems = listItems;
        this.activity=activity;
        this.mode=mode;
        this.i=i;
        this.language=language;
    }


    @Override
    public int getCount() {
        return listItems.size();

    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        inflater=(LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview=inflater.inflate(R.layout.viewpager_items,container,false);

//        Random randGen = new Random();
//        final int rando = randGen.nextInt(100);

        Collections.shuffle(listItems);

        final QuotesStructure quotesStructure=listItems.get(position);
        final TextView quotes,author;
        quotes=(TextView)itemview.findViewById(R.id.quotes);
        author=(TextView)itemview.findViewById(R.id.author);
        share=(ImageView)itemview.findViewById(R.id.sharemsg);
        theme=(ImageView)itemview.findViewById(R.id.theme);
        //relativeLayout=(RelativeLayout) itemview.findViewById(R.id.viewpager_items);


        Typeface typeFace = Typeface.createFromAsset(activity.getAssets(), "Barlow-Regular.ttf");
        quotes.setTypeface(typeFace);
        Typeface typeFace1 = Typeface.createFromAsset(activity.getAssets(), "Barlow-SemiBold.ttf");
        author.setTypeface(typeFace1);

//        if(language)
//        {
//            Typeface typeFace3 = Typeface.createFromAsset(activity.getAssets(), "Kruti_Dev_010.ttf");
//            quotes.setTypeface(typeFace3);
//            Typeface typeFace4 = Typeface.createFromAsset(activity.getAssets(), "Barlow-SemiBold.ttf");
//            author.setTypeface(typeFace4);
//        }

        quotes.setText(quotesStructure.getQuotes());
        author.setText(quotesStructure.getAuthor());

        /*if(isNightModeEnabled)
        {
            quotes.setTextColor(Color.parseColor("#ffffff"));
            author.setTextColor(Color.parseColor("#0092cc"));
            container2.setBackgroundColor(Color.parseColor("#000000"));
        }*/
        

        if(mode==true){
//            activity.setTheme(R.style.BlackTheme);

            quotes.setTextColor(Color.parseColor("#ffffff"));
            author.setTextColor(Color.parseColor("#0092cc"));
            container.setBackgroundColor(Color.parseColor("#000000"));

//            Toast.makeText(activity, "night mode", Toast.LENGTH_SHORT).show();
        }
        else if(mode==false){
//            activity.setTheme(R.style.whiteTheme);
            quotes.setTextColor(Color.parseColor("#000000"));
            author.setTextColor(Color.parseColor("#8A8A8A"));
            container.setBackgroundColor(Color.parseColor("#ffffff"));

//            Toast.makeText(activity, "day mode", Toast.LENGTH_SHORT).show();
        }



//        String nextValue = images[RAND.nextInt(images.length)];

        container.addView(itemview);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, quotesStructure.getQuotes()+"\n-"+quotesStructure.getAuthor());
                activity.startActivity(Intent.createChooser(sharingIntent,"Share Via"));
            }
        });
        container2=container;

        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(activity, ""+listItems.size(), Toast.LENGTH_SHORT).show();
                    i++;
//                Toast.makeText(activity, ""+i, Toast.LENGTH_SHORT).show();
//                Handler handler = new Handler();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        i = 0;
                    }
                };
                if (i == 1)
                {
                    //Single click
//                    activity.setTheme(R.style.BlackTheme);
                    isNightModeEnabled = true;
                    mode=true;
//                    Toast.makeText(activity, ""+mode, Toast.LENGTH_SHORT).show();
                    Intent a=new Intent(activity, MainActivity.class);
                    a.putExtra("mode",mode);
                    a.putExtra("i",i);
                    a.putExtra("language",language);
                    a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(a);
                    activity.finish();
                    activity.overridePendingTransition(0,0);
//                        quotes.setTextColor(Color.parseColor("#ffffff"));
//                        author.setTextColor(Color.parseColor("#0092cc"));
//                        container.setBackgroundColor(Color.parseColor("#000000"));
                    Toast.makeText(activity, "Night Mode", Toast.LENGTH_SHORT).show();
                }
                else if (i == 2)
                {
                    //Double click
//                    activity.setTheme(R.style.BlackTheme);
                    i = 0;
                    isNightModeEnabled=false;
                    mode=false;
                    Intent b=new Intent(activity, MainActivity.class);
                    b.putExtra("mode",mode);
                    b.putExtra("i",i);
                    b.putExtra("language",language);
                    b.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(b);
                    activity.finish();
                    activity.overridePendingTransition(0,0);
//                    quotes.setTextColor(Color.parseColor("#000000"));
//                    author.setTextColor(Color.parseColor("#8A8A8A"));
//                    container.setBackgroundColor(Color.parseColor("#ffffff"));
                    Toast.makeText(activity, "Day Mode", Toast.LENGTH_SHORT).show();
                }
            }
        });


//        notification=new NotificationCompat.Builder(activity);
//        notification.setAutoCancel(true);
//
//        notification.setSmallIcon(R.drawable.splashlogo);
//        notification.setTicker("Motivational Quotes");
//        notification.setWhen(System.currentTimeMillis());
//        notification.setContentTitle("Motivational Quote");
////        notification.setContentText(quotesStructure.getQuotes());
//        notification.setStyle(new NotificationCompat.BigTextStyle().bigText(quotesStructure.getQuotes()+"\n"+"-"+quotesStructure.getAuthor()));
//        notification.setSound(alarmSound);
//        notification.setOnlyAlertOnce(true);
//        notification.setDefaults(Notification.DEFAULT_VIBRATE);
//
//        Intent intent=new Intent(activity,MainActivity.class);
//        PendingIntent pendingIntent=PendingIntent.getActivity(activity,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//        notification.setContentIntent(pendingIntent);
//
//        NotificationManager nm=(NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
//        nm.notify(Unique,notification.build());


////        Intent myIntent = new Intent(activity , Notification.class);
//        AlarmManager alarmManager = (AlarmManager)activity.getSystemService(ALARM_SERVICE);
//        PendingIntent pendingIntent = PendingIntent.getService(activity, 0, myIntent, 0);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 01);
//        calendar.set(Calendar.MINUTE, 32);
//        calendar.set(Calendar.SECOND, 00);
//
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000 , pendingIntent);

        return itemview;
    }

    @Override
    public void destroyItem(final ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View)object);
    }


}
