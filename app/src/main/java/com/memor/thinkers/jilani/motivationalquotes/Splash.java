package com.memor.thinkers.jilani.motivationalquotes;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class Splash extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    Spinner lang;
    static int i;
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
    SharedPref sharedPref;
    boolean isLanguage=false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        sharedPref=new SharedPref(Splash.this);
        lang=(Spinner) findViewById(R.id.spinner);
        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,choose);
        lang.setAdapter(arrayAdapter);
        lang.setOnItemSelectedListener(this);

        if(sharedPref.getLaguage()) {
            Toast.makeText(this, "Helllo", Toast.LENGTH_SHORT).show();
            lang.setVisibility(View.GONE);
            findViewById(R.id.textView).setVisibility(View.GONE);
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

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
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        String data = adapterView.getItemAtPosition(i).toString();
        lang.setSelected(false);

        if(data.equals("English"))
        {
            language=false;
            shabana();
            Toast.makeText(Splash.this, "English", Toast.LENGTH_SHORT).show();
        }
        else if(data.equals("हिंदी")){
            language=true;
            shabana();
        }
        else if(data.equals("Select Language"))
        {
            Toast.makeText(this, "Please Select a Language to Continue", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void shabana(){
        mode=false;
        i=0;
        isLanguage=true;
        sharedPref.setLanguage(isLanguage);
        Intent d=new Intent(getApplicationContext(), MainActivity.class);
        d.putExtra("mode",mode);
        d.putExtra("i",i);
        d.putExtra("language",language);
        d.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(d);
        finish();
        Splash.this.overridePendingTransition(0,0);

    }
}
