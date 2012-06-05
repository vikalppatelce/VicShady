package com.examples.fetchtime;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FetchTimeActivity extends Activity {
    /** Called when the activity is first created. */
	private TextView mTimeText;
	private Button mStartButton;
	private long mDifference, mStartTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.main);
    
     mTimeText = (TextView) findViewById(R.id.TimeTextView);
    
     mStartButton = (Button) findViewById(R.id.StartButton);
    
     mStartButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {
       mStartTime = System.currentTimeMillis();
    
       final ProgressDialog progDialog;
       progDialog = ProgressDialog.show(FetchTimeActivity.this,
         getText(R.string.TimerText),
         getText(R.string.TimerPlaseWaitText), true);
       new Thread() {
        public void run() {
         try {
          // Place your algorithm here
          //
          
          for (int i = 1; i < 150; i++) {
           for (int j = 1; j < 150; j++) {
            Math.log(Math.pow(i, j));
           }
          }
    
          //
          //
          mHandler.sendEmptyMessage(0);
         } catch (Exception e) {
          Log.d("TimerTestException:", e.getMessage());
         }
         progDialog.dismiss();
        }
       }.start();
      }
     });
    }
    
    private Handler mHandler = new Handler() {
     public void handleMessage(Message msg) {
      mDifference = System.currentTimeMillis() - mStartTime;
      double formatedTime = ((double) mDifference) / 1000;
      mTimeText.setText(getText(R.string.TestRunningTime) + " "
        + formatedTime + " sec");
      mTimeText.setVisibility(TextView.VISIBLE);
     }
    };

}