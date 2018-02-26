package com.memor.thinkers.jilani.motivationalquotes;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Random;

public class Splash extends AppCompatActivity {

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
