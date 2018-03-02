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

    Button day,night,english,hindi;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        day=(Button)findViewById(R.id.whitetheme);
        english=(Button)findViewById(R.id.English);
        hindi=(Button)findViewById(R.id.Hindi);
        night=(Button)findViewById(R.id.darktheme);
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=0;
                mode=false;
                Intent c=new Intent(getApplicationContext(), MainActivity.class);
                c.putExtra("mode",mode);
                c.putExtra("i",i);
                c.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(c);
                Splash.this.overridePendingTransition(0,0);
                Toast.makeText(Splash.this, "Day", Toast.LENGTH_SHORT).show();
            }
        });

        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=1;
                mode=true;
                Intent b=new Intent(getApplicationContext(), MainActivity.class);
                b.putExtra("mode",mode);
                b.putExtra("i",i);
                b.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(b);
                Splash.this.overridePendingTransition(0,0);
                Toast.makeText(Splash.this, "Night", Toast.LENGTH_SHORT).show();
            }
        });

        hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
}
