package com.example.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Signup1Activity  extends Activity
{
  //Spinner spinsecurity1;
  EditText answer1,security2,answer2,email2,security1;
  Button reg1back,reg1next;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register1);
	 /*  spinsecurity1=(Spinner)findViewById(R.id.security1);
	    ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.security1spinner, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinsecurity1.setAdapter(adapter);
	    spinsecurity1.setOnItemSelectedListener(new MyOnItemSelectedListener());*/
		security1=(EditText)findViewById(R.id.security1);
	    answer1=(EditText)findViewById(R.id.answer1);
	    security2=(EditText)findViewById(R.id.security2);
	    answer2=(EditText)findViewById(R.id.answer2);
	    email2=(EditText)findViewById(R.id.email2);
	    reg1back=(Button)findViewById(R.id.reg1back);
	    reg1next=(Button)findViewById(R.id.reg1next);
	    
	    
	     final DatabaseHandler mydb=new DatabaseHandler(this);
	    reg1next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("Insert:","Updating More Registration Info...");
		         final String sec1=security1.getText().toString();
		         final String ans1=answer1.getText().toString();
		         final String sec2=security2.getText().toString();
		         final String ans2=answer2.getText().toString();
		         final String mail1=email2.getText().toString();
		         int id=mydb.getId(new Register());
		         mydb.addregister1(new Register(id,sec1,ans1,sec2,ans2,mail1));
		         Log.d("Update:", "Updating via Second-Form Of Registration...");
		         Intent i=new Intent(getApplicationContext(),Signup2Activity.class);
		         startActivity(i);
		        		
			}
		});
	    
	    reg1back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			   Intent i=new Intent(getApplicationContext(),SignupActivity.class);
			   startActivity(i);
			}
		});
	    
	    
	    
	
	
	}
/*	public class MyOnItemSelectedListener implements OnItemSelectedListener
	{
		public void onItemSelected(AdapterView<?> parent, View v, int pos, long id)
		{
			String security1=parent.getItemAtPosition(pos).toString();
			Toast.makeText(parent.getContext(), " "+parent.getItemAtPosition(pos).toString() , Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	*/
}
