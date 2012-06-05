package com.example.androidhive;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class AndroidExternalFontsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Font path
        String fontPath = "fonts/Face Your Fears.ttf";
        
        // text view label
        TextView txtGhost = (TextView) findViewById(R.id.ghost);
        
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        
        // Applying font
        txtGhost.setTypeface(tf);
    }
}