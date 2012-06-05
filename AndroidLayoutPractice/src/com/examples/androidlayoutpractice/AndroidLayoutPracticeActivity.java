package com.examples.androidlayoutpractice;

import android.app.Activity;
import android.os.Bundle;

public class AndroidLayoutPracticeActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_layout);
    }
}