package com.memor.thinkers.jilani.motivationalquotes;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Splash extends AppCompatActivity {

    Button day,night;
    boolean mode=false;
    private String[] title = {
            "ENJOY EVERY MOMENT",
            "STAY MOTIVATED",
            "MAKE IT HAPPEN",
            "YOU CAN DO IT",
            "THINK POSITIVE BE POSITIVE",
            "EVERY MOMENT MATTERS",
            "IF NOT NOW, THEN WHEN?"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        day=(Button)findViewById(R.id.whitetheme);
        night=(Button)findViewById(R.id.darktheme);
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode=false;
                Toast.makeText(Splash.this, "Day", Toast.LENGTH_SHORT).show();
            }
        });

        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode=true;
                Toast.makeText(Splash.this, "Night", Toast.LENGTH_SHORT).show();
            }
        });

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

        Thread thread=new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("mode",mode);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
