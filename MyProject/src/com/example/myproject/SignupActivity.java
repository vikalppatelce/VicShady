package com.example.myproject;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends Activity{
	
	EditText firstname,lastname,email1,password,confirmpassword;
	Button cancel,next;
	SQLiteDatabase database;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		firstname=(EditText)findViewById(R.id.firstname);
		lastname=(EditText)findViewById(R.id.lastname);
		email1=(EditText)findViewById(R.id.email);
		password=(EditText)findViewById(R.id.password);
		confirmpassword=(EditText)findViewById(R.id.confirmpassword);
		cancel=(Button)findViewById(R.id.regcancel);
		next=(Button)findViewById(R.id.regnext);
		final  DatabaseHandler mydb = new DatabaseHandler(this);
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*Intent i=new Intent(null,MyProjectDatabase.class);
				startActivity(i);*/
				
				
				Log.d("Insert:","Inserting...");
				final String fname=firstname.getText().toString();
				final String lname=lastname.getText().toString();
				final String email=email1.getText().toString();
				final String pass=password.getText().toString();
				final String cpass=confirmpassword.getText().toString();
		
				
				
				mydb.addregister(new Register(fname,lname,email,pass,cpass));
				Intent i=new Intent(getApplicationContext(),Signup1Activity.class);
				startActivity(i);
				Log.d("Insert:", "Insert via First-Form Of Registration...");
			}
		});	
     }

}
