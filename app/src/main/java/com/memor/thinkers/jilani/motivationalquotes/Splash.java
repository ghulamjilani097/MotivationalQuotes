package com.memor.thinkers.jilani.motivationalquotes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.sdsmdg.harjot.rotatingtext.RotatingTextWrapper;
//import com.sdsmdg.harjot.rotatingtext.models.Rotatable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Splash extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    Spinner lang;
    static int i;
//    static boolean Default1;
    static boolean language=false;
    static boolean mode=false;
    private String[] title = {
            "ENJOY EVERY MOMENT", "STAY MOTIVATED", "MAKE IT HAPPEN",
            "YOU CAN DO IT", "THINK POSITIVE BE POSITIVE", "EVERY MOMENT MATTERS",
            "IF NOT NOW, THEN WHEN?", "SMILE", "GOOD VIBES ONLY", "WORK HARD DREAM BIG", "PROVE THEM WRONG",
            "NEVER STOP DREAMING", "SHINE LIKE THE STARS", "COLLECT MOMENTS, NOT THINGS", "LIFE IS A GIFT"
    };


    String choose[]={"Select Language","English","हिंदी"};
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);


        lang=(Spinner) findViewById(R.id.spinner);
        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,choose);
        lang.setAdapter(arrayAdapter);
        lang.setOnItemSelectedListener(this);

//        if(Default1)
//        {
//            lang.setVisibility(View.INVISIBLE);
//            final Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent d=new Intent(getApplicationContext(), MainActivity.class);
//                    SharedPreferences settings = getSharedPreferences("Default1", 0);
//                    Default1= settings.getBoolean("firstRun", true);
//                    finish();
//                    startActivity(d);
//                }
//            }, 3000);
//
//
//        }

        final TextView splash,quote;
        splash=(TextView)findViewById(R.id.txtsplash);
        quote=(TextView)findViewById(R.id.splashquote);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "Barlow-Regular.ttf");
        splash.setTypeface(typeFace);
        quote.setTypeface(typeFace);

        final Random randGen = new Random();
        int rando = randGen.nextInt(5);
        String nextValue = title[randGen.nextInt(title.length)];
        quote.setText(nextValue);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                quote.setText(title[randGen.nextInt(title.length)]);
                handler.postDelayed(this, 5000);
            }
        }, 5000);




//        RotatingTextWrapper rotatingTextWrapper = (RotatingTextWrapper) findViewById(R.id.splashquote);
//        rotatingTextWrapper.setSize(35);
//
//        Rotatable rotatable = new Rotatable(Color.parseColor("#FFA036"), 1000, nextValue);
//        rotatable.setSize(35);
//        rotatable.setAnimationDuration(500);
//
//        rotatingTextWrapper.setContent("?", rotatable);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String data = adapterView.getItemAtPosition(i).toString();
        lang.setSelected(false);
//        Toast.makeText(this, ""+data, Toast.LENGTH_SHORT).show();
        if(data.equals("English"))
        {
            i=0;
//            Default1=true;
//            SharedPreferences settings = getSharedPreferences("Default1", 0);
//            Default1= settings.getBoolean("firstRun", true);
            language=false;
            mode=false;
            Intent d=new Intent(getApplicationContext(), MainActivity.class);
            d.putExtra("mode",mode);
            d.putExtra("i",i);
            d.putExtra("language",language);
//            d.putExtra("Default",Default1);
            d.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(d);
            finish();
            Splash.this.overridePendingTransition(0,0);
            Toast.makeText(Splash.this, "English", Toast.LENGTH_SHORT).show();
        }
        else if(data.equals("हिंदी")){
            i=0;
            language=true;
            mode=false;
            Intent d=new Intent(getApplicationContext(), MainActivity.class);
            d.putExtra("mode",mode);
            d.putExtra("i",i);
            d.putExtra("language",language);
            d.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(d);
            finish();
            Splash.this.overridePendingTransition(0,0);
            Toast.makeText(Splash.this, "हिंदी", Toast.LENGTH_SHORT).show();
        }
        else if(data.equals("Select Language"))
        {
            Toast.makeText(this, "Please Select a Language to Continue", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
