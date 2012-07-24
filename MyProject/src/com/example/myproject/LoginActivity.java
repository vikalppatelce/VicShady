package com.example.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity{
	EditText username,password;
	Button Signin,forget;
	String user;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		username=(EditText)findViewById(R.id.username);
		password=(EditText)findViewById(R.id.password);
		Signin=(Button)findViewById(R.id.signin);
		forget=(Button)findViewById(R.id.forget);
		try
		{
		Signin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent i=new Intent(getApplication(),DashBoardActivity.class);
			startActivity(i);
			}
		});
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		try
		{
		forget.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),ForgetPasswordActivity.class);
				i.putExtra(user, username.getText().toString());
				startActivity(i);
			}
		});
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		//Toast.makeText(getApplicationContext(), "Work In Progress...", Toast.LENGTH_LONG).show();
		
	}

}
