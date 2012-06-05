package com.examples.helloformstuff;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class HelloFormStuffActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final EditText edittext = (EditText) findViewById(R.id.edittext);
        edittext.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                  // Perform action on key press
                  Toast.makeText(HelloFormStuffActivity.this, edittext.getText(), Toast.LENGTH_SHORT).show();
                  return true;
                }
                return false;
            }
        });
        final RatingBar ratingbar = (RatingBar) findViewById(R.id.ratingbar);
        ratingbar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(HelloFormStuffActivity.this, "New Rating: " + rating, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onButtonClicked(View v) {
        // Do something when the button is clicked
        Toast.makeText(HelloFormStuffActivity.this, "Button clicked", Toast.LENGTH_SHORT).show();
    }

    public void onCheckboxClicked(View v) {
        // Perform action on clicks, depending on whether it's now checked
        if (((CheckBox) v).isChecked()) {
            Toast.makeText(HelloFormStuffActivity.this, "Selected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(HelloFormStuffActivity.this, "Not selected", Toast.LENGTH_SHORT).show();
        }
    }
    public void onRadioButtonClicked(View v) {
        // Perform action on clicks
        RadioButton rb = (RadioButton) v;
        Toast.makeText(HelloFormStuffActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
    }
    public void onToggleClicked(View v) {
        // Perform action on clicks
        if (((ToggleButton) v).isChecked()) {
            Toast.makeText(HelloFormStuffActivity.this, "Toggle on", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(HelloFormStuffActivity.this, "Toggle off", Toast.LENGTH_SHORT).show();
        }
    }
}