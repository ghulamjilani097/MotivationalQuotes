package com.memor.thinkers.jilani.motivationalquotes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView left,right;
    Toolbar toolbar;
    TextView text;
    static int i,j;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    private List<QuotesStructure> listItems;
    Sqlite sqlite;
    static boolean mode,bc;
    static boolean language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Motivational Quotes");

        viewPager=(ViewPager)findViewById(R.id.viewpager);

        ImageView imag=(ImageView)findViewById(R.id.theme);
         text=(TextView)findViewById(R.id.quotes);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        left=(ImageView)findViewById(R.id.left_arrow);
        right=(ImageView)findViewById(R.id.right_arrow);


        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.arrowScroll(View.FOCUS_LEFT);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.arrowScroll(View.FOCUS_RIGHT);
            }
        });

        jsonparse();
        handleNotification();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment=null;
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            Intent i=new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            this.overridePendingTransition(0,0);


        } else if (id == R.id.nav_gallery)

        {
            fragment=new About();
            Bundle b= new Bundle();
            b.putBoolean("mode",mode);
            fragment.setArguments(b);

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/dev?id=5700313618786177705"));
            startActivity(intent);

        }

        else if (id == R.id.settings) {
            fragment=new Settings();
//            Bundle b= new Bundle();
            Bundle c=new Bundle();
            Bundle d=new Bundle();
//            Bundle e=new Bundle();
//            Bundle extra1=getIntent().getExtras();
//            bc=extra1.getBoolean("b");
//            j=extra1.getInt("j");
            c.putBoolean("mode",mode);
            d.putInt("i",i);
//            d.putBoolean("language",language);
//            e.putInt("j",j);

//            Toast.makeText(this, "ABCD"+bc, Toast.LENGTH_SHORT).show();
//            fragment.setArguments(e);
//            fragment.setArguments(b);
            fragment.setArguments(c);
            fragment.setArguments(d);

        }
//        else if (id == R.id.whitetheme) {
//            if(i==0 && mode==false)
//            {
//                Toast.makeText(this, "Already In Day Mode", Toast.LENGTH_SHORT).show();
//            }
//
//            else {
//                i=0;
//                mode=false;
//                Intent a=new Intent(this, MainActivity.class);
//                a.putExtra("mode",mode);
//                a.putExtra("i",i);
//                a.putExtra("language",language);
//                a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(a);
//                overridePendingTransition(0,0);
//            }


//        }

//        else if (id == R.id.Black) {

//            if (i == 1 && mode == true) {
//                Toast.makeText(this, "Already In Night Mode", Toast.LENGTH_SHORT).show();
//            } else {
//                i = 1;
//                mode = true;
//                Intent a = new Intent(this, MainActivity.class);
//                a.putExtra("mode", mode);
//                a.putExtra("i", i);
//                a.putExtra("language",language);
//                a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(a);
//                overridePendingTransition(0, 0);
//            }
//        }
//        else if (id == R.id.nav_send) {
//
//        }


        if(fragment!=null)
        {
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.mainfragment, fragment);
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void jsonparse()
    {

        Bundle extra=getIntent().getExtras();
        if(extra!=null){
            mode=extra.getBoolean("mode");
            i=extra.getInt("i");
            bc=extra.getBoolean("b");
            Toast.makeText(this, "jilani"+bc, Toast.LENGTH_SHORT).show();
            language=extra.getBoolean("language");
        }
//        Toast.makeText(this, "HI I AM JILANI", Toast.LENGTH_SHORT).show();
        StringBuffer sbc = new StringBuffer();
        listItems=new ArrayList<>();
        BufferedReader br = null;
        try {
            if(language)
            {
                br = new BufferedReader(new InputStreamReader(getAssets().open("jilanihindi.json")));
            }
            else {
                br = new BufferedReader(new InputStreamReader(getAssets().open("jilani.json")));
            }

            String temp;
            while ((temp = br.readLine()) != null)
                sbc.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            try {
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        String myjsonstring = sbc.toString();
        try {
            JSONObject jsonObjMain = new JSONObject(myjsonstring);
            JSONArray jsonArray = jsonObjMain.getJSONArray("allquotes");

            for (int i =0; i < jsonArray.length(); i++) {

                JSONObject jsonObj = jsonArray.getJSONObject(i);
                QuotesStructure item= new QuotesStructure(jsonObj.getString("quote"),jsonObj.getString("author"));
                listItems.add(item);
//                Toast.makeText(this, ""+jsonObj.getString("quote"), Toast.LENGTH_SHORT).show();
            }

            viewPagerAdapter= new ViewPagerAdapter(listItems,MainActivity.this,mode,i,language);
            sqlite=new Sqlite(getApplicationContext());
            sqlite.jilani(MainActivity.this,listItems);
            viewPager.setAdapter(viewPagerAdapter);
          viewPager.setOffscreenPageLimit(1);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    private void handleNotification() {

        long t = System.currentTimeMillis();
//        jsonparse();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,01);
        calendar.set(Calendar.MINUTE, 11);
        calendar.set(Calendar.SECOND, 00);

        if (t <= calendar.getTimeInMillis()) {
            Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
            alarmIntent.setAction("alarm");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }
}
