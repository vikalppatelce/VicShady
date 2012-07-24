package com.example.myproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPasswordActivity extends Activity{
   EditText username;
   Button mailme;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forget);
		username=(EditText)findViewById(R.id.fusername);
		mailme=(Button)findViewById(R.id.fbutton);
		
		mailme.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try
				{
					
				}
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

}
