package com.example.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MyProjectActivity extends Activity {
    /** Called when the activity is first created. */
     Button login,signup;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
        login=(Button)findViewById(R.id.hlogin);
        signup=(Button)findViewById(R.id.hsignup);
        try
        {
        	
        login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),LoginActivity.class);
				startActivity(i);
			}
		});
        }
        catch (Exception e) {
			// TODO: handle exception
        	Log.w(MyProjectActivity.class.getName(),"Login_Error",e);
		}
        try
        {
        signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),SignupActivity.class);
				startActivity(i);
			}
		});
        }
        catch(Exception e)
        {
        	Log.w(MyProjectActivity.class.getName(),"Sign_Error",e);
        }
    }
}