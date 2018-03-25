package com.memor.thinkers.jilani.motivationalquotes;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    ImageView left,right;
    Toolbar toolbar;
    TextView text;
    static int i,j;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    private List<QuotesStructure> listItems;
    static boolean mode,bc;
    static boolean language;
    boolean doubleBackToExitPressedOnce = false;

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

         NavigationView nv=(NavigationView)findViewById(R.id.nav_view);
         nv.setItemIconTintList(null);

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
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            if (getSupportActionBar().getTitle() == "About Us") {
                shabana();
            }
            else if (getSupportActionBar().getTitle() == "Settings") {
                shabana();
            }
            else
            {
                if (doubleBackToExitPressedOnce)
                {
                    super.onBackPressed();
                    return;
                }

                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Press once again to exit!", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment=null;
        int id = item.getItemId();

        if (id == R.id.Home)
        {
            if(getSupportActionBar().getTitle()=="Motivational Quotes")
            {
                Toast.makeText(this, "Already in Home", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent i = new Intent(this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                this.overridePendingTransition(0, 0);
            }
        }
        else if (id == R.id.About)
        {
            fragment=new About();
            Bundle b= new Bundle();
            b.putBoolean("mode",mode);
            fragment.setArguments(b);
        }
        else if (id == R.id.Rateus) {
            Uri uri = Uri.parse("market://details?id=" + getApplication().getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getApplication().getPackageName())));
            }
        }
        else if (id == R.id.settings)
        {
            fragment=new Settings();
            Bundle d=new Bundle();
            d.putBoolean("mode",mode);
            d.putInt("i",i);
            d.putBoolean("language",language);
            fragment.setArguments(d);

        }
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
            language=extra.getBoolean("language");
        }
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

            for (int i =0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                QuotesStructure item= new QuotesStructure(jsonObj.getString("quote"),jsonObj.getString("author"));
                listItems.add(item);
            }
            viewPagerAdapter= new ViewPagerAdapter(listItems,MainActivity.this,mode,i,language);
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
        overridePendingTransition(0,0);
    }

    public void shabana(){
        Intent a = new Intent(getApplicationContext(), MainActivity.class);
        a.putExtra("mode",mode);
        a.putExtra("i",i);
        a.putExtra("language",language);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
        finish();
        overridePendingTransition(0,0);
    }
}
