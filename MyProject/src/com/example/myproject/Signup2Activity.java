package com.example.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Signup2Activity extends Activity {
	CheckBox accept;
	Button submit,back,upload;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register2);
		accept=(CheckBox)findViewById(R.id.checkbox);
		submit=(Button)findViewById(R.id.register);
		back=(Button)findViewById(R.id.reg2back);
		upload=(Button)findViewById(R.id.upload);
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		upload.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent();
				i.setType("image/*");
				i.setAction(Intent.ACTION_GET_CONTENT);
				int REQUEST_CODE=100;
				startActivityForResult(Intent.createChooser(i, "Select Picture..."), REQUEST_CODE);
			}
			
		});
		
		
		
		
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		        Toast.makeText(getApplicationContext(), "Finally get Updated...", Toast.LENGTH_SHORT).show();	
			}
		});
	}

}
