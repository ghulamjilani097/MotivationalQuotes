package com.memor.thinkers.jilani.motivationalquotes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter  {
    Activity activity;
    LayoutInflater inflater;
    List<QuotesStructure> listItems;
    ImageView share,theme;
    static ViewGroup container2=null;
    private boolean isNightModeEnabled = false;
    static int i;
    static boolean mode,language;



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
        Collections.shuffle(listItems);

        final QuotesStructure quotesStructure=listItems.get(position);
        final TextView quotes,author;
        quotes=(TextView)itemview.findViewById(R.id.quotes);
        author=(TextView)itemview.findViewById(R.id.author);
        share=(ImageView)itemview.findViewById(R.id.sharemsg);
        theme=(ImageView)itemview.findViewById(R.id.theme);
        Typeface typeFace = Typeface.createFromAsset(activity.getAssets(), "Barlow-Regular.ttf");
        quotes.setTypeface(typeFace);
        Typeface typeFace1 = Typeface.createFromAsset(activity.getAssets(), "Barlow-SemiBold.ttf");
        author.setTypeface(typeFace1);
        quotes.setText(quotesStructure.getQuotes());
        author.setText(quotesStructure.getAuthor());

        if(mode==true){
            quotes.setTextColor(Color.parseColor("#ffffff"));
            author.setTextColor(Color.parseColor("#0092cc"));
            container.setBackgroundColor(Color.parseColor("#000000"));
        }
        else if(mode==false){
            quotes.setTextColor(Color.parseColor("#000000"));
            author.setTextColor(Color.parseColor("#8A8A8A"));
            container.setBackgroundColor(Color.parseColor("#ffffff"));
        }
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
                i++;
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        i = 0;
                    }
                };
                if (i == 1)
                {
                    isNightModeEnabled = true;
                    mode=true;
                    shabana();
                    Toast.makeText(activity, "Night Mode", Toast.LENGTH_SHORT).show();
                }
                else if (i == 2)
                {
                    i = 0;
                    isNightModeEnabled=false;
                    mode=false;
                    shabana();
                    Toast.makeText(activity, "Day Mode", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return itemview;
    }

    @Override
    public void destroyItem(final ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View)object);
    }

    public void shabana(){
        Intent b=new Intent(activity, MainActivity.class);
        b.putExtra("mode",mode);
        b.putExtra("i",i);
        b.putExtra("language",language);
        b.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(b);
        activity.finish();
        activity.overridePendingTransition(0,0);
    }
}
