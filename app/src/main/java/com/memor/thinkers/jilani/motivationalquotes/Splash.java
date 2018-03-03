package com.memor.thinkers.jilani.motivationalquotes;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Splash extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button day,night,english,hindi;
    Spinner lang;
    static int i;
    static boolean language=false;
    static boolean mode=false;
    private String[] title = {
            "ENJOY EVERY MOMENT",
            "STAY MOTIVATED",
            "MAKE IT HAPPEN",
            "YOU CAN DO IT",
            "THINK POSITIVE BE POSITIVE",
            "EVERY MOMENT MATTERS",
            "IF NOT NOW, THEN WHEN?"
    };

    String choose[]={"Select Language","Hindi","English"};
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

//        day=(Button)findViewById(R.id.whitetheme);
//        english=(Button)findViewById(R.id.language);
        //hindi=(Button)findViewById(R.id.Hindi);
        lang=(Spinner) findViewById(R.id.spinner);
        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,choose);
        lang.setAdapter(arrayAdapter);
//        lang.setPrompt("Choose Your Language");

//        lang.setSelection(0,false);
        lang.setSelected(false);  // must
//        lang.setSelection(0,true);  //must
        lang.setOnItemSelectedListener(this);


//        lang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String data = adapterView.getItemAtPosition(i).toString();
//                if(data.equals("English"))
//                {
//                    i=0;
//                    language=false;
//                    mode=false;
//                    Intent d=new Intent(getApplicationContext(), MainActivity.class);
//                    d.putExtra("mode",mode);
//                    d.putExtra("i",i);
//                    d.putExtra("language",language);
//                    d.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(d);
//                    Splash.this.overridePendingTransition(0,0);
//                    Toast.makeText(Splash.this, "English", Toast.LENGTH_SHORT).show();
//                }
//                else if(data.equals("Hindi")){
//                    i=0;
//                    language=true;
//                    mode=false;
//                    Intent d=new Intent(getApplicationContext(), MainActivity.class);
//                    d.putExtra("mode",mode);
//                    d.putExtra("i",i);
//                    d.putExtra("language",language);
//                    d.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(d);
//                    Splash.this.overridePendingTransition(0,0);
//                    Toast.makeText(Splash.this, "Hindi", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
////                Toast.makeText(Splash.this, "Jilani", Toast.LENGTH_SHORT).show();
//
//            }
//        });


        TextView splash,quote;
        splash=(TextView)findViewById(R.id.txtsplash);
        quote=(TextView)findViewById(R.id.splashquote);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "Barlow-Regular.ttf");
        splash.setTypeface(typeFace);
        quote.setTypeface(typeFace);

        Random randGen = new Random();
        int rando = randGen.nextInt(5);

                String nextValue = title[randGen.nextInt(title.length)];


        quote.setText(nextValue);

//        Thread thread=new Thread()
//        {
//            @Override
//            public void run() {
//                try {
//                    sleep(3000);
//                    Intent intent= new Intent(getApplicationContext(),MainActivity.class);
//                    intent.putExtra("mode",mode);
//                    startActivity(intent);
//                    finish();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        thread.start();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String data = adapterView.getItemAtPosition(i).toString();
        lang.setSelected(false);
        Toast.makeText(this, ""+data, Toast.LENGTH_SHORT).show();
        if(data.equals("English"))
        {
            i=0;
            language=false;
            mode=false;
            Intent d=new Intent(getApplicationContext(), MainActivity.class);
            d.putExtra("mode",mode);
            d.putExtra("i",i);
            d.putExtra("language",language);
            d.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(d);
            Splash.this.overridePendingTransition(0,0);
            Toast.makeText(Splash.this, "English", Toast.LENGTH_SHORT).show();
        }
        else if(data.equals("Hindi")){
            i=0;
            language=true;
            mode=false;
            Intent d=new Intent(getApplicationContext(), MainActivity.class);
            d.putExtra("mode",mode);
            d.putExtra("i",i);
            d.putExtra("language",language);
            d.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(d);
            Splash.this.overridePendingTransition(0,0);
            Toast.makeText(Splash.this, "Hindi", Toast.LENGTH_SHORT).show();
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
